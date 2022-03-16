package com.habib.eshop.service;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.domain.User;

public interface CartService {
    Cart getCartByUser(User currentUser);

    void addProductToCart(String productId, Cart cart);
    void removeProductToCart(String productId, Cart cart);
}
