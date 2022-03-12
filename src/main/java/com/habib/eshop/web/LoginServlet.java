package com.habib.eshop.web;

import com.habib.eshop.domain.User;
import com.habib.eshop.dto.LoginDTO;
import com.habib.eshop.exception.UserNotFoundException;
import com.habib.eshop.repository.UserRepositoryImpl;
import com.habib.eshop.service.UserService;
import com.habib.eshop.service.UserServiceImpl;
import com.habib.eshop.util.SecurityContext;
import com.habib.eshop.util.ValidationUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoginServlet.class);

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        LOGGER.info("Saving login page");

        String logout = req.getParameter("logout");
        if(logout != null && Boolean.parseBoolean(logout)){
            req.setAttribute("message", "You have been successfully logged out.");
        }

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        var loginDTO = new LoginDTO(req.getParameter("username"), req.getParameter("password"));

        LOGGER.info("Received long data: {}");

        var errors = ValidationUtil.getInstance().validate(loginDTO);

        if (!errors.isEmpty()){
            LOGGER.info("Faild to login, sending login form again");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
        try {
            login(loginDTO, req);

            LOGGER.info("Login successful, redirecting to home page");
            resp.sendRedirect("/home");
        } catch (UserNotFoundException e){
            LOGGER.info("incorrect username/password");

            errors.put("username", "Incorrect username/password");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    private void login(LoginDTO loginDTO, HttpServletRequest req) throws UserNotFoundException {
        User user = userService.verifyUser(loginDTO);

        SecurityContext.login(req, user);
    }
}
