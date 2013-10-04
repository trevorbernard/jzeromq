package com.jzeromq;

import java.util.Arrays;

public class Frame {
    private final byte[] data;
    private final boolean more;

    public Frame(byte[] data, boolean more) {
        this.data = data;
        this.more = more;
    }

    public byte[] getData() {
        return data;
    }

    public boolean isMore() {
        return more;
    }

    public long size() {
        return (data != null) ? data.length : 0;
    }

    @Override
    public int hashCode() {
        return (data != null) ? Arrays.hashCode(data) : 0;
    }
}
