package com.e2open.entity;

/**
 * Created by abhishek on 12/15/16.
 */
public class QueueObject {

    private String orderid;
    private Long timeToWait;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Long getTimeToWait() {
        return timeToWait;
    }

    public void setTimeToWait(Long timeToWait) {
        this.timeToWait = timeToWait;
    }
}
