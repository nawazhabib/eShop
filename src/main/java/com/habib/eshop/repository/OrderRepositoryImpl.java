package com.habib.eshop.repository;

import com.habib.eshop.domain.Order;
import com.habib.eshop.domain.User;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository{
    private static final Set<Order> ORDERS = new CopyOnWriteArraySet<>();

    @Override
    public Order save(Order order) {
        ORDERS.add(order);

        return order;
    }

    public Set<Order> findOrderByUser(User user) {

        return ORDERS.stream()
                .filter(order -> order.getUser().equals(user))
                .collect(Collectors.toSet());
    }
}
