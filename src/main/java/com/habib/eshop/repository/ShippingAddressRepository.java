package com.habib.eshop.repository;

import com.habib.eshop.domain.ShippingAddress;

import java.util.Optional;

public interface ShippingAddressRepository {
    ShippingAddress save(ShippingAddress convertTo);

    Optional<ShippingAddress> findOne(long shippingAddressId);
}
