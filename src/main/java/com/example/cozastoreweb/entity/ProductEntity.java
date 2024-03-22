package com.example.cozastoreweb.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "images")
    private String images;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;


    @ManyToOne
    @JoinColumn(name ="id_category")
    private CategoryEntity categoryEntity;


    @Column(name = "tags")
    private String tags;

    @OneToMany(mappedBy = "idProduct")
    private List<ProductdetailEntity> listProduct;

    public List<ProductdetailEntity> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductdetailEntity> listProduct) {
        this.listProduct = listProduct;
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

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
