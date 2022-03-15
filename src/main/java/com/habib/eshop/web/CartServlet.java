package com.habib.eshop.web;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.domain.User;
import com.habib.eshop.repository.CartItemRepositoryImpl;
import com.habib.eshop.repository.CartRepositoryImpl;
import com.habib.eshop.repository.ProductRepositoryImpl;
import com.habib.eshop.service.CartService;
import com.habib.eshop.service.CartServiceImpl;
import com.habib.eshop.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServlet.class);

    private CartService cartService
            = new CartServiceImpl(new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        var productId = req.getParameter("productId");

        LOGGER.info(
                "Received request to add product with id: {} to cart",
                productId);

        var cart = getCart(req);
        cartService.addProductToCart(productId, cart);

        resp.sendRedirect("/home");
    }

    private Cart getCart(HttpServletRequest req) {
        final User currentUser = SecurityContext.getCurrentUser(req);

        return cartService.getCartByUser(currentUser);
    }
}
