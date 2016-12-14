package com.e2open.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by abhishek on 12/13/16.
 */
public class BaseEntity {

    @Id
    private String id;
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
