package com.leidi.lteapp.event;

public class ChangeHeadPicEvent {
    String headPicPath;

    public String getHeadPicPath() {
        return headPicPath;
    }

    public void setHeadPicPath(String headPicPath) {
        this.headPicPath = headPicPath;
    }

    public ChangeHeadPicEvent(String headPicPath) {
        this.headPicPath = headPicPath;
    }
}
