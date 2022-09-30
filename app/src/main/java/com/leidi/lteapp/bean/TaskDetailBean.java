package com.leidi.lteapp.bean;

import java.util.List;

public class TaskDetailBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"zyuser","createTime":"2022-08-26 15:20:39","updateBy":null,"updateTime":null,"remark":null,"params":{},"taskId":6,"taskNo":"1661498438676","taskName":"555555","taskContent":"66666","deptId":"207","deptName":"电信","dwId":"209","dwName":"电信-单位1","bzId":"210","bzName":"电信-单位1-班组1","createUserId":"110","taskStatus":"0","responseTimes":null,"handleTimes":null,"arriveTime":null,"completeTime":null,"endTime":null,"delFlag":"0","appLteTaskDetails":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":null,"taskNo":"1661498438676","arrivePosition":null,"arriveTime":null,"responseTimes":null,"completeTime":null,"handleTimes":null,"userId":null,"userName":null,"faultDes":null,"processDes":null,"deviceDes":null,"status":null,"lteTaskDetailsPics":null}}
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
         * createTime : 2022-08-26 15:20:39
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * taskId : 6
         * taskNo : 1661498438676
         * taskName : 555555
         * taskContent : 66666
         * deptId : 207
         * deptName : 电信
         * dwId : 209
         * dwName : 电信-单位1
         * bzId : 210
         * bzName : 电信-单位1-班组1
         * createUserId : 110
         * taskStatus : 0
         * responseTimes : null
         * handleTimes : null
         * arriveTime : null
         * completeTime : null
         * endTime : null
         * delFlag : 0
         * appLteTaskDetails : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":null,"taskNo":"1661498438676","arrivePosition":null,"arriveTime":null,"responseTimes":null,"completeTime":null,"handleTimes":null,"userId":null,"userName":null,"faultDes":null,"processDes":null,"deviceDes":null,"status":null,"lteTaskDetailsPics":null}
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
        private String responseTimes;
        private String handleTimes;
        private String arriveTime;
        private String completeTime;
        private String endTime;
        private String delFlag;
        private AppLteTaskDetailsBean appLteTaskDetails;

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

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public AppLteTaskDetailsBean getAppLteTaskDetails() {
            return appLteTaskDetails;
        }

        public void setAppLteTaskDetails(AppLteTaskDetailsBean appLteTaskDetails) {
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
             * id : null
             * taskNo : 1661498438676
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

            private String searchValue;
            private String createBy;
            private String createTime;
            private String updateBy;
            private String updateTime;
            private String remark;
            private ParamsBeanX params;
            private String id;
            private String taskNo;
            private String arrivePosition;
            private String arriveTime;
            private String responseTimes;
            private String completeTime;
            private String handleTimes;
            private String userId;
            private String userName;
            private String faultDes;
            private String processDes;
            private String deviceDes;
            // status 1标示点过了到达现场
            private String status;
            private List<PictureData> lteTaskDetailsPics;

            public List<PictureData> getLteTaskDetailsPics() {
                return lteTaskDetailsPics;
            }

            public void setLteTaskDetailsPics(List<PictureData> lteTaskDetailsPics) {
                this.lteTaskDetailsPics = lteTaskDetailsPics;
            }

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getResponseTimes() {
                return responseTimes;
            }

            public void setResponseTimes(String responseTimes) {
                this.responseTimes = responseTimes;
            }

            public String getCompleteTime() {
                return completeTime;
            }

            public void setCompleteTime(String completeTime) {
                this.completeTime = completeTime;
            }

            public String getHandleTimes() {
                return handleTimes;
            }

            public void setHandleTimes(String handleTimes) {
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


            public static class ParamsBeanX {
            }
        }

        public static class PictureData {

            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 6
             * taskNo : 1663312956800
             * detailsId : 1570674516901326849
             * url : http://192.168.8.26:9001/lte-gz/2022/09/16/Screenshot_20220726-104904_20220916152435A001.jpeg
             * urlName : Screenshot_20220726-104904.jpeg
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int id;
            private String taskNo;
            private String detailsId;
            private String url;
            private String urlName;

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
