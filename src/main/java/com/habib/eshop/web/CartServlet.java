package com.habib.eshop.web;

import com.habib.eshop.domain.Cart;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        var productId = req.getParameter("productId");
        LOGGER.info("Receiving request to add product with id: {} to cart", productId);

        var cart = getCart(req);
        addProductToCart(productId, cart);

        resp.sendRedirect("/home");
    }

    private void addProductToCart(String productId, Cart cart){
        //will implement later
    }
    private Cart getCart(HttpServletRequest req){
        //will implement later
        return new Cart();
    }
}
