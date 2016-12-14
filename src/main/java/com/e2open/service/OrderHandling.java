package com.e2open.service;

import com.e2open.entity.Part;

/**
 * Created by abhishek on 12/14/16.
 */
public interface OrderHandling {

    String placeOrder(Part part);
    Boolean available(Part part, Long count);
}
