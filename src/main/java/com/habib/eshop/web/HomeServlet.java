package com.habib.eshop.web;

import com.habib.eshop.domain.Cart;
import com.habib.eshop.dto.ProductDTO;
import com.habib.eshop.repository.CartItemRepositoryImpl;
import com.habib.eshop.repository.CartRepositoryImpl;
import com.habib.eshop.repository.ProductRepositoryImpl;
import com.habib.eshop.service.CartService;
import com.habib.eshop.service.CartServiceImpl;
import com.habib.eshop.service.ProductService;
import com.habib.eshop.service.ProductServiceImpl;
import com.habib.eshop.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet  extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);

    private ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    private CartService cartService = new CartServiceImpl(
            new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving home page");

        List<ProductDTO> allProducts = productService.findAllProductSortedByName();
        LOGGER.info("Total product found {}", allProducts.size());

        if (SecurityContext.isAuthenticated(req)){
            var currentUser = SecurityContext.getCurrentUser(req);
            var cart = cartService.getCartByUser(currentUser);
            req.setAttribute("cart", cart);
        }

        req.setAttribute("products", allProducts);

        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
