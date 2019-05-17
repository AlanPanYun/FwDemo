package com.example.fwdemo.bean.request;

import com.example.fwdemo.bean.BaseNullBean;

/**
 * @author Alan
 * @date 2018/7/19
 */
public class GetVersionRequest extends BaseNullBean {

    private String version;
    private String platform;

    public GetVersionRequest(String version, String platform) {
        this.version = version;
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
