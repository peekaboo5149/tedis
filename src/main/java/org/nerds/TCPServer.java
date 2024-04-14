package org.nerds;

public interface TCPServer extends AutoCloseable{
    void start(int port) throws Exception;
}
