package com.leidi.lteapp.base;

public class UploadHeadPicBean {

    /**
     * msg : 操作成功
     * imgUrl : http://192.168.8.26:9001//lte-gz/2022/09/01/Screenshot_20220215_111236_com.leidi.trainalarm_20220901112903A005.jpeg
     * code : 200
     */

    private String msg;
    private String imgUrl;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
