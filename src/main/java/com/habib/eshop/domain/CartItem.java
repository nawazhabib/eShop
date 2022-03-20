package com.habib.eshop.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private Integer id;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private Cart cart;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o){
        if(this == 0) return true;
        if(!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;

        return Objects.equals(getId(), cartItem.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }
}
