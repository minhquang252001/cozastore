package com.example.cozastoreweb.dto;

public class ProductdetailDTO {
    private int idProductDetail;
    private int idColor;
    private int idProduct;
    private int idSize;
    private int quantity;
    private String description;

    public int getIdProductDetail() {
        return this.idProductDetail;
    }

    public void setIdProductDetail(int idProductDetail) {
        this.idProductDetail = idProductDetail;
    }

    public int getIdColor() {
        return this.idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdSize() {
        return this.idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
