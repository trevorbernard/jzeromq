package com.jzeromq;

import org.zeromq.jni.ZMQ;

public enum ErrorType {
    ENOTSUP(ZMQ.ENOTSUP), EPROTONOSUPPORT(ZMQ.EPROTONOSUPPORT), ENOBUFS(ZMQ.ENOBUFS);
    private final int value;

    ErrorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
