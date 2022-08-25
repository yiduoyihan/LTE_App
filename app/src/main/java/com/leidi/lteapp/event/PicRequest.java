package com.leidi.lteapp.event;

/**
 * @author 阎
 * @date 2021/8/4
 * @description 通过接口将MainActivity种取到的图片path传到SelfFragment中
 */
public interface PicRequest {

    void getPicPath(String path);

}