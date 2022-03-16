package com.habib.eshop.service;

import com.habib.eshop.domain.User;
import com.habib.eshop.dto.ShippingAddressDTO;

public interface OrderService {
    void processOrdre(ShippingAddressDTO shippingAddress, User currentUser);
}
