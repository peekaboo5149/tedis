package org.nerds;

public class ApplicationEntry {

    public static void main(String[] args) throws Exception {
        try (TCPServer server = new NettyTCPServer()) {
            server.start(8080);
        }
    }

}
