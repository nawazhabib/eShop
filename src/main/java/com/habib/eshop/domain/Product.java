package com.habib.eshop.domain;

import java.math.BigDecimal;

public class Product extends Domain{
    private String name;
    private String description;
    private BigDecimal price;

    public Product(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
