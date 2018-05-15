package com.panteon.stock.controllers.users;

import com.panteon.stock.dto.LoginDto;
import com.panteon.stock.services.LoginService;
import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserSignInServlet extends HttpServlet {

    private static final long serialVersionUID = 4305455827550748624L;
    private static final LoginService loginService = MyContext.getInstance().getLoginService();

    public UserSignInServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = false;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if ((login != null) && (!login.isEmpty()) && (password != null) && (!password.isEmpty())) {
            result = loginService.isLogged(new LoginDto(login, password));
        }
        if (result) {
            request.setAttribute(MyContext.LOGIN_ATTRIBUTE, login);
            request.getSession(true).setAttribute(MyContext.LOGIN_ATTRIBUTE, login);
            response.sendRedirect("/personalEquipment");
        } else {
            request.setAttribute("errorMessage", "Invalid Login or Password");
            request.getRequestDispatcher("/WEB-INF/views/users/Login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(MyContext.LOGIN_ATTRIBUTE) == null) {

            request.getRequestDispatcher("/WEB-INF/views/users/Login.jsp").forward(request, response);

        } else
            response.sendRedirect("/personalEquipment");

    }
}
