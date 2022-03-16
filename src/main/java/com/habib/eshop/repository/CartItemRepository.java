package com.habib.eshop.repository;

import com.habib.eshop.domain.CartItem;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);

    CartItem update(CartItem cartItem);

    void remove(CartItem cartItem);
}
