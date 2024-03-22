package com.example.cozastoreweb.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "productdetail")
public class ProductdetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_product_detail")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_color")
    private ColorEntity idColor;

    @ManyToOne
    @JoinColumn(name="id_product")
    private ProductEntity idProduct;

    @ManyToOne
    @JoinColumn(name="id_size")
    private SizeEntity idSize;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorEntity getIdColor() {
        return idColor;
    }

    public void setIdColor(ColorEntity idColor) {
        this.idColor = idColor;
    }

    public ProductEntity getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductEntity idProduct) {
        this.idProduct = idProduct;
    }

    public SizeEntity getIdSize() {
        return idSize;
    }

    public void setIdSize(SizeEntity idSize) {
        this.idSize = idSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
