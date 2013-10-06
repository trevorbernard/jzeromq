package com.jzeromq;

import java.io.Closeable;

public interface Context extends Closeable {
    public int getIOThreads();

    public void setIOThreads(int threads);
    
    public Socket createSocket(SocketType type);
    
    public void close();
}
