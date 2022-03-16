package com.habib.eshop.repository;

import com.habib.eshop.domain.Order;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class OrderRepositoryImpl implements OrderRepository{
    private static final Set<Order> ORDER_SET = new CopyOnWriteArraySet<>();

    @Override
    public Order save(Order order){
        ORDER_SET.add(order);

        return order;
    }
}
