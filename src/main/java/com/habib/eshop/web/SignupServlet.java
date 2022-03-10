package com.habib.eshop.web;

import com.habib.eshop.dto.UserDTO;
import com.habib.eshop.repository.UserRepositoryImpl;
import com.habib.eshop.service.UserServiceImpl;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(SignupServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        LOGGER.info("serving signup page");
        req.getRequestDispatcher("WEB-INF/signup.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = copyParametersTo(req);
        Map<String, String> errors = validate(userDTO);

        if (errors.isEmpty()) {
            LOGGER.info("user is valid, creating a new user with: {}", userDTO);
            userService.saveUsre(userDTO);
            resp.sendRedirect("/home");
        } else {
            req.setAttribute("errors", errors);
            LOGGER.info("User sent invalid data: {}", userDTO);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
        }
    }

        private UserDTO copyParametersTo(HttpServletRequest req){
            var userDTO = new UserDTO();
            userDTO.setFirstName(req.getParameter("firstName"));
            userDTO.setLastName(req.getParameter("lastName"));
            userDTO.setPassword(req.getParameter("password"));
            userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
            userDTO.setEmail(req.getParameter("email"));
            userDTO.setUsername(req.getParameter("username"));

            return userDTO;
        }

        private Map<String, String> validate(UserDTO userDTO){
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<UserDTO> violation : violations){
            String path = violation.getPropertyPath().toString();
            if(errors.containsKey(path));
            errors.put(path, errorMsg + "<br/>" + violation.getMessage());
        } else{
            errors.put(path, violation.getMessage());
            }
        }
        return errors;

}
