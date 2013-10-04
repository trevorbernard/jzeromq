package com.jzeromq;

import java.io.Closeable;
import java.nio.ByteBuffer;

public interface Socket extends Closeable {
    public SocketType getType();

    public int send(byte[] buf, int offset, int length, int flags);

    public int send(byte[] buf, int flags);

    public int send(byte[] buf);

    public int send(ByteBuffer bb, int flags);

    public byte[] receive(int flags);

    public void recieve(ByteBuffer bb, int flags);

    public void recieve(byte[] buf, int offset, int length, int flags);

    public void connect(String endpoint);

    public void disconnect(String endpoint);

    public void bind(String endpoint);

    public void unbind(String endpoint);

    public void subscribe(byte[] data);

    public void unsubscribe(byte[] data);

}
