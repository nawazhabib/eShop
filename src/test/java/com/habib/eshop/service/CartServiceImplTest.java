package com.habib.eshop.service;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.domain.CartItem;
import com.habib.eshop.domain.Product;
import com.habib.eshop.domain.User;
import com.habib.eshop.exception.ProductNotFoundException;
import com.habib.eshop.repository.CartItemRepository;
import com.habib.eshop.repository.CartRepository;
import com.habib.eshop.repository.ProductRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    private User testUser;
    private Cart cart;

    private static final Product TEST_PRODUCT
            = new Product(1L, "Test Product", "Test Description", BigDecimal.valueOf(33.99));

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cartService = new CartServiceImpl(cartRepository, productRepository, cartItemRepository);

        cart = new Cart();

        testUser = new User();
        testUser.setUsername("TEST_USER");
        cart.setUser(testUser);
    }

    @Test
    public void testGetCartByUser_givenNullUser_shouldThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        cartService.getCartByUser(null);
    }

    @Test
    public void testGetCartByUser_givenNoExistingCartByUser_shouldCreateNewCart() {
        when(cartRepository.findByUser(testUser)).thenReturn(Optional.empty());
        when(cartRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);

        Cart cartByUser = cartService.getCartByUser(testUser);
        assertThat(cartByUser, notNullValue());
        assertThat(cartByUser.getUser(), equalTo(testUser));
        assertThat(cartByUser.getCartItems().size(), CoreMatchers.is(0));

        verify(cartRepository, times(1)).findByUser(testUser);
        verify(cartRepository, times(1)).save(any());
        verifyZeroInteractions(cartItemRepository);
    }

    @Test
    public void testGetCartByUser_givenExistingCartByUser_shouldReturnExistingCart() {
        when(cartRepository.findByUser(testUser)).thenReturn(Optional.of(cart));

        Cart cartByUser = cartService.getCartByUser(testUser);

        assertThat(cartByUser, notNullValue());
        assertThat(cartByUser, equalTo(cart));

        verify(cartRepository, times(1)).findByUser(testUser);
        verifyNoMoreInteractions(cartItemRepository);
    }

    @Test
    public void testAddProductToCart_givenEmptyProductId_shouldThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        cartService.addProductToCart("", cart);
    }

    @Test
    public void testAddProductToCart_givenMalformedProductId_shouldThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        cartService.addProductToCart("454DD", cart);
    }

    @Test
    public void testAddProductToCart_givenInvalidProductId_shouldThrowProductNotFoundException() {
        exception.expect(ProductNotFoundException.class);
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        cartService.addProductToCart("11", cart);
    }

    @Test
    public void testAddProductToCart_givenProductId_shouldAddProductInTheCart() {
        //arrange
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(TEST_PRODUCT));

        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);

        when(cartItemRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        when(cartRepository.update(argumentCaptor.capture())).thenAnswer(invocation -> invocation.getArguments()[0]);

        //act
        cartService.addProductToCart("1", cart);

        //assert
        Cart captorValue = argumentCaptor.getValue();

        assertThat(captorValue.getCartItems().size(), CoreMatchers.is(1));
        assertThat(captorValue.getTotalItem(), CoreMatchers.is(1));
        assertThat(captorValue.getTotalPrice(), CoreMatchers.is(cart.getTotalPrice()));

        verify(productRepository, times(1)).findById(anyLong());
        verify(cartRepository, times(1)).update(any());
        verify(cartItemRepository, times(1)).save(any());
        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(cartItemRepository);
        verifyNoMoreInteractions(cartRepository);
    }

    @Test
    public void testAddProductToCart_givenSimilarProductAlreadyInCart_shouldIncreaseCartItemQuantity() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(TEST_PRODUCT));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(TEST_PRODUCT);
        cartItem.setQuantity(1);
        cartItem.setPrice(TEST_PRODUCT.getPrice());

        cart.getCartItems().add(cartItem);

        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);

        when(cartItemRepository.update(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
        when(cartRepository.update(argumentCaptor.capture())).thenAnswer(invocation -> invocation.getArguments()[0]);

        cartService.addProductToCart("1", cart);

        Cart captorValue = argumentCaptor.getValue();
        assertThat(captorValue.getCartItems().size(), CoreMatchers.is(1));
        assertThat(captorValue.getTotalItem(), CoreMatchers.is(2));
        assertThat(captorValue.getTotalPrice(), CoreMatchers.is(TEST_PRODUCT.getPrice().multiply(BigDecimal.valueOf(2))));

        verify(productRepository, times(1)).findById(anyLong());
        verify(cartRepository, times(1)).update(any());
        verify(cartItemRepository, times(1)).update(any());
        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(cartItemRepository);
        verifyNoMoreInteractions(cartRepository);
    }

}
