package me.hammer86gn.discordjar.api.connection.websocket.exception;

public class RateLimitOverflowException extends Exception {
    public RateLimitOverflowException() {
        super();
    }

    public RateLimitOverflowException(String s) {
        super(s);
    }

}
