package com.e2open.entity;

import java.util.List;

/**
 * Created by abhishek on 12/13/16.
 */
public class ChildPart extends BaseEntity {

    String partId;
    Long countRequired;
    Float qualityRatio;   //alternative part : how good this for parent
    List<ChildPart> childParts;  // alternative parts for this part

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public Long getCountRequired() {
        return countRequired;
    }

    public void setCountRequired(Long countRequired) {
        this.countRequired = countRequired;
    }

    public Float getQualityRatio() {
        return qualityRatio;
    }

    public void setQualityRatio(Float qualityRatio) {
        this.qualityRatio = qualityRatio;
    }

    public List<ChildPart> getChildParts() {
        return childParts;
    }

    public void setChildParts(List<ChildPart> childParts) {
        this.childParts = childParts;
    }
}
