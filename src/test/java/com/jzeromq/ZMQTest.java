package com.jzeromq;

import java.nio.ByteBuffer;

import junit.framework.TestCase;

import org.junit.Test;

public class ZMQTest extends TestCase {
    @Test
    public void testPushPull() {
        Context context = null;
        try {
            context = ZMQ.context(1);
            Socket push = ZMQ.socket(context, SocketType.PUSH);
            push.connect("tcp://127.0.0.1:7001");

            Socket pull = ZMQ.socket(context, SocketType.PULL);
            pull.bind("tcp://*:7001");

            push.send("helloworld".getBytes());
            byte[] recv = pull.receive();
            assertEquals("helloworld", new String(recv));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                context.close();
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void testSendByteBuffer() {
        Context context = null;
        try {
            context = ZMQ.context(1);
            Socket push = ZMQ.socket(context, SocketType.PUSH);
            push.connect("tcp://127.0.0.1:7002");

            Socket pull = ZMQ.socket(context, SocketType.PULL);
            pull.bind("tcp://*:7002");
            ByteBuffer bb = ByteBuffer.allocateDirect(10);
            bb.put("helloworld".getBytes());
            bb.flip();
            push.send(bb, 0);
            byte[] recv = pull.receive(0);
            assertEquals("helloworld", new String(recv));
        } finally {
            try {
                context.close();
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void testReceiveByteBuffer() {
        Context context = null;
        try {
            context = ZMQ.context(1);
            Socket push = ZMQ.socket(context, SocketType.PUSH);
            push.connect("tcp://127.0.0.1:7003");

            Socket pull = ZMQ.socket(context, SocketType.PULL);
            pull.bind("tcp://*:7003");
            push.send("helloworld".getBytes());

            ByteBuffer bb = ByteBuffer.allocateDirect(10);
            pull.receive(bb, 0);
            bb.flip();
            byte[] buf = new byte[10];
            bb.get(buf);
            assertEquals("helloworld", new String(buf));
        } finally {
            try {
                context.close();
            } catch (Exception e) {
            }
        }
    }

    public void testCloseContextTwice() {
        Context context = ZMQ.context(1);
        context.close();
        context.close();
    }
    // @Test
    // public void testSetIdentity() {
    // Context context = null;
    // try {
    // context = ZMQ.context(1);
    // Socket req = ZMQ.socket(context, SocketType.REQ);
    // req.setIdentity(new byte[0]);
    // } finally {
    // try {
    // context.close();
    // } catch (Exception e) {
    // }
    // }
    // }
}
