package com.jzeromq;

import org.zeromq.jni.ZMQ;

public class ManagedContext implements Context {
    public static final int IO_THREADS = 1;
    public static final int MAX_SOCKETS = 2;

    private final long address;

    public ManagedContext(int ioThreads) {
        this.address = ZMQ.zmq_ctx_new();
        setIOThreads(ioThreads);
    }

    public long getAddress() {
        return address;
    }

    @Override
    public int getIOThreads() {
        return ZMQ.zmq_ctx_get(address, IO_THREADS);
    }

    @Override
    public void setIOThreads(int ioThreads) {
        ZMQ.zmq_ctx_set(address, IO_THREADS, ioThreads);
    }

    @Override
    public Socket createSocket(SocketType type) {
        return new ManagedSocket(this, type);
    }

    @Override
    public void close() {
        if (!ZMQ.zmq_ctx_destroy(address)) {
            int errno = ZMQ.zmq_errno();
            if (errno == 1) {
                throw new InvalidContextException();
            }
        }
    }
}
