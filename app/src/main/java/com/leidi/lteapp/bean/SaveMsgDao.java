package com.leidi.lteapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SaveMsgDao {
    //@Id：主键，通过这个注解标记的字段必须是Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
    @Id(autoincrement = true)
    private Long id;
    private String taskId;
    private String text1;
    private String text2;
    private String text3;
    private String picUrl;
    @Generated(hash = 958614386)
    public SaveMsgDao(Long id, String taskId, String text1, String text2,
            String text3, String picUrl) {
        this.id = id;
        this.taskId = taskId;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.picUrl = picUrl;
    }
    @Generated(hash = 1349002444)
    public SaveMsgDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTaskId() {
        return this.taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getText1() {
        return this.text1;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }
    public String getText2() {
        return this.text2;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }
    public String getText3() {
        return this.text3;
    }
    public void setText3(String text3) {
        this.text3 = text3;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
