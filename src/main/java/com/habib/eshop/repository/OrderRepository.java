package com.habib.eshop.repository;

import com.habib.eshop.domain.Order;
import com.habib.eshop.domain.User;

import java.util.Set;

public interface OrderRepository {
    Order save(Order order);
    Set<Order> findOrderByUser(User user);
}
