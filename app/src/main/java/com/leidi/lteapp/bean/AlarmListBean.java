package com.leidi.lteapp.bean;

import java.util.List;

public class AlarmListBean {

    /**
     * total : 1
     * rows : [{"id":1,"occurTime":"2022-08-27 13:47:57","deviceName":"告警设备","alarmLevel":"1","devLocation":"告警位置","alarmCause":"告警信息","alarmRestore":"1","restoreTime":"2022-08-27 13:48:14","alarmConfirm":"1","confirmTime":"2022-08-27 13:48:18"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

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
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * occurTime : 2022-08-27 13:47:57
         * deviceName : 告警设备
         * alarmLevel : 1
         * devLocation : 告警位置
         * alarmCause : 告警信息
         * alarmRestore : 1
         * restoreTime : 2022-08-27 13:48:14
         * alarmConfirm : 1
         * confirmTime : 2022-08-27 13:48:18
         */

        private int id;
        private String occurTime;
        private String deviceName;
        private String alarmLevel;
        private String devLocation;
        private String alarmCause;
        private String alarmRestore;
        private String restoreTime;
        private String alarmConfirm;
        private String confirmTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOccurTime() {
            return occurTime;
        }

        public void setOccurTime(String occurTime) {
            this.occurTime = occurTime;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getAlarmLevel() {
            return alarmLevel;
        }

        public void setAlarmLevel(String alarmLevel) {
            this.alarmLevel = alarmLevel;
        }

        public String getDevLocation() {
            return devLocation;
        }

        public void setDevLocation(String devLocation) {
            this.devLocation = devLocation;
        }

        public String getAlarmCause() {
            return alarmCause;
        }

        public void setAlarmCause(String alarmCause) {
            this.alarmCause = alarmCause;
        }

        public String getAlarmRestore() {
            return alarmRestore;
        }

        public void setAlarmRestore(String alarmRestore) {
            this.alarmRestore = alarmRestore;
        }

        public String getRestoreTime() {
            return restoreTime;
        }

        public void setRestoreTime(String restoreTime) {
            this.restoreTime = restoreTime;
        }

        public String getAlarmConfirm() {
            return alarmConfirm;
        }

        public void setAlarmConfirm(String alarmConfirm) {
            this.alarmConfirm = alarmConfirm;
        }

        public String getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            this.confirmTime = confirmTime;
        }
    }
}
