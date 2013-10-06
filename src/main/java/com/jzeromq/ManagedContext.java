package com.jzeromq;

import java.util.ArrayList;
import java.util.List;

import org.zeromq.jni.ZMQ;

public class ManagedContext implements Context {
    private final List<Socket> sockets = new ArrayList<Socket>();

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
        return ZMQ.zmq_ctx_get(address, ContextType.IO_THREADS.getValue());
    }

    @Override
    public void setIOThreads(int ioThreads) {
        ZMQ.zmq_ctx_set(address, ContextType.IO_THREADS.getValue(), ioThreads);
    }

    @Override
    public Socket createSocket(SocketType type) {
        ManagedSocket socket = new ManagedSocket(this, type);
        sockets.add(socket);
        return socket;
    }

    @Override
    public void close() {
        for (Socket s : sockets) {
            s.close();
        }
        sockets.clear();
        if (!ZMQ.zmq_ctx_destroy(address)) {
            final int errno = ZMQ.zmq_errno();
            if (ZMQ.ETERM == errno) {
                throw new InvalidContextException();
            }
        }
    }
}
