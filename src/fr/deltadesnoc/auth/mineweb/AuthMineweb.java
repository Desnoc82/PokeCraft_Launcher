package fr.deltadesnoc.auth.mineweb;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.deltadesnoc.Main;
import fr.deltadesnoc.utils.FxAlert;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

public class AuthMineweb {

    private boolean connected = false;
    private String pseudo;
    private String hash;
    public AuthMineweb(String pseudo, String password){
       try{
           URLConnection connection = (new URL("https://pokecraft-fr.fr/auth/start?username=" + pseudo + "&password=" + getSHA256(password))).openConnection();
           connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
           connection.connect();
           InputStream is = connection.getInputStream();
           BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
           String inputLine;
           while ((inputLine = in.readLine()) != null) {
               switch (inputLine){
                   case "success_ok":
                       Main.logger.log("Connexion ok");
                       setHash(getSHA256(password));
                       setPseudo(pseudo);
                       setConnected(true);
                       break;
                   case "error_password":
                       Main.logger.warn("Une erreur est survenue durant l'authentification.");
                       FxAlert alert = new FxAlert(Alert.AlertType.ERROR, "PokeCraft Launcher | Erreur", "Une erreur est survenue", "Si l'erreur perciste veuillez contacter un membre du staff !", new Exception("Il y a une erreur dans le mot de passe entré"), "");
                       alert.setIcon("logo");
                       alert.setImage("logo", 100, 100);
                       alert.show();
                       break;
                   default:
                       Main.logger.warn(inputLine);
                       break;
               }
           }
           in.close();
       }catch (Exception e){
           Main.logger.warn(e.getMessage());
       }
    }

    public String getInfo(String info){
        if(isConnected()){
            try {
                URLConnection connection = (new URL("https://pokecraft-fr.fr/auth/getDataLauncher?username=" + getPseudo() + "&password=" + getHash())).openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    JsonObject jsonObject = (JsonObject) new JsonParser().parse(inputLine);
                    return String.valueOf(jsonObject.get(info)).replaceAll("\"", "");
                }
            } catch (IOException e) {
                Main.logger.warn(e.getMessage());
            }
        }else{
            Main.logger.warn("Vous n'êtes pas connecté ");
        }
        return "";
    }

    private String getSHA256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        AtomicReference<StringBuffer> stringBuffer = new AtomicReference<>(new StringBuffer());
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1)stringBuffer.get().append('0');
            stringBuffer.get().append(hex);
        }
        return stringBuffer.toString();
    }

    public AuthMineweb(){}

    public boolean isConnected() { return connected; }
    public String getPseudo() { return pseudo; }
    private String getHash() { return hash; }
    private void setConnected(boolean connected) { this.connected = connected; }
    private void setPseudo(String pseudo) { this.pseudo = pseudo; }
    private void setHash(String hash) { this.hash = hash; }
}
