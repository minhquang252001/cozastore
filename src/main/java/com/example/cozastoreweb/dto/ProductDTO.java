package com.example.cozastoreweb.dto;

public class ProductDTO {
    private int id;
    private String images;
    private String title;
    private Double price;
    private int quantity=1;
    private int idCategory;
    private String tags;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quanlity) {
        this.quantity = quanlity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
