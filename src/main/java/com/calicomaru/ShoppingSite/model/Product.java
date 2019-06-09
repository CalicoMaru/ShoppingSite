package com.calicomaru.ShoppingSite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String productDescription;
    private double price;
    private int quantity;
    private boolean inStock;

    public Product() {
    }

    public boolean isInStock() {
        return inStock;
    }

    @JsonIgnore
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(int id, String name, String productDescription, double price, int quantity, boolean inStock) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
    }
}
