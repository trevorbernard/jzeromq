package com.jzeromq;

public enum SocketType {

    PAIR(0), PUB(1), SUB(2), REQ(3), REP(4), DEALER(5), ROUTER(6), PULL(7), PUSH(8), XPUB(9), XSUB(10), XREQ(5), XREP(6);

    private final int value;

    SocketType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
