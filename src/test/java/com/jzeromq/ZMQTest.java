package com.jzeromq;

import java.nio.ByteBuffer;

import junit.framework.TestCase;

import org.junit.Test;

public class ZMQTest extends TestCase {
    @Test
    public void testPushPull() {
        Context context = ZMQ.context(1);
        Socket push = ZMQ.socket(context, SocketType.PUSH);
        push.connect("tcp://127.0.0.1:6001");

        Socket pull = ZMQ.socket(context, SocketType.PULL);
        pull.bind("tcp://*:6001");

        push.send("helloworld".getBytes());
        byte[] recv = pull.receive(0);
        assertEquals("helloworld", new String(recv));
    }

    @Test
    public void testSendByteBuffer() {
        Context context = ZMQ.context(1);
        Socket push = ZMQ.socket(context, SocketType.PUSH);
        push.connect("tcp://127.0.0.1:6002");

        Socket pull = ZMQ.socket(context, SocketType.PULL);
        pull.bind("tcp://*:6002");
        ByteBuffer bb = ByteBuffer.allocateDirect(10);
        bb.put("helloworld".getBytes());
        bb.flip();
        push.send(bb, 0);
        byte[] recv = pull.receive(0);
        assertEquals("helloworld", new String(recv));
    }

    @Test
    public void testReceiveByteBuffer() {
        Context context = ZMQ.context(1);
        Socket push = ZMQ.socket(context, SocketType.PUSH);
        push.connect("tcp://127.0.0.1:6003");

        Socket pull = ZMQ.socket(context, SocketType.PULL);
        pull.bind("tcp://*:6003");
        push.send("helloworld".getBytes());

        ByteBuffer bb = ByteBuffer.allocateDirect(10);
        pull.receive(bb, 0);
        bb.flip();
        byte[] buf = new byte[10];
        bb.get(buf);
        assertEquals("helloworld", new String(buf));
    }
}
