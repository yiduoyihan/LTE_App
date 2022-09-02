package com.leidi.lteapp.bean;

public class UpdataBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"admin","createTime":"2022-09-02 16:05:59","updateBy":"","updateTime":null,"remark":null,"params":{},"id":"8","packageName":"app.apk","version":"","url":"http://192.168.8.26:9001/lte-gz/2022/09/02/app_20220902160559A005.apk","issueType":"1","delFlag":"0"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2022-09-02 16:05:59
         * updateBy :
         * updateTime : null
         * remark : null
         * params : {}
         * id : 8
         * packageName : app.apk
         * version :
         * url : http://192.168.8.26:9001/lte-gz/2022/09/02/app_20220902160559A005.apk
         * issueType : 1
         * delFlag : 0
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private String id;
        private String packageName;
        private String version;
        private String url;
        private String issueType;
        private String delFlag;

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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
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

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIssueType() {
            return issueType;
        }

        public void setIssueType(String issueType) {
            this.issueType = issueType;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public static class ParamsBean {
        }
    }
}
