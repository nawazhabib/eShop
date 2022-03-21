package com.habib.eshop.service;

import com.habib.eshop.domain.Order;
import com.habib.eshop.domain.ShippingAddress;
import com.habib.eshop.domain.User;
import com.habib.eshop.dto.ShippingAddressDTO;
import com.habib.eshop.exception.CartItemNotFoundException;
import com.habib.eshop.repository.CartRepository;
import com.habib.eshop.repository.OrderRepository;
import com.habib.eshop.repository.ShippingAddressRepository;

public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ShippingAddressRepository shippingAddressRepository;
    private CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ShippingAddressRepository shippingAddressRepository,
                            CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void processOrdre(ShippingAddressDTO shippingAddressDTO, User currentUser) {
        var shippingAddress = convertTo(shippingAddressDTO);
        var savedShippingAddress = shippingAddressRepository.save(shippingAddress);

        var cart = cartRepository.findByUser(currentUser)
                .orElseThrow(() ->
                        new CartItemNotFoundException(
                                "Cart Not found by current users"));

        Order order = new Order();
        order.setCart(cart);
        order.setShippingAddress(savedShippingAddress);
        order.setShipped(false);
        order.setUser(currentUser);

        orderRepository.save(order);
    }

    private ShippingAddress convertTo(ShippingAddressDTO shippingAddressDTO) {
        var shippingAddress = new ShippingAddress();
        shippingAddress.setAddress(shippingAddressDTO.getAddress());
        shippingAddress.setAddress2(shippingAddressDTO.getAddress2());
        shippingAddress.setCountry(shippingAddressDTO.getCountry());
        shippingAddress.setState(shippingAddressDTO.getState());
        shippingAddress.setZip(shippingAddressDTO.getZip());
        shippingAddress.setMobileNumber(shippingAddressDTO.getMobileNumber());

        return shippingAddress;
    }
}
