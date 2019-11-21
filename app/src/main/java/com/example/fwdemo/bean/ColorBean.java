package com.example.fwdemo.bean;

/**
 * Created by qk14 on 2018/3/22.
 */

public class ColorBean {

    private String color;
    /**
     * state : false
     * totalSize : 0
     * success : false
     * message : 登陆成功
     * list : {"tag_id":"","tag":""}
     * data :
     * code : 1
     */

    private boolean state;
    private int totalSize;
    private boolean success;
    private String message;
    private ListBean list;
    private String data;
    private int code;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public static class ListBean {
        /**
         * tag_id :
         * tag :
         */

        private String tag_id;
        private String tag;

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
