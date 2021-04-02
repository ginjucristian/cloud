package com.cloud_lab.tema3_cloud.utils;

public class ResponseWrapper<Type> {

    private Type data;

    private String status;

    public ResponseWrapper(Type data, String status) {
        this.data = data;
        this.status = status;
    }

    public Type getData() {
        return data;
    }

    public void setData(Type data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
