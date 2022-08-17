package com.leidi.lteapp.util;

import rxhttp.wrapper.annotation.DefaultDomain;

public class Url {
    @DefaultDomain() //设置为默认域名
    public static String baseUrl = "http://172.16.200.50:7080";

    public static final String login = "/app/login";
    public static final String login_out = "logout";
}
