package com.leidi.lteapp.util;

import rxhttp.wrapper.annotation.DefaultDomain;

public class Url {
    @DefaultDomain() //设置为默认域名
//    public static String baseUrl = "http://shenhaitao.top:8910";
    public static String baseUrl = "http://192.168.8.26:7080";

    public static final String login = "/app/login";
    public static final String getInfo = "/app/getInfo";
    public static final String login_out = "logout";
    public static final String task_list = "/app/task/list";
    public static final String task_detail = "/app/task/";
    public static final String task_delete = "/app/task/";
    public static final String task_create = "/app/task";
    public static final String task_arrive = "/app/task/arrive/";
    public static final String task_complete = "/app/task/complete";
    public static final String knowledge = "/app/business/maintainInstruct/pageList";
    public static final String alarm_list = "/app/business/alarminfo/list";
    public static final String device_list = "/app/business/deviceStatus/list";
    public static final String change_pwd = "/app/updatePwd";
    public static final String check_update = "检查更新";
}
