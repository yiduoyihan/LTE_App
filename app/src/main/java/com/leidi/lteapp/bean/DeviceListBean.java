package com.leidi.lteapp.bean;

import java.util.List;

public class DeviceListBean {


    /**
     * total : 1
     * rows : [{"id":1,"equipName":"短号","deviceIp":"192.168.1.1","deviceType":"1","devLocation":"设备位置1","connectStatus":"0"}]
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
         * equipName : 短号
         * deviceIp : 192.168.1.1
         * deviceType : 1
         * devLocation : 设备位置1
         * connectStatus : 0
         * (0 下线， 1上线)
         */


        private String equipName;
        private String deviceIp;
        private String deviceType;
        private String devLocation;
        private String connectStatus;

        public String getEquipName() {
            return equipName;
        }

        public void setEquipName(String equipName) {
            this.equipName = equipName;
        }

        public String getDeviceIp() {
            return deviceIp;
        }

        public void setDeviceIp(String deviceIp) {
            this.deviceIp = deviceIp;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getDevLocation() {
            return devLocation;
        }

        public void setDevLocation(String devLocation) {
            this.devLocation = devLocation;
        }

        public String getConnectStatus() {
            return connectStatus;
        }

        public void setConnectStatus(String connectStatus) {
            this.connectStatus = connectStatus;
        }
    }
}
