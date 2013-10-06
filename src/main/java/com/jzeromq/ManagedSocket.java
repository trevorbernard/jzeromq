package com.jzeromq;

import java.nio.ByteBuffer;

import org.zeromq.jni.ZMQ;

public class ManagedSocket implements Socket {
    private final SocketType type;

    private final long address;

    public ManagedSocket(ManagedContext context, SocketType type) {
        this.type = type;
        this.address = ZMQ.zmq_socket(context.getAddress(), type.getValue());
        if (this.address == -1) {
            int errno = ZMQ.zmq_errno();
            switch (errno) {
            case 1:
                throw new InvalidSocketException();
            }
        }
    }

    public long getAddress() {
        return address;
    }

    @Override
    public SocketType getType() {
        return type;
    }

    @Override
    public int send(byte[] buf) {
        return send(buf, 0, buf.length, 0);
    }

    @Override
    public int send(byte[] buf, int flags) {
        return send(buf, 0, buf.length, flags);
    }

    @Override
    public int send(byte[] buf, int offset, int length, int flags) {
        return ZMQ.zmq_send(address, buf, offset, length, flags);
    }

    @Override
    public int send(ByteBuffer bb, int flags) {
        return ZMQ.zmq_send(address, bb, flags);
    }

    @Override
    public byte[] receive() {
        return receive(0);
    }

    @Override
    public byte[] receive(int flags) {
        return ZMQ.zmq_recv(address, flags);
    }

    @Override
    public int receive(ByteBuffer bb, int flags) {
        return ZMQ.zmq_recv(address, bb, flags);
    }

    @Override
    public int recieve(byte[] buf, int offset, int length, int flags) {
        return ZMQ.zmq_recv(address, buf, offset, length, flags);
    }

    @Override
    public void connect(String endpoint) {
        ZMQ.zmq_connect(address, endpoint);
    }

    @Override
    public void disconnect(String endpoint) {
        ZMQ.zmq_disconnect(address, endpoint);
    }

    @Override
    public void bind(String endpoint) {
        ZMQ.zmq_bind(address, endpoint);
    }

    @Override
    public void unbind(String endpoint) {
        ZMQ.zmq_unbind(address, endpoint);
    }

    @Override
    public void subscribe(byte[] value) {
        ZMQ.zmq_setsockopt(address, SocketOptions.SUBSCRIBE.getValue(), value);
    }

    @Override
    public void unsubscribe(byte[] value) {
        ZMQ.zmq_setsockopt(address, SocketOptions.UNSUBSCRIBE.getValue(), value);
    }

    @Override
    public boolean hasReceiveMore() {
        return 1 == ZMQ.zmq_getsockopt_int(address, SocketOptions.RCVMORE.getValue());
    }

    @Override
    public void setSendBufferSize(int size) {
        ZMQ.zmq_setsockopt(address, SocketOptions.SNDBUF.getValue(), size);
    }

    @Override
    public void setReceiveBufferSize(int size) {
        ZMQ.zmq_setsockopt(address, SocketOptions.RCVBUF.getValue(), size);
    }

    @Override
    public void setIdentity(byte[] identity) {
        if (identity.length < 1 || identity.length > 255)
            throw new IllegalArgumentException("Identity must be at least 1 byte and at most 255");
        ZMQ.zmq_setsockopt(address, SocketOptions.IDENTITY.getValue(), identity);
    }

    @Override
    public void close() {
        if (!ZMQ.zmq_close(address)) {
            int errno = ZMQ.zmq_errno();
            if (errno == 1) {
                throw new InvalidSocketException();
            }
        }
    }
}
