package com.leidi.lteapp.bean;

import java.util.List;

public class TaskListBean {

    /**
     * msg : 鎿嶄綔鎴愬姛
     * code : 200
     * data : [{"searchValue":null,"createBy":"a","createTime":"2022-11-10 13:44:19","updateBy":null,"updateTime":null,"remark":null,"params":{},"taskId":74,"taskNo":"DX1_221110001","taskName":"fhhb","taskContent":"gghhh","deptId":"240","deptName":"电信1","dwId":"241","dwName":"LD","bzId":"242","bzName":"TEST","createUserId":"139","taskStatus":"0","taskLimited":null,"responseTimes":null,"handleTimes":null,"arriveTime":null,"completeTime":null,"endTime":null,"taskType":"4","planCompleteStartTime":null,"planCompleteEndTime":null,"createPosition":null,"createPositionNo":null,"endPosition":null,"delFlag":"0","appLteTaskDetails":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":null,"taskNo":"DX1_221110001","arrivePosition":null,"arriveTime":null,"responseTimes":null,"completeTime":null,"handleTimes":null,"userId":null,"userName":null,"faultDes":null,"processDes":null,"deviceDes":null,"status":null,"lteTaskDetailsPics":null}]}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : a
         * createTime : 2022-11-10 13:44:19
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * taskId : 74
         * taskNo : DX1_221110001
         * taskName : fhhb
         * taskContent : gghhh
         * deptId : 240
         * deptName : 电信1
         * dwId : 241
         * dwName : LD
         * bzId : 242
         * bzName : TEST
         * createUserId : 139
         * taskStatus : 0
         * taskLimited : null
         * responseTimes : null
         * handleTimes : null
         * arriveTime : null
         * completeTime : null
         * endTime : null
         * taskType : 4
         * planCompleteStartTime : null
         * planCompleteEndTime : null
         * createPosition : null
         * createPositionNo : null
         * endPosition : null
         * delFlag : 0
         * appLteTaskDetails : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":null,"taskNo":"DX1_221110001","arrivePosition":null,"arriveTime":null,"responseTimes":null,"completeTime":null,"handleTimes":null,"userId":null,"userName":null,"faultDes":null,"processDes":null,"deviceDes":null,"status":null,"lteTaskDetailsPics":null}]
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int taskId;
        private String taskNo;
        private String taskName;
        private String taskContent;
        private String deptId;
        private String deptName;
        private String dwId;
        private String dwName;
        private String bzId;
        private String bzName;
        private String createUserId;
        private String taskStatus;
        private Object taskLimited;
        private Object responseTimes;
        private Object handleTimes;
        private Object arriveTime;
        private Object completeTime;
        private Object endTime;
        private String taskType;
        private Object planCompleteStartTime;
        private Object planCompleteEndTime;
        private Object createPosition;
        private Object createPositionNo;
        private Object endPosition;
        private String delFlag;
        private String timeout;

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }
        //        private List<AppLteTaskDetailsBean> appLteTaskDetails;

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

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskNo() {
            return taskNo;
        }

        public void setTaskNo(String taskNo) {
            this.taskNo = taskNo;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskContent() {
            return taskContent;
        }

        public void setTaskContent(String taskContent) {
            this.taskContent = taskContent;
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

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }

        public Object getTaskLimited() {
            return taskLimited;
        }

        public void setTaskLimited(Object taskLimited) {
            this.taskLimited = taskLimited;
        }

        public Object getResponseTimes() {
            return responseTimes;
        }

        public void setResponseTimes(Object responseTimes) {
            this.responseTimes = responseTimes;
        }

        public Object getHandleTimes() {
            return handleTimes;
        }

        public void setHandleTimes(Object handleTimes) {
            this.handleTimes = handleTimes;
        }

        public Object getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(Object arriveTime) {
            this.arriveTime = arriveTime;
        }

        public Object getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(Object completeTime) {
            this.completeTime = completeTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public Object getPlanCompleteStartTime() {
            return planCompleteStartTime;
        }

        public void setPlanCompleteStartTime(Object planCompleteStartTime) {
            this.planCompleteStartTime = planCompleteStartTime;
        }

        public Object getPlanCompleteEndTime() {
            return planCompleteEndTime;
        }

        public void setPlanCompleteEndTime(Object planCompleteEndTime) {
            this.planCompleteEndTime = planCompleteEndTime;
        }

        public Object getCreatePosition() {
            return createPosition;
        }

        public void setCreatePosition(Object createPosition) {
            this.createPosition = createPosition;
        }

        public Object getCreatePositionNo() {
            return createPositionNo;
        }

        public void setCreatePositionNo(Object createPositionNo) {
            this.createPositionNo = createPositionNo;
        }

        public Object getEndPosition() {
            return endPosition;
        }

        public void setEndPosition(Object endPosition) {
            this.endPosition = endPosition;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

//        public List<AppLteTaskDetailsBean> getAppLteTaskDetails() {
//            return appLteTaskDetails;
//        }
//
//        public void setAppLteTaskDetails(List<AppLteTaskDetailsBean> appLteTaskDetails) {
//            this.appLteTaskDetails = appLteTaskDetails;
//        }

        public static class ParamsBean {
        }

        public static class AppLteTaskDetailsBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : null
             * taskNo : DX1_221110001
             * arrivePosition : null
             * arriveTime : null
             * responseTimes : null
             * completeTime : null
             * handleTimes : null
             * userId : null
             * userName : null
             * faultDes : null
             * processDes : null
             * deviceDes : null
             * status : null
             * lteTaskDetailsPics : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
            private Object id;
            private String taskNo;
            private Object arrivePosition;
            private Object arriveTime;
            private Object responseTimes;
            private Object completeTime;
            private Object handleTimes;
            private Object userId;
            private Object userName;
            private Object faultDes;
            private Object processDes;
            private Object deviceDes;
            private Object status;
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
                this.params = params;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getTaskNo() {
                return taskNo;
            }

            public void setTaskNo(String taskNo) {
                this.taskNo = taskNo;
            }

            public Object getArrivePosition() {
                return arrivePosition;
            }

            public void setArrivePosition(Object arrivePosition) {
                this.arrivePosition = arrivePosition;
            }

            public Object getArriveTime() {
                return arriveTime;
            }

            public void setArriveTime(Object arriveTime) {
                this.arriveTime = arriveTime;
            }

            public Object getResponseTimes() {
                return responseTimes;
            }

            public void setResponseTimes(Object responseTimes) {
                this.responseTimes = responseTimes;
            }

            public Object getCompleteTime() {
                return completeTime;
            }

            public void setCompleteTime(Object completeTime) {
                this.completeTime = completeTime;
            }

            public Object getHandleTimes() {
                return handleTimes;
            }

            public void setHandleTimes(Object handleTimes) {
                this.handleTimes = handleTimes;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getLteTaskDetailsPics() {
                return lteTaskDetailsPics;
            }

            public void setLteTaskDetailsPics(Object lteTaskDetailsPics) {
                this.lteTaskDetailsPics = lteTaskDetailsPics;
            }

            public static class ParamsBeanX {
            }
        }
    }
}
