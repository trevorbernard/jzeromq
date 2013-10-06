package com.jzeromq;

import java.io.Closeable;
import java.nio.ByteBuffer;

public interface Socket extends Closeable {
    public SocketType getType();

    public int send(byte[] buf, int offset, int length, int flags);

    public int send(byte[] buf, int flags);

    public int send(byte[] buf);

    public int send(ByteBuffer bb);

    public int send(ByteBuffer bb, int flags);

    public byte[] receive();

    public byte[] receive(int flags);

    public int receive(ByteBuffer bb);

    public int receive(ByteBuffer bb, int flags);

    public int recieve(byte[] buf, int offset, int length, int flags);

    public void connect(String endpoint);

    public void disconnect(String endpoint);

    public void bind(String endpoint);

    public void unbind(String endpoint);

    public void subscribe(byte[] data);

    public void unsubscribe(byte[] data);

    public void setSendBufferSize(int size);

    public void setReceiveBufferSize(int size);

    public void setSendHWM(int size);

    public void setReceiveHWM(int size);

    public boolean hasReceiveMore();

    public void setIdentity(byte[] identity);

    public void close();
}
