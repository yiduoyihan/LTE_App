package com.leidi.lteapp.bean;

import java.util.List;

public class TaskDetailBean {

    /**
     * msg : 鎿嶄綔鎴愬姛
     * code : 200
     * data : {"searchValue":null,"createBy":"a","createTime":"2022-11-10 13:44:19","updateBy":null,"updateTime":null,"remark":null,"params":{},"taskId":74,"taskNo":"DX1_221110001","taskName":"fhhb","taskContent":"gghhh","deptId":"240","deptName":"电信1","dwId":"241","dwName":"LD","bzId":"242","bzName":"TEST","createUserId":"139","taskStatus":"0","taskLimited":null,"responseTimes":null,"handleTimes":null,"arriveTime":null,"completeTime":null,"endTime":null,"taskType":"4","planCompleteStartTime":null,"planCompleteEndTime":null,"createPosition":null,"createPositionNo":null,"endPosition":null,"delFlag":"0","appLteTaskDetails":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1590582575656177666,"taskNo":"DX1_221110001","arrivePosition":"番禺广场至南村万博区间左线rru-A08","arriveTime":"2022-11-10 12:50:12","responseTimes":5,"completeTime":"2022-11-10 14:24:49","handleTimes":10,"userId":"139","userName":"a","faultDes":"1","processDes":"1","deviceDes":"1","status":"2","lteTaskDetailsPics":[]},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1590582575656177999,"taskNo":"DX1_221110001","arrivePosition":"番禺广场至南村万博区间左线rru-A08","arriveTime":"2022-11-10 13:50:12","responseTimes":5,"completeTime":null,"handleTimes":null,"userId":"139","userName":"a","faultDes":null,"processDes":null,"deviceDes":null,"status":"1","lteTaskDetailsPics":null}]}
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
         * appLteTaskDetails : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1590582575656177666,"taskNo":"DX1_221110001","arrivePosition":"番禺广场至南村万博区间左线rru-A08","arriveTime":"2022-11-10 12:50:12","responseTimes":5,"completeTime":"2022-11-10 14:24:49","handleTimes":10,"userId":"139","userName":"a","faultDes":"1","processDes":"1","deviceDes":"1","status":"2","lteTaskDetailsPics":[]},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1590582575656177999,"taskNo":"DX1_221110001","arrivePosition":"番禺广场至南村万博区间左线rru-A08","arriveTime":"2022-11-10 13:50:12","responseTimes":5,"completeTime":null,"handleTimes":null,"userId":"139","userName":"a","faultDes":null,"processDes":null,"deviceDes":null,"status":"1","lteTaskDetailsPics":null}]
         */

        private String searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
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
        private String taskLimited;
        private String responseTimes;
        private String handleTimes;
        private String arriveTime;
        private String completeTime;
        private String endTime;
        private String taskType;
        private String planCompleteStartTime;
        private String planCompleteEndTime;
        private String createPosition;
        private String createPositionNo;
        private String endPosition;
        private String delFlag;
        private List<AppLteTaskDetailsBean> appLteTaskDetails;

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public String getTaskLimited() {
            return taskLimited;
        }

        public void setTaskLimited(String taskLimited) {
            this.taskLimited = taskLimited;
        }

        public String getResponseTimes() {
            return responseTimes;
        }

        public void setResponseTimes(String responseTimes) {
            this.responseTimes = responseTimes;
        }

        public String getHandleTimes() {
            return handleTimes;
        }

        public void setHandleTimes(String handleTimes) {
            this.handleTimes = handleTimes;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getCompleteTime() {
            return completeTime;
        }

        public void setCompleteTime(String completeTime) {
            this.completeTime = completeTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTaskType() {
            return taskType;
        }

        public void setTaskType(String taskType) {
            this.taskType = taskType;
        }

        public String getPlanCompleteStartTime() {
            return planCompleteStartTime;
        }

        public void setPlanCompleteStartTime(String planCompleteStartTime) {
            this.planCompleteStartTime = planCompleteStartTime;
        }

        public String getPlanCompleteEndTime() {
            return planCompleteEndTime;
        }

        public void setPlanCompleteEndTime(String planCompleteEndTime) {
            this.planCompleteEndTime = planCompleteEndTime;
        }

        public String getCreatePosition() {
            return createPosition;
        }

        public void setCreatePosition(String createPosition) {
            this.createPosition = createPosition;
        }

        public String getCreatePositionNo() {
            return createPositionNo;
        }

        public void setCreatePositionNo(String createPositionNo) {
            this.createPositionNo = createPositionNo;
        }

        public String getEndPosition() {
            return endPosition;
        }

        public void setEndPosition(String endPosition) {
            this.endPosition = endPosition;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public List<AppLteTaskDetailsBean> getAppLteTaskDetails() {
            return appLteTaskDetails;
        }

        public void setAppLteTaskDetails(List<AppLteTaskDetailsBean> appLteTaskDetails) {
            this.appLteTaskDetails = appLteTaskDetails;
        }

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
             * id : 1590582575656177666
             * taskNo : DX1_221110001
             * arrivePosition : 番禺广场至南村万博区间左线rru-A08
             * arriveTime : 2022-11-10 12:50:12
             * responseTimes : 5
             * completeTime : 2022-11-10 14:24:49
             * handleTimes : 10
             * userId : 139
             * userName : a
             * faultDes : 1
             * processDes : 1
             * deviceDes : 1
             * status : 2
             * lteTaskDetailsPics : []
             */

            private String searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private String remark;
            private ParamsBeanX params;
            private long id;
            private String taskNo;
            private String arrivePosition;
            private String arriveTime;
            private int responseTimes;
            private String completeTime;
            private int handleTimes;
            private String userId;
            private String userName;
            private String faultDes;
            private String processDes;
            private String deviceDes;
            private String status;
            private List<LteTaskDetailsPicsBean> lteTaskDetailsPics;

            public String getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(String searchValue) {
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public String getCompleteTime() {
                return completeTime;
            }

            public void setCompleteTime(String completeTime) {
                this.completeTime = completeTime;
            }

            public int getHandleTimes() {
                return handleTimes;
            }

            public void setHandleTimes(int handleTimes) {
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

            public String getFaultDes() {
                return faultDes;
            }

            public void setFaultDes(String faultDes) {
                this.faultDes = faultDes;
            }

            public String getProcessDes() {
                return processDes;
            }

            public void setProcessDes(String processDes) {
                this.processDes = processDes;
            }

            public String getDeviceDes() {
                return deviceDes;
            }

            public void setDeviceDes(String deviceDes) {
                this.deviceDes = deviceDes;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<LteTaskDetailsPicsBean> getLteTaskDetailsPics() {
                return lteTaskDetailsPics;
            }

            public void setLteTaskDetailsPics(List<LteTaskDetailsPicsBean> lteTaskDetailsPics) {
                this.lteTaskDetailsPics = lteTaskDetailsPics;
            }

            public static class ParamsBeanX {
            }
        }
        public static class LteTaskDetailsPicsBean{

            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 19
             * taskNo : DX1_221110001
             * detailsId : 1590582575656177999
             * url : http://192.168.8.26:9001/lte-gz/2022/11/10/JPEG_20211122_143622_20221110165835A001.jpeg
             * urlName : JPEG_20211122_143622.jpeg
             */

            private String searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private String remark;
            private ParamsBean params;
            private int id;
            private String taskNo;
            private String detailsId;
            private String url;
            private String urlName;

            public String getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(String searchValue) {
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

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
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

            public String getTaskNo() {
                return taskNo;
            }

            public void setTaskNo(String taskNo) {
                this.taskNo = taskNo;
            }

            public String getDetailsId() {
                return detailsId;
            }

            public void setDetailsId(String detailsId) {
                this.detailsId = detailsId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrlName() {
                return urlName;
            }

            public void setUrlName(String urlName) {
                this.urlName = urlName;
            }

            public static class ParamsBean {
            }
        }
    }
}
