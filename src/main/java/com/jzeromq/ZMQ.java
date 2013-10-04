package com.jzeromq;

public class ZMQ {
    public static Context context(int ioThreads) {
        return new ManagedContext(ioThreads);
    }

    public static Socket socket(Context context, SocketType type) {
        return context.createSocket(type);
    }
}
