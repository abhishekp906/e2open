package com.e2open.entity;

import com.e2open.common.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by abhishek on 12/13/16.
 */
public class OrderTransaction extends BaseEntity {
    public static Type priceBreakupType = new TypeToken<Map<String, Double>>() {
    }.getType();
    private static final Gson gson = new Gson();

    
    private Long userId;
    
    private Long customerId;
    
    private String paymentType;
      
    private String paymentGateway;
      
    private String paymentId;
      
    private Constants.PaymentStatus paymentStatus;
      
    private String paymentGatewayStatus;
      
    private Boolean paymentCaptured;
      
    private String paymentCaptureStatus;
      
    private Date paymentCaptureTime;
      
    private Long shippingAddressId;
      
    private Long billingAddressId;
      
    private Double amountPaid;
      
    private Double styleMilesUsed;
      
    private Double totalAmount;
      
    private Double couponDiscountUsed;
      
    private String couponUsed;
      
    private String couponType;
      
    private String phone;
      
    private String customerName;
    /**
     * This is TRUE store if all orders in the transaction is cancalled
     */
      
    private Boolean allOrdersCancelled;
    //    Don't use this
//      (serialized = "true", defaultFetchGroup = "true")
//    private Map<String, Double> priceBreakup = new HashMap<String, Double>();
      
    private String priceBreakupString;

    private List<Long> storeId;
      
    private String skuId;
      
    private String productId;

      
    private String osType;
    //This will define the handler (Wooplr or StoreKing)
      
    private String transactionHandler;
      
    private Long reSellerId;

    public static Type getPriceBreakupType() {
        return priceBreakupType;
    }

    public static void setPriceBreakupType(Type priceBreakupType) {
        OrderTransaction.priceBreakupType = priceBreakupType;
    }

    public static Gson getGson() {
        return gson;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Constants.PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Constants.PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentGatewayStatus() {
        return paymentGatewayStatus;
    }

    public void setPaymentGatewayStatus(String paymentGatewayStatus) {
        this.paymentGatewayStatus = paymentGatewayStatus;
    }

    public Boolean getPaymentCaptured() {
        return paymentCaptured;
    }

    public void setPaymentCaptured(Boolean paymentCaptured) {
        this.paymentCaptured = paymentCaptured;
    }

    public String getPaymentCaptureStatus() {
        return paymentCaptureStatus;
    }

    public void setPaymentCaptureStatus(String paymentCaptureStatus) {
        this.paymentCaptureStatus = paymentCaptureStatus;
    }

    public Date getPaymentCaptureTime() {
        return paymentCaptureTime;
    }

    public void setPaymentCaptureTime(Date paymentCaptureTime) {
        this.paymentCaptureTime = paymentCaptureTime;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Double getStyleMilesUsed() {
        return styleMilesUsed;
    }

    public void setStyleMilesUsed(Double styleMilesUsed) {
        this.styleMilesUsed = styleMilesUsed;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getCouponDiscountUsed() {
        return couponDiscountUsed;
    }

    public void setCouponDiscountUsed(Double couponDiscountUsed) {
        this.couponDiscountUsed = couponDiscountUsed;
    }

    public String getCouponUsed() {
        return couponUsed;
    }

    public void setCouponUsed(String couponUsed) {
        this.couponUsed = couponUsed;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getAllOrdersCancelled() {
        return allOrdersCancelled;
    }

    public void setAllOrdersCancelled(Boolean allOrdersCancelled) {
        this.allOrdersCancelled = allOrdersCancelled;
    }

    public String getPriceBreakupString() {
        return priceBreakupString;
    }

    public void setPriceBreakupString(String priceBreakupString) {
        this.priceBreakupString = priceBreakupString;
    }

    public List<Long> getStoreId() {
        return storeId;
    }

    public void setStoreId(List<Long> storeId) {
        this.storeId = storeId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getTransactionHandler() {
        return transactionHandler;
    }

    public void setTransactionHandler(String transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    public Long getReSellerId() {
        return reSellerId;
    }

    public void setReSellerId(Long reSellerId) {
        this.reSellerId = reSellerId;
    }
}
