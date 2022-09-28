package com.leidi.lteapp.util;

import rxhttp.wrapper.annotation.DefaultDomain;

public class Url {
    @DefaultDomain() //设置为默认域名
    public static String baseUrl = "http://172.16.200.44:7080";
//    public static String baseUrl = "http://192.168.4.150:7080";
//    public static String baseUrl = "http://192.168.8.26:7080";
//    public static String baseUrl = "http://192.168.8.60:7080";

    public static final String login = "/app/login";
    public static final String getInfo = "/app/getInfo";
    public static final String login_out = "logout";
    public static final String task_list = "/app/task/list";
    public static final String task_detail = "/app/task/details/";
    public static final String task_delete = "/app/task/";
    public static final String task_create = "/app/task";
    public static final String task_arrive = "/app/task/arrive/";
    public static final String task_complete = "/app/task/complete";
    public static final String task_end = "/app/task/end/";
    public static final String knowledge = "/app/business/maintainInstruct/pageList";
    public static final String alarm_list = "/app/business/alarminfo/list";
    public static final String device_list = "/app/business/deviceStatus/list";
    public static final String change_pwd = "/app/updatePwd";
    public static final String check_update = "app/appset";
    public static final String sign_start = "/app/business/sgin";
    public static final String sign_end = "/app/business/sgin";
    public static final String sign_last = "/app/business/sgin/getLimit";
    public static final String upload_pic = "/app/avatar";
    public static final String word_to_pdf = "/app/business/maintainInstruct/getDetail";
}
