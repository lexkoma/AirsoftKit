package com.panteon.stock.controllers.users;

import com.panteon.stock.dto.UserDto;
import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class UserSignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 5258199671920902361L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = false;

        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        if ((!login.isEmpty()) && (login != null)
                && (!name.isEmpty()) && (name != null)
                && (!email.isEmpty()) && (email != null)
                && (!password.isEmpty()) && (password != null)
                && (!confirm_password.isEmpty()) && (confirm_password != null)) {
            request.getSession().invalidate();
            UserDto userDto = new UserDto(-1L, name, login, email, password);
            result = MyContext.getInstance().getUserProfileService().setUserDto(userDto);
        }
        if (result){
            response.sendRedirect("/login");
        } else {
            request.setAttribute("errorProfile", "Enter correct data... Try again!");
            request.getRequestDispatcher("WEB-INF/views/users/UserProfile.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("login") != null) {
            request.getSession().setAttribute("errorSignUp", "First logout from system");
            request.getRequestDispatcher("/personalEquipment").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/views/users/UserProfile.jsp").forward(request, response);
        }
    }
}
