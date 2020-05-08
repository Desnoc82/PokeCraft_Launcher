package fr.deltadesnoc.ping;

public class PingConnect {

    private String hostname;
    private int port = 25565;
    private int timeout = 2000;
    private String charset = "UTF-8";

    public PingConnect setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public PingConnect setPort(int port) {
        this.port = port;
        return this;
    }

    public PingConnect setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public PingConnect setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getCharset() {
        return charset;
    }


}
