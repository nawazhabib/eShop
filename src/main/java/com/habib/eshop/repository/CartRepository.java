package com.habib.eshop.repository;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.domain.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User currentUser);

    Cart save(Cart cart);

    Cart update(Cart cart);

    Optional<Cart> findOne(long cartId);
}
