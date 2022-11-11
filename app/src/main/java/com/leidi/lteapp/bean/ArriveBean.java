package com.leidi.lteapp.bean;

public class ArriveBean {

    /**
     * msg : 鎿嶄綔鎴愬姛
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1590633921415557122,"taskNo":"DX1_221110001","arrivePosition":"番禺广场至南村万博区间左线rru-A08","arriveTime":"2022-11-10 17:14:14","responseTimes":101,"completeTime":null,"completePosition":null,"handleTimes":null,"userId":"139","userName":"a","faultDes":null,"processDes":null,"deviceDes":null,"status":"1","lteTaskDetailsPics":null}
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
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1590633921415557122
         * taskNo : DX1_221110001
         * arrivePosition : 番禺广场至南村万博区间左线rru-A08
         * arriveTime : 2022-11-10 17:14:14
         * responseTimes : 101
         * completeTime : null
         * completePosition : null
         * handleTimes : null
         * userId : 139
         * userName : a
         * faultDes : null
         * processDes : null
         * deviceDes : null
         * status : 1
         * lteTaskDetailsPics : null
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private long id;
        private String taskNo;
        private String arrivePosition;
        private String arriveTime;
        private int responseTimes;
        private Object completeTime;
        private Object completePosition;
        private Object handleTimes;
        private String userId;
        private String userName;
        private Object faultDes;
        private Object processDes;
        private Object deviceDes;
        private String status;
        private Object lteTaskDetailsPics;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
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

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTaskNo() {
            return taskNo;
        }

        public void setTaskNo(String taskNo) {
            this.taskNo = taskNo;
        }

        public String getArrivePosition() {
            return arrivePosition;
        }

        public void setArrivePosition(String arrivePosition) {
            this.arrivePosition = arrivePosition;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public int getResponseTimes() {
            return responseTimes;
        }

        public void setResponseTimes(int responseTimes) {
            this.responseTimes = responseTimes;
        }

        public Object getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(Object completeTime) {
            this.completeTime = completeTime;
        }

        public Object getCompletePosition() {
            return completePosition;
        }

        public void setCompletePosition(Object completePosition) {
            this.completePosition = completePosition;
        }

        public Object getHandleTimes() {
            return handleTimes;
        }

        public void setHandleTimes(Object handleTimes) {
            this.handleTimes = handleTimes;
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

        public Object getFaultDes() {
            return faultDes;
        }

        public void setFaultDes(Object faultDes) {
            this.faultDes = faultDes;
        }

        public Object getProcessDes() {
            return processDes;
        }

        public void setProcessDes(Object processDes) {
            this.processDes = processDes;
        }

        public Object getDeviceDes() {
            return deviceDes;
        }

        public void setDeviceDes(Object deviceDes) {
            this.deviceDes = deviceDes;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getLteTaskDetailsPics() {
            return lteTaskDetailsPics;
        }

        public void setLteTaskDetailsPics(Object lteTaskDetailsPics) {
            this.lteTaskDetailsPics = lteTaskDetailsPics;
        }

        public static class ParamsBean {
        }
    }
}
