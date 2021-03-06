package com.e2open.service;

import com.e2open.common.Constants;
import com.e2open.common.cache.RedisServiceManager;
import com.e2open.entity.ChildPart;
import com.e2open.entity.OrderTransaction;
import com.e2open.entity.Part;
import com.e2open.entity.QueueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by abhishek on 12/14/16.
 */
public class OrderhandlingImpl implements OrderHandling {
    @Autowired
    MongoTemplate mongoTemplate;

    RedisServiceManager redisServiceManager;

    public static Queue minQueue;
    @Override
    public String placeOrder(Part part, String priority, String orderId) {
        String partId = part.getSku();
        Long maxTime=0L ;
        Long minTime = 0L;
        String orderLogHistoryId = null;
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(partId));
        List<Part> partList= mongoTemplate.find(query, Part.class);
        if (partList != null && partList.size()>0) {
            if (partList.get(0).getQuantity()>0) {
                //place order successful
            }
        }
        else{
            Part p = partList.get(0);
            List<ChildPart> childParts = p.getChildParts();
            Boolean isAvailable = Boolean.FALSE;
            for (ChildPart pat: childParts){
                Part childpart = getpartById(pat.getPartId());
                isAvailable = available(childpart, p.getQuantity());
                Long max = getMaximumDelayTime(partList.get(0));
                Long min = getMinimumDelayTime(partList.get(0));

                maxTime = max;
                minTime = min;
                /**
                 * make async call for fulfilment of quantity
                 */

                break;
            }
            if(isAvailable){
                /**
                 * order successfully fulfilled order
                 * trigger the delivery flow &
                 * payment capture flow
                 */
            } else{
                if (Constants.OrderPriority.P1.getValue().equalsIgnoreCase(priority)){
                    /**
                     * reset next lookup , time-based min heap
                     * put the updated status into the cache to avoid next latency to read from table
                     */
                 part.setNextLookUpTime(new Date(part.getMaxTimeToMakeAvailable()+ minTime));
                 mongoTemplate.save(part);
                    orderLogHistoryId =logHistoryTable(part, orderId, priority);
                }else if (Constants.OrderPriority.P2.getValue().equalsIgnoreCase(priority)){
                    part.setNextLookUpTime(new Date(part.getMaxTimeToMakeAvailable()+ maxTime));
                    mongoTemplate.save(part);
                    orderLogHistoryId =logHistoryTable(part, orderId, priority);
                }else{
                    orderLogHistoryId =logHistoryTable(part, orderId, priority);
                }
            }
            return orderLogHistoryId;
        }



        return null;

    }

    public Boolean available(Part part, Long count) {
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

    public long getMaximumDelayTime(Part part){
        Long max = Long.MAX_VALUE;

        List<ChildPart> childParts = part.getChildParts();
        for (ChildPart childPart: childParts){
            Part pat = getpartById(childPart.getPartId());
            Long temp = getMaximumDelayTime(pat);
            if (max < temp)
                max = temp;
        }

        return max;
    }

    public long getMinimumDelayTime(Part part){
        Long min = Long.MIN_VALUE;

        List<ChildPart> childParts = part.getChildParts();
        for (ChildPart childPart: childParts){
            Part pat = getpartById(childPart.getPartId());
            Long temp = getMaximumDelayTime(pat);
            if (min > temp)
                min = temp;
        }
        return min;
    }


    public Part getpartById(String partId){
        if (redisServiceManager.get(partId) != null){
             redisServiceManager.get(partId);
            // need to make object serialize
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(partId));
        List<Part> partList= mongoTemplate.find(query, Part.class);
        return partList != null && partList.size()>0 ? partList.get(0): null;
    }

    public String logHistoryTable(Part part,String orderId,String priority){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(orderId));
        String id = null;
        List<OrderTransaction> orderTransactionList= mongoTemplate.find(query, OrderTransaction.class);
        if (orderTransactionList != null && orderTransactionList.size()>0){
            OrderTransaction transaction = orderTransactionList.get(0);
            transaction.setProductId(part.getId());
            transaction.setSkuId(part.getSku());
            transaction.setCreateDate(part.getNextLookUpTime());
            mongoTemplate.save(transaction);
            id = transaction.getId();
        }else{
            OrderTransaction transaction = new OrderTransaction();
            transaction.setProductId(part.getId());
            transaction.setSkuId(part.getSku());
            transaction.setCreateDate(part.getNextLookUpTime());
            mongoTemplate.save(transaction);
            id = transaction.getId();
        }
        updateMinQueue(orderTransactionList.get(0).getId());

        return id;
    }

    public Queue updateMinQueue(String transationId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(transationId));
        String id = null;
        List<OrderTransaction> orderTransactionList= mongoTemplate.find(query, OrderTransaction.class);
        if (orderTransactionList != null && orderTransactionList.size()>0){
            if (minQueue == null)
                minQueue = new PriorityQueue();
            QueueObject object = new QueueObject();
            object.setOrderid(orderTransactionList.get(0).getId());
            object.setTimeToWait(orderTransactionList.get(0).getCreateDate().getTime());  // modifying time although not good to change createDate
           if (minQueue.contains(object)){
                // heapify  in memory
           }else{
             minQueue.add(object);
               // heapify  in memory
           }
        }
        return minQueue;
    }
}
