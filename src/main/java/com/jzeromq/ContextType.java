package com.jzeromq;

public enum ContextType {
    IO_THREADS(1), MAX_SOCKETS(2);
    private final int value;

    ContextType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
