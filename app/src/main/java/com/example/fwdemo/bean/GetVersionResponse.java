package com.example.fwdemo.bean;

/**
 * Created by Administrator on 2018/4/9.
 * {"result":210,"error":"","message":"","version":"5.9.0","url":"http://192.168.1.236:8080/iDataService/qk365.apk"}
 */

public class GetVersionResponse extends BaseNullBean{

    private int result;
    private String error;
    private String message;
    private String version;
    private String url;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
