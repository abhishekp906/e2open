package com.e2open.common;

import java.io.Serializable;

/**
 * Created by abhishek on 9/21/16.
 */
public class ResponseObject implements Serializable {

    public Boolean status;
    public String reason;
    public Serializable response;

    public ResponseObject() {
    }

    public ResponseObject(Boolean status, String reason, Serializable response) {
        this.status = status;
        this.reason = reason;
        this.response = response;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Serializable getResponse() {
        return response;
    }

    public void setResponse(Serializable response) {
        this.response = response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseObject{");
        sb.append("status=").append(status);
        sb.append(", reason='").append(reason).append('\'');
        sb.append(", response=").append(response);
        sb.append('}');
        return sb.toString();
    }
}
