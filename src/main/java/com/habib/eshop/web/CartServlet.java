package com.habib.eshop.web;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.domain.User;
import com.habib.eshop.repository.CartItemRepositoryImpl;
import com.habib.eshop.repository.CartRepositoryImpl;
import com.habib.eshop.repository.ProductRepositoryImpl;
import com.habib.eshop.service.Action;
import com.habib.eshop.service.CartService;
import com.habib.eshop.service.CartServiceImpl;
import com.habib.eshop.util.SecurityContext;
import com.habib.eshop.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.habib.eshop.service.Action.ADD;

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
        var action = req.getParameter("productId");
        var cart = getCart(req);

        if(StringUtil.isNotEmpty(action)){
            processCart(productId, action, cart);

            resp.sendRedirect("/checkout");
            return;
        }

        LOGGER.info(
                "Received request to add product with id: {} to cart",
                productId);

        cartService.addProductToCart(productId, cart);

        resp.sendRedirect("/home");
    }

    private void processCart(String productId, String action, Cart cart){
        switch (Action.valueOf(action.toUpperCase())){
            case ADD:
                LOGGER.info("Recived request to add product with id: {} to cart", productId);
                cartService.addProductToCart(productId, cart);
                break;
            case REMOVE:
                LOGGER.info("Received request to remove product with id: {} to cart", productId);
                cartService.removeProductToCart(productId, cart);
                break;
        }
    }

    private Cart getCart(HttpServletRequest req) {
        final User currentUser = SecurityContext.getCurrentUser(req);

        return cartService.getCartByUser(currentUser);
    }
}
