package com.leidi.lteapp.event;

public class TokenInvalidEvent {
    String msg;

    public TokenInvalidEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
