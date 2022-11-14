package com.leidi.lteapp.bean;

public class TaskNumBean {

    /**
     * msg : 鎿嶄綔鎴愬姛
     * code : 200
     * data : {"c":3,"t":1,"n":2}
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
         * c : 3
         * t : 1
         * n : 2
         */

        private int c;
        private int t;
        private int n;

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }
    }
}
