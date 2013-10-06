package com.jzeromq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Message {
    private final List<Frame> frames;

    public Message() {
        this.frames = new ArrayList<Frame>();
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public static Message receiveMessage(Socket socket) {
        Message message = new Message();
        do {
            byte[] part = socket.receive();
            message.addFrame(new Frame(part, socket.hasReceiveMore()));
        } while (socket.hasReceiveMore());

        return message;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
