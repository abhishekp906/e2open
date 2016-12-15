package com.e2open.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by abhishek on 12/13/16.
 */
public class Part extends BaseEntity {

    private String name;
    private String sku;
    private Long quantity;
    private Double mrp;
    private Double sellingPrice;
    private Date nextLookUpTime;

    private Boolean isTransportable;
    private Boolean isPurchaseble;
    private Boolean isProduceable;

    private String addressId;  // require in terms of purchase & produced
    private Long maxTimeToMakeAvailable;

    // if isProduceable : parts required
    private List<ChildPart> childParts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Boolean getIsTransportable() {
        return isTransportable;
    }

    public void setIsTransportable(Boolean isTransportable) {
        this.isTransportable = isTransportable;
    }

    public Boolean getIsPurchaseble() {
        return isPurchaseble;
    }

    public void setIsPurchaseble(Boolean isPurchaseble) {
        this.isPurchaseble = isPurchaseble;
    }

    public Boolean getIsProduceable() {
        return isProduceable;
    }

    public void setIsProduceable(Boolean isProduceable) {
        this.isProduceable = isProduceable;
    }

    public List<ChildPart> getChildParts() {
        return childParts;
    }

    public void setChildParts(List<ChildPart> childParts) {
        this.childParts = childParts;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Long getMaxTimeToMakeAvailable() {
        return maxTimeToMakeAvailable;
    }

    public void setMaxTimeToMakeAvailable(Long maxTimeToMakeAvailable) {
        this.maxTimeToMakeAvailable = maxTimeToMakeAvailable;
    }

    public Date getNextLookUpTime() {
        return nextLookUpTime;
    }

    public void setNextLookUpTime(Date nextLookUpTime) {
        this.nextLookUpTime = nextLookUpTime;
    }
}
