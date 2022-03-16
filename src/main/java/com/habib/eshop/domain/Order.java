package com.habib.eshop.domain;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private Cart cart;
    private ShippingAddress shippingAddress;
    private LocalDateTime shippingDate;
    private Payment payment;
    private boolean shipped;
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public boolean isShipped() {
        return shipped;
    }

    public User getUser() {
        return user;
    }
}
