package com.jzeromq;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private final List<Frame> frames;

    public Message() {
        this.frames = new ArrayList<Frame>();
    }
    

}
