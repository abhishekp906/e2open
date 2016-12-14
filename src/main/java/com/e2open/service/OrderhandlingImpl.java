package com.e2open.service;

import com.e2open.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by abhishek on 12/14/16.
 */
public class OrderhandlingImpl implements OrderHandling {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public String placeOrder(Part part) {
        /**
         * TODO :Graph formation and checking logs with all dependent parts and alternate parts recursively
         */
        return part.getId();
    }

    @Override
    public Boolean available(Part part, int count) {
        if (part == null)
            return Boolean.FALSE;

        String partId = part.getSku();
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(partId));
        List<Part> partList= mongoTemplate.find(query, Part.class);

        if (partList != null && partList.size()>0){
            if (partList.size()>= count)
                return Boolean.TRUE;

        }

        /**
         * TODO: Trigger task for creating log
         */

        return Boolean.FALSE;

    }


}
