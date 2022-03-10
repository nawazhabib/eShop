package com.habib.eshop.web;

import com.habib.eshop.dto.ProductDTO;
import com.habib.eshop.repository.DummyProductRepositoryImpl;
import com.habib.eshop.service.ProductService;
import com.habib.eshop.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet  extends HttpServlet {
    private ProductService productService = new ProductServiceImpl(new DummyProductRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<ProductDTO> allProducts = productService.findAllProductSortedByName();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
