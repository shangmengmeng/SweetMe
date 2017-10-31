package com.example.administrator.sweetme.bean;

/**
 * Created by FancyMenG on 2017/10/11.
 */

public class heh {

    /**
     * data : {"exception":"java.lang.IllegalArgumentException","message":"No converter found for return value of type: class com.wewell.jntfsj.util.Json"}
     * code : 504
     * info : No converter found for return value of type: class com.wewell.jntfsj.util.Json
     */

    private DataBean data;
    private int code;
    private String info;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static class DataBean {
        /**
         * exception : java.lang.IllegalArgumentException
         * message : No converter found for return value of type: class com.wewell.jntfsj.util.Json
         */

        private String exception;
        private String message;

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
