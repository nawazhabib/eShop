package com.habib.eshop.repository;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartRepositoryImplTest {
    private CartRepository cartRepository = new CartRepositoryImpl();

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void testFindByUser(){
    }

    @Test
    public void save(){
        var user = new User();
        user.setUsername("habib");

        Cart cart1 = createCart(user);
        Cart cart2 = createCart(user);
        Cart cart3 = createCart(user);

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);

        var byUser = cartRepository.findByUser(user);
        assertThat(byUser.isPresent(), is(true));
        assertThat(cart3, is(byUser.get()));
    }

    private Cart createCart(User user){
        var cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(BigDecimal.valueOf(34.4));
        return cart;
    }

    private void update(){
        var user = new User();
        user.setUsername("habib");

        Cart cart1 = createCart(user);
        Cart cart2 = createCart(user);
        Cart cart3 = createCart(user);

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);

        cart3 = createCart(user);
        cartRepository.update(cart3);

        var byUser = cartRepository.findByUser(user);
        assertThat(byUser.isPresent(), is(true));

        assertThat(cart3, is(byUser.get()));
    }
}
