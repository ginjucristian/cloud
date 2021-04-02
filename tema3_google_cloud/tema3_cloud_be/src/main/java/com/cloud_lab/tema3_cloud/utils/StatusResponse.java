package com.cloud_lab.tema3_cloud.utils;

public class StatusResponse {
    private Boolean status;

    public StatusResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
