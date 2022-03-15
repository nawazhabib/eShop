package com.habib.eshop.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Cart {
    private Long id;
    private Set<CartItem> cartItems = new HashSet<>();
    private BigDecimal totalPrice;
    private Integer totalItem;
    private User user;

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public User getUser() {
        return user;
    }
}
