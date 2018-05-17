package com.zc.base.web.ext;

import java.util.Map;

public class ExtFormData {
    private boolean success;
    private Map<String, String> data;

    public ExtFormData() {
    }

    public ExtFormData(boolean success, Map<String, String> data) {
        this.success = success;
        this.data = data;
    }


    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
