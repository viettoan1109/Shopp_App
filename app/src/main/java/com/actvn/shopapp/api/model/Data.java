package com.actvn.shopapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("upc")
    @Expose
    private Object upc;
    @SerializedName("ean")
    @Expose
    private Object ean;
    @SerializedName("jan")
    @Expose
    private Object jan;
    @SerializedName("isbn")
    @Expose
    private Object isbn;
    @SerializedName("mpn")
    @Expose
    private Object mpn;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("supplier_id")
    @Expose
    private String supplierId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("sold")
    @Expose
    private Integer sold;
    @SerializedName("minimum")
    @Expose
    private Integer minimum;
    @SerializedName("weight_class")
    @Expose
    private Object weightClass;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("length_class")
    @Expose
    private Object lengthClass;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("kind")
    @Expose
    private Integer kind;
    @SerializedName("virtual")
    @Expose
    private Integer virtual;
    @SerializedName("tax_id")
    @Expose
    private String taxId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("date_lastview")
    @Expose
    private String dateLastview;
    @SerializedName("date_available")
    @Expose
    private Object dateAvailable;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("descriptions")
    @Expose
    private List<Description> descriptions = null;
    @SerializedName("promotion_price")
    @Expose
    private Object promotionPrice;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Object getUpc() {
        return upc;
    }

    public void setUpc(Object upc) {
        this.upc = upc;
    }

    public Object getEan() {
        return ean;
    }

    public void setEan(Object ean) {
        this.ean = ean;
    }

    public Object getJan() {
        return jan;
    }

    public void setJan(Object jan) {
        this.jan = jan;
    }

    public Object getIsbn() {
        return isbn;
    }

    public void setIsbn(Object isbn) {
        this.isbn = isbn;
    }

    public Object getMpn() {
        return mpn;
    }

    public void setMpn(Object mpn) {
        this.mpn = mpn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Object getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(Object weightClass) {
        this.weightClass = weightClass;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Object getLengthClass() {
        return lengthClass;
    }

    public void setLengthClass(Object lengthClass) {
        this.lengthClass = lengthClass;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getVirtual() {
        return virtual;
    }

    public void setVirtual(Integer virtual) {
        this.virtual = virtual;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDateLastview() {
        return dateLastview;
    }

    public void setDateLastview(String dateLastview) {
        this.dateLastview = dateLastview;
    }

    public Object getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Object dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Object getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Object promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
