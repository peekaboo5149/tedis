package org.nerds;

import java.util.Timer;

public class ApplicationEntry {

    public static void main(String[] args) throws Exception {
        try (TCPServer server = new NettyTCPServer()) {
            server.start(8080);
            Timer memoryCleaner = new Timer("Memory Cleaner");
            Runtime.getRuntime().addShutdownHook(new Thread(memoryCleaner::cancel));
            memoryCleaner.schedule(new MemoryCleanerTask(), 10000L);
        }
    }

}
