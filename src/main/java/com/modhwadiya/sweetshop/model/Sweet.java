package com.modhwadiya.sweetshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Sweet entity represents a sweet item in the shop.
 */
@Entity
public class Sweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for each sweet

    private String name;
    private String category;
    private double price;
    private int quantityInStock;

    // Default constructor (required by JPA)
    public Sweet() {
    }

    // Constructor with fields (useful for creating new sweets)
    public Sweet(String name, String category, double price, int quantityInStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
