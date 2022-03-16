package com.habib.eshop.repository;

import com.habib.eshop.domain.ShippingAddress;
import com.habib.eshop.dto.ShippingAddressDTO;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ShippingAddressRepositoryImpl implements ShippingAddressRepository{
    private static final Set<ShippingAddress> SHIPPING_ADDRESSES = new CopyOnWriteArraySet<>();

    @Override
    public ShippingAddress save(ShippingAddress shippingAddress){
        SHIPPING_ADDRESSES.add(shippingAddress);

        return shippingAddress;
    }
}
