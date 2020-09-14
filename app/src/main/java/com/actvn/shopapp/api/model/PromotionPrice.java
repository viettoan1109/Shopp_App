package com.actvn.shopapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionPrice {
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("price_promotion")
    @Expose
    private Integer pricePromotion;
    @SerializedName("date_start")
    @Expose
    private Object dateStart;
    @SerializedName("date_end")
    @Expose
    private Object dateEnd;
    @SerializedName("status_promotion")
    @Expose
    private Integer statusPromotion;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(Integer pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public Object getDateStart() {
        return dateStart;
    }

    public void setDateStart(Object dateStart) {
        this.dateStart = dateStart;
    }

    public Object getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Object dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getStatusPromotion() {
        return statusPromotion;
    }

    public void setStatusPromotion(Integer statusPromotion) {
        this.statusPromotion = statusPromotion;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
