package com.habib.eshop.repository;

import com.habib.eshop.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
