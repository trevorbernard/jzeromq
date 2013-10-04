package com.jzeromq;

public enum SocketOptions {
    AFFINITY(4), IDENTITY(5), SUBSCRIBE(6), UNSUBSCRIBE(7), RATE(8), RECOVERY_IVL(9), SNDBUF(11), RCVBUF(12), RCVMORE(
            13), FD(14), EVENTS(15), TYPE(16), LINGER(17), RECONNECT_IVL(18), BACKLOG(19), RECONNECT_IVL_MAX(21), MAXMSGSIZE(
            22), SNDHWM(23), RCVHWM(24), MULTICAST_HOPS(25), RCVTIMEO(27), SNDTIMEO(28), IPV4ONLY(31), LAST_ENDPOINT(32), ROUTER_MANDATORY(
            33), TCP_KEEPALIVE(34), TCP_KEEPALIVE_CNT(35), TCP_KEEPALIVE_IDLE(36), TCP_KEEPALIVE_INTVL(37), TCP_ACCEPT_FILTER(
            38), DELAY_ATTACH_ON_CONNECT(39), XPUB_VERBOSE(40);
    private final int value;

    SocketOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
