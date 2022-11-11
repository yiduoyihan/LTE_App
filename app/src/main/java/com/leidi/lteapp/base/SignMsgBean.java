package com.leidi.lteapp.base;

public class SignMsgBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"zyuser","createTime":"2022-09-01 11:23:05","updateBy":"","updateTime":null,"remark":null,"params":{},"id":2,"userId":"110","userName":"zyuser","workStartTime":"2022-09-01 11:23:05","workEndTime":null,"deptId":"207","deptName":"电信","dwId":"209","dwName":"电信-单位1","bzId":"210","bzName":"电信-单位1-班组1","inAreaNo":null,"inAreaName":null,"outAreaNo":"","outAreaName":"陕西省西安市雁塔区鱼化寨街道天谷七路辅路"}
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
         * createBy : zyuser
         * createTime : 2022-09-01 11:23:05
         * updateBy :
         * updateTime : null
         * remark :备注
         * params : {}
         * id : 2
         * userId : 110
         * userName : zyuser
         * workStartTime : 2022-09-01 11:23:05
         * workEndTime : null
         * deptId : 207
         * deptName : 电信
         * dwId : 209
         * dwName : 电信-单位1
         * bzId : 210
         * bzName : 电信-单位1-班组1
         * inAreaNo : null
         * inAreaName : null
         * outAreaNo :
         * outAreaName : 陕西省西安市雁塔区鱼化寨街道天谷七路辅路
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String userId;
        private String userName;
        private String workStartTime;
        private Object workEndTime;
        private String deptId;
        private String deptName;
        private String dwId;
        private String dwName;
        private String bzId;
        private String bzName;
        private Object inAreaNo;
        private Object inAreaName;
        private String outAreaNo;
        private String outAreaName;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getWorkStartTime() {
            return workStartTime;
        }

        public void setWorkStartTime(String workStartTime) {
            this.workStartTime = workStartTime;
        }

        public Object getWorkEndTime() {
            return workEndTime;
        }

        public void setWorkEndTime(Object workEndTime) {
            this.workEndTime = workEndTime;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getDwId() {
            return dwId;
        }

        public void setDwId(String dwId) {
            this.dwId = dwId;
        }

        public String getDwName() {
            return dwName;
        }

        public void setDwName(String dwName) {
            this.dwName = dwName;
        }

        public String getBzId() {
            return bzId;
        }

        public void setBzId(String bzId) {
            this.bzId = bzId;
        }

        public String getBzName() {
            return bzName;
        }

        public void setBzName(String bzName) {
            this.bzName = bzName;
        }

        public Object getInAreaNo() {
            return inAreaNo;
        }

        public void setInAreaNo(Object inAreaNo) {
            this.inAreaNo = inAreaNo;
        }

        public Object getInAreaName() {
            return inAreaName;
        }

        public void setInAreaName(Object inAreaName) {
            this.inAreaName = inAreaName;
        }

        public String getOutAreaNo() {
            return outAreaNo;
        }

        public void setOutAreaNo(String outAreaNo) {
            this.outAreaNo = outAreaNo;
        }

        public String getOutAreaName() {
            return outAreaName;
        }

        public void setOutAreaName(String outAreaName) {
            this.outAreaName = outAreaName;
        }

        public static class ParamsBean {
        }
    }
}
