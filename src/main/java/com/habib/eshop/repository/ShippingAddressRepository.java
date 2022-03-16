package com.habib.eshop.repository;

import com.habib.eshop.domain.ShippingAddress;

public interface ShippingAddressRepository {
    ShippingAddress save(ShippingAddress convertTo);
}
