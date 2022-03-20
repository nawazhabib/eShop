package com.habib.eshop.web;

import com.habib.eshop.dto.ShippingAddressDTO;
import com.habib.eshop.repository.*;
import com.habib.eshop.service.CartService;
import com.habib.eshop.service.CartServiceImpl;
import com.habib.eshop.service.OrderService;
import com.habib.eshop.service.OrderServiceImpl;
import com.habib.eshop.util.SecurityContext;
import com.habib.eshop.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServlet.class);

    private CartService cartService = new CartServiceImpl(
            new JdbcCartRepositoryImpl(),
            new JdbcProductRepositoryImpl(),
            new JdbcCartItemRepositoryImpl()
            );
    private OrderService orderService = new OrderServiceImpl(
            new OrderRepositoryImpl(),
            new ShippingAddressRepositoryImpl(),
            new CartRepositoryImpl()
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        addCartToUi(req);
        req.setAttribute("countries", getCountries());

        req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
    }

    private void addCartToUi(HttpServletRequest req){
        if(SecurityContext.isAuthenticated(req)){
            var currentUser = SecurityContext.getCurrentUser(req);
            var cart = cartService.getCartByUser(currentUser);
            req.setAttribute("cart", cart);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        LOGGER.info("Handle order request form");
        var shippingAddress = copyParametersTo(req);

        LOGGER.info("shippingAdress information: {}", shippingAddress);

        var errors = ValidationUtil.getInstance().validate(shippingAddress);

        if(!errors.isEmpty()){
            req.setAttribute("countries", getCountries());
            req.setAttribute("errors", errors);
            req.setAttribute("shippingAddress", shippingAddress);
            addCartToUi(req);
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
        } else {
            orderService.processOrdre(shippingAddress, SecurityContext.getCurrentUser(req));
            resp.sendRedirect("/home?orderSuccess=true");
        }
    }

    private ShippingAddressDTO copyParametersTo(HttpServletRequest req){
        var shipingAddress = new ShippingAddressDTO();

        shipingAddress.setAddress(req.getParameter("address"));
        shipingAddress.setAddress2(req.getParameter("address2"));
        shipingAddress.setState(req.getParameter("state"));
        shipingAddress.setZip(req.getParameter("zip"));
        shipingAddress.setCountry(req.getParameter("country"));
        shipingAddress.setMobileNumber(req.getParameter("mobileNumber"));

        return shipingAddress;
    }

    private List<String> getCountries(){
        return List.of("Bangladesh", "Switzerland", "Japan", "USA", "UK");
    }
}
