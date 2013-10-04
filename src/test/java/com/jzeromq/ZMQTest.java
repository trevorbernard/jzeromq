package com.jzeromq;

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
}
