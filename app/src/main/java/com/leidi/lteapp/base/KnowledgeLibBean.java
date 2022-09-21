package com.leidi.lteapp.base;

import java.util.List;

public class KnowledgeLibBean {

    /**
     * total : 1
     * rows : [{"searchValue":null,"createBy":"admin","createTime":"2022-08-18 14:24:05",
     * "updateBy":"admin","updateTime":"2022-08-31 18:04:31","remark":null,"params":{},"id":"1",
     * "major":"2","deviceType":"的范德萨发","fileType":"3","fileName":"klks","fileUrl":"hiiodsf",
     * "delFlag":0}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsBean> getRows() {
        return data;
    }

    public void setRows(List<RowsBean> rows) {
        this.data = rows;
    }

    public static class RowsBean {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2022-08-18 14:24:05
         * updateBy : admin
         * updateTime : 2022-08-31 18:04:31
         * remark : null
         * params : {}
         * id : 1
         * major : 2
         * deviceType : 的范德萨发
         * fileType : 3
         * fileName : klks
         * fileUrl : hiiodsf
         * delFlag : 0
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private String id;
        private String major;
        private String deviceType;
        private String fileType;
        private String fileName;
        private String fileUrl;
        private int delFlag;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public static class ParamsBean {
        }
    }
}
