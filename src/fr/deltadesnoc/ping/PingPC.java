package fr.deltadesnoc.ping;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PingPC {

    public PingInfos.V1_7_10 getPing_V1_7_10(final String hostname) throws IOException {
        return this.getPing_V1_7_10(new PingConnect().setHostname(hostname));
    }


    public PingInfos.V1_7_10 getPing_V1_7_10(final PingConnect options) throws IOException {
        PingUtils.validate(options.getHostname(), "Le nom d'hôte ne peut pas être nul");
        PingUtils.validate(options.getPort(), "Le port ne peut pas être nul");

        final Socket socket = new Socket();
        socket.connect(new InetSocketAddress(options.getHostname(), options.getPort()), options.getTimeout());
        final long startTime = System.currentTimeMillis();
        final DataInputStream in = new DataInputStream(socket.getInputStream());
        final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        ByteArrayOutputStream handshake_bytes = new ByteArrayOutputStream();
        DataOutputStream handshake = new DataOutputStream(handshake_bytes);

        handshake.writeByte(PingUtils.PACKET_HANDSHAKE);
        PingUtils.writeVarInt(handshake, PingUtils.PROTOCOL_VERSION);
        PingUtils.writeVarInt(handshake, options.getHostname().length());
        handshake.writeBytes(options.getHostname());
        handshake.writeShort(options.getPort());
        PingUtils.writeVarInt(handshake, PingUtils.STATUS_HANDSHAKE);

        PingUtils.writeVarInt(out, handshake_bytes.size());
        out.write(handshake_bytes.toByteArray());

        out.writeByte(0x01); // Size of packet
        out.writeByte(PingUtils.PACKET_STATUSREQUEST);

        PingUtils.readVarInt(in); // Size
        int id = PingUtils.readVarInt(in);

        PingUtils.io(id == -1, "\n" + "Le serveur s'est arrêté prématurément.");
        PingUtils.io(id != PingUtils.PACKET_STATUSREQUEST, "Le serveur a renvoyé un identifiant de paquet non valide.");

        int length = PingUtils.readVarInt(in);
        PingUtils.io(length == -1, "Le serveur s'est arrêté prématurément.");
        PingUtils.io(length == 0, "\n" + "Le serveur a renvoyé une valeur inattendue.");

        byte[] data = new byte[length];
        in.readFully(data);
        String json = new String(data, options.getCharset());

        long now = System.currentTimeMillis();
        out.writeByte(0x09); // Size of packet
        out.writeByte(PingUtils.PACKET_PING);
        out.writeLong(now);

        PingUtils.readVarInt(in);
        id = PingUtils.readVarInt(in);
        PingUtils.io(id == -1, "Le serveur s'est arrêté prématurément.");
        PingUtils.io(id != PingUtils.PACKET_PING, "Le serveur a renvoyé un identifiant de paquet non valide.");

        PingInfos.V1_7_10 infos = new Gson().fromJson(json, PingInfos.V1_7_10.class);
        infos.setLatency(now - startTime);

        handshake.close();
        handshake_bytes.close();
        out.close();
        in.close();
        socket.close();

        return infos;
    }
}
