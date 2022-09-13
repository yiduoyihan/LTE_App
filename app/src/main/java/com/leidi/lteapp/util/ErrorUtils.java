package com.leidi.lteapp.util;

public class ErrorUtils {
    public static String whichError(String error) {
        if (error.contains("Failed to connect to")) {
            return "无法连接到服务器";
        } else if (error.equals("Unauthorized")){
            return "该资源无法下载";
        }else {
            return "";
        }
    }
}
