package com.e2open.common;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public enum UserType{
        USER("USER"),
        CURATOR("CURATOR"),
        ADMIN("ADMIN");

        public String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        UserType(String value) {
            this.value = value;
        }
    }

    public enum OrderPriority{
        P1("P1"),
        P2("P2");

        public String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        OrderPriority(String value) {
            this.value = value;
        }
    }


    public enum PaymentStatus{
        INITIATED("INITIATED"),
        FAILED("FAILED"),
        SUCCESS("SUCCESS"),
        PENDING_VERFICATION("PENDING_VERFICATION"),
        ABANDONED("ABANDONED");

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        PaymentStatus(String value){
            this.value = value;
        }

    }

    public enum ECommerceEarningType {
        BONUS,
        ORDER,
        ORDER_REFERRAL,
        VISITS;
    }

    public class Entity{
        private Long id;
        private String type;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


    public enum NotificationSchedule{
        ONE_TIME("one_time");

        NotificationSchedule(String schedule){
        this.schedule = schedule;
    }

        private String schedule;

        public String getSchedule() {
        return schedule;
    }
    }

public enum NotificationEvent{
    PRODUCT_DISCOUNT {
        @Override
        protected String getSchedule() {
            return NotificationSchedule.ONE_TIME.getSchedule();
        }

        @Override
        protected String getTemplate() {
            return null;
        }

        @Override
        public void setEntity(Entity entity) {
            this.entity = entity;
        }

    }, PRODUCT_BACK_IN_STOCK {
        @Override
        protected String getSchedule() {
            return NotificationSchedule.ONE_TIME.getSchedule();
        }

        @Override
        protected String getTemplate() {
            return null;
        }

        @Override
        public void setEntity(Entity entity) {
            this.entity = entity;
        }
    };

    protected Entity entity;
    protected abstract String getSchedule();
    protected abstract String getTemplate();
    public Entity getEntity(){
        return entity;
    }
    public abstract void setEntity(Entity entity);

    public static NotificationEvent getNotificationEvent(String notificationEventName){
        for(NotificationEvent notificationEvent: Arrays.asList(NotificationEvent.values())){
            if(notificationEvent.toString().equals(notificationEventName))
                return notificationEvent;
        }
        return null;
    }

    public class Entity{
        private Long id;
        private String type;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    }


    public enum OrderStatus {
        UNCONFIRMED("UNCONFIRMED") {
            @Override
            public String getDisplayOrderStatus() { return "PLACED";}

        },
        CONFIRMED("CONFIRMED"),
        REJECTED("REJECTED") {
            @Override
            public String getDisplayOrderStatus() { return "REJECTED";}
        },
        CANCELLED("CANCELLED"),
        SHIPPED("SHIPPED"),
        COMPLETED("COMPLETED") {
            @Override
            public String getDisplayOrderStatus() { return "DELIVERED";}

        },
        RETURNED("RETURNED"),
        FAILED("FAILED"),
        ABANDONED("ABANDONED");
        /**
         * TODO: IMPORTANT If any status is added check the Payment captureTask to Capture Payment logic
         * TODO: AND Check updateTransactionOrder method
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        OrderStatus(String value){
            this.value = value;
        }

        private static Map<String, OrderStatus> map = new HashMap<String, OrderStatus>();
        static {
            for(OrderStatus orderStatus : OrderStatus.values()){
                map.put(orderStatus.getValue(), orderStatus);
            }
        }

        public static OrderStatus getOrderStatus(String orderStatusString){
            if(orderStatusString != null){

                return map.get(orderStatusString);
            }

            return null;
        }
        public String getDisplayOrderStatus(){
            return this.value;
        }

    }

    public enum DerivedOrderStatus {

        PLACED(OrderStatus.UNCONFIRMED, "PLACED", "Placed", "Wuhoo, your order has been placed on Wooplr! :D"),
        REJECTED(OrderStatus.REJECTED, "REJECTED", "Cancelled", "Order Product is Out Stock"),
        CANCELLED(OrderStatus.CANCELLED, "CANCELLED", "Cancelled", "We are sad to see that you have cancelled your order with us :("),
        CONFIRMED(OrderStatus.CONFIRMED, "CONFIRMED", "Confirmed", "Yippee, your order has been confirmed and we will be processing it shortly! :)"),
        QUALITY_CHECK(OrderStatus.CONFIRMED, "QUALITY_CHECK", "Quality Check", "Your order is going through our Quality Control process so that you receive nothing but the best :)"),
        READY_TO_PICK(OrderStatus.CONFIRMED, "READY_TO_PICK", "Ready to pick", "Your order is all packed and ready to be shipped. Our delivery agent will pick it up shortly! :)"),
        SHIPPED(OrderStatus.SHIPPED, "SHIPPED", "Shipped", "Our delivery agent has picked up your order and you will receive it shortly! :)"),
        DISPATCHED_FROM_ORIGIN_CITY(OrderStatus.SHIPPED, "DISPATCHED_FROM_ORIGIN_CITY", "Dispatched from origin city", "Your product just left origin city, it will reach your city  shortly! :D"),
        REACHED_DESTINATION_CITY(OrderStatus.SHIPPED, "REACHED_DESTINATION_CITY", "Reached destination city", "Your product just arrived in your city, it will soon be on its way to you! :D"),
        OUT_FOR_DELIVERY(OrderStatus.SHIPPED, "OUT_FOR_DELIVERY", "Out for delivery", "It’s time to do the happy dance, your product is out for delivery and should reach you shortly! \n" +
                "Woop!"),
        DELIVERY_ATTEMPT_FAILED(OrderStatus.SHIPPED, "DELIVERY_ATTEMPT_FAILED", "Delivery attempt failed", "We tried delivering your order but couldn't do as we were unable to reach you! :("),
        DELIVERED(OrderStatus.COMPLETED, "DELIVERED", "Delivered", "Wuhoo, we can see your order has been delivered.\n" +
                "Pose and post a picture. We are sure you will rock it! :)"),
        RTO(OrderStatus.RETURNED, "RTO", "Returned", "We are so sad to know that you couldn't accept the product :( \n" +
                "Let us know the reason so that we can serve you better next time"),
        DTO_INITIATED(OrderStatus.COMPLETED, "DTO_INITIATED", "Return Initiated", "We are so sad to know that you were unhappy with the product. We regret the inconvenience caused :( \n" +
                "Hope you give us an opportunity to serve you again."),
        DTO_PICKED_UP(OrderStatus.RETURNED, "DTO_PICKED_UP", "Return Picked Up", "The  return pick up for your order has been completed, we will keep you informed about the status of your refund."),
        REFUND_INITIATED(OrderStatus.RETURNED, "REFUND_INITIATED", "Refund Initiated", "Your refund process has been initiated and you should receive within the next 5 - 7 working days! :)"),
        REFUND_PROCESSED(OrderStatus.RETURNED, "REFUND_PROCESSED", "Refund Processed", "Your refund has been processed, please check your bank statement to confirm the same! :)"),
        FAILED(OrderStatus.FAILED, "FAILED", "Invalid Order", "Order is Invalid"),
        ABANDONED(OrderStatus.ABANDONED, "ABANDONED", "Invalid Order", "Order is Abandoned");

        private OrderStatus orderStatus;
        private String value;
        private String displayValue;
        private String message;
        private List<DerivedOrderStatus> validPreviousStates;
        private List<DerivedOrderStatus> validForwardStates;
        private List<DerivedOrderStatus> validForwardPositiveStates;

        DerivedOrderStatus(OrderStatus orderStatus, String value, String displayValue, String message) {
            this.orderStatus = orderStatus;
            this.value = value;
            this.displayValue = displayValue;
            this.message = message;
        }

        public OrderStatus getOrderStatus() {
            return orderStatus;
        }

        public String getValue() {
            return value;
        }

        public List<DerivedOrderStatus> getValidPreviousStates() {
            return validPreviousStates;
        }

        public void setValidPreviousStates(List<DerivedOrderStatus> validPreviousStates) {
            this.validPreviousStates = validPreviousStates;
        }

        public List<DerivedOrderStatus> getValidForwardStates() {
            return validForwardStates;
        }

        public void setValidForwardStates(List<DerivedOrderStatus> validForwardStates) {
            this.validForwardStates = validForwardStates;
        }

        public List<DerivedOrderStatus> getValidForwardPositiveStates() {
            return validForwardPositiveStates;
        }

        private void setValidForwardPositiveStates(List<DerivedOrderStatus> validForwardPositiveStates) {
            this.validForwardPositiveStates = validForwardPositiveStates;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDisplayValue() {
            return displayValue;
        }

        private static Map<String, DerivedOrderStatus> valueMap = new HashMap<String, DerivedOrderStatus>();
        static {
            PLACED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.REJECTED, DerivedOrderStatus.CANCELLED, DerivedOrderStatus.CONFIRMED,
                    DerivedOrderStatus.CANCELLED,
                    DerivedOrderStatus.QUALITY_CHECK, DerivedOrderStatus.READY_TO_PICK, DerivedOrderStatus.SHIPPED,
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY, DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            CONFIRMED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.QUALITY_CHECK,DerivedOrderStatus.CANCELLED,
                    DerivedOrderStatus.READY_TO_PICK, DerivedOrderStatus.SHIPPED,
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY,DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            QUALITY_CHECK.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.READY_TO_PICK, DerivedOrderStatus.CANCELLED, DerivedOrderStatus.SHIPPED,
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY,DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            READY_TO_PICK.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.SHIPPED, DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY,DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            SHIPPED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY,DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            DISPATCHED_FROM_ORIGIN_CITY.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.REACHED_DESTINATION_CITY,DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            REACHED_DESTINATION_CITY.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            OUT_FOR_DELIVERY.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.DELIVERY_ATTEMPT_FAILED,
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            DELIVERY_ATTEMPT_FAILED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.DELIVERED, DerivedOrderStatus.RTO, DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            DELIVERED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.DTO_INITIATED,
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            RTO.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            DTO_INITIATED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.DTO_PICKED_UP, DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            DTO_PICKED_UP.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.REFUND_INITIATED, DerivedOrderStatus.REFUND_PROCESSED,
            }));
            REFUND_INITIATED.setValidForwardStates(Arrays.asList(new DerivedOrderStatus[]{
                    DerivedOrderStatus.REFUND_PROCESSED,
            }));

            for (DerivedOrderStatus derivedOrderStatus : DerivedOrderStatus.values()) {
                valueMap.put(derivedOrderStatus.getValue(), derivedOrderStatus);
            }

            /**
             *  Populate validForwardPositiveStates
             */
            PLACED.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.CONFIRMED,
                    DerivedOrderStatus.QUALITY_CHECK, DerivedOrderStatus.READY_TO_PICK, DerivedOrderStatus.SHIPPED,
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY, DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            CONFIRMED.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.QUALITY_CHECK,
                    DerivedOrderStatus.READY_TO_PICK, DerivedOrderStatus.SHIPPED, DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY,
                    DerivedOrderStatus.REACHED_DESTINATION_CITY, DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            QUALITY_CHECK.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.READY_TO_PICK, DerivedOrderStatus.SHIPPED,
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY, DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            READY_TO_PICK.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.SHIPPED,
                    DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY, DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            SHIPPED.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.DISPATCHED_FROM_ORIGIN_CITY,
                    DerivedOrderStatus.REACHED_DESTINATION_CITY, DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            DISPATCHED_FROM_ORIGIN_CITY.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.REACHED_DESTINATION_CITY,
                    DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            REACHED_DESTINATION_CITY.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.OUT_FOR_DELIVERY, DerivedOrderStatus.DELIVERED));

            OUT_FOR_DELIVERY.setValidForwardPositiveStates(Arrays.asList(DerivedOrderStatus.DELIVERED));

            DELIVERY_ATTEMPT_FAILED.setValidForwardPositiveStates(Arrays.asList(DELIVERED));

            DTO_INITIATED.setValidForwardPositiveStates(Arrays.asList(DTO_PICKED_UP, REFUND_INITIATED, REFUND_PROCESSED));

            DTO_PICKED_UP.setValidForwardPositiveStates(Arrays.asList(REFUND_INITIATED, REFUND_PROCESSED));

            REFUND_INITIATED.setValidForwardPositiveStates(Arrays.asList(REFUND_PROCESSED));

            RTO.setValidForwardPositiveStates(Arrays.asList(REFUND_INITIATED, REFUND_PROCESSED));

        }

        public static DerivedOrderStatus getDerivedOrderStatus(String statusValue) {
            return valueMap.get(statusValue);
        }

        private static Map<OrderStatus, DerivedOrderStatus> statusMap = new HashMap<OrderStatus, DerivedOrderStatus>();
        static{
            statusMap.put(OrderStatus.UNCONFIRMED, DerivedOrderStatus.PLACED);
            statusMap.put(OrderStatus.CONFIRMED, DerivedOrderStatus.CONFIRMED);
            statusMap.put(OrderStatus.REJECTED, DerivedOrderStatus.REJECTED);
            statusMap.put(OrderStatus.CANCELLED, DerivedOrderStatus.CANCELLED);
            statusMap.put(OrderStatus.SHIPPED, DerivedOrderStatus.SHIPPED);
            statusMap.put(OrderStatus.COMPLETED, DerivedOrderStatus.DELIVERED);
            statusMap.put(OrderStatus.RETURNED, DerivedOrderStatus.DTO_PICKED_UP);
            statusMap.put(OrderStatus.FAILED, DerivedOrderStatus.FAILED);
            statusMap.put(OrderStatus.ABANDONED, DerivedOrderStatus.ABANDONED);
        }

        public static DerivedOrderStatus getDerivedOrderStatusByOrderStatus(OrderStatus orderStatus) {
            return statusMap.get(orderStatus);
        }
    }
}
