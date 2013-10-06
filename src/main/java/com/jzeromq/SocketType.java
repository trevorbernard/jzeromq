package com.jzeromq;

import org.zeromq.jni.ZMQ;

public enum SocketType {

    PAIR(ZMQ.PAIR), PUB(ZMQ.PUB), SUB(ZMQ.SUB), REQ(ZMQ.REQ), REP(ZMQ.REP), DEALER(ZMQ.DEALER), ROUTER(ZMQ.ROUTER), PULL(
            ZMQ.PULL), PUSH(ZMQ.PUSH), XPUB(ZMQ.XPUB), XSUB(ZMQ.XSUB), XREQ(ZMQ.XREQ), XREP(ZMQ.XREP);

    private final int value;

    SocketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
