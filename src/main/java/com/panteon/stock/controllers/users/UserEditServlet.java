package com.panteon.stock.controllers.users;

import com.panteon.stock.dto.UserDto;
import com.panteon.stock.services.UserProfileService;
import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class UserEditServlet extends HttpServlet {
    private static final long serialVersionUID = 8123080502112449738L;
    private static final UserProfileService userProfileService = MyContext.getInstance().getUserProfileService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        boolean result = false;
        if (name != null && !name.isEmpty()
                && login != null && !login.isEmpty()
                && email != null && !email.isEmpty()
                && password != null && !password.isEmpty()
                && confirmPassword != null && !confirmPassword.isEmpty()) {

            UserDto userDto = new UserDto(id, name, login, email, password);

            result = userProfileService.setUserDto(userDto);

            if (result) {
                response.sendRedirect("/personalEquipment");
            }
        } else {
            request.setAttribute("errorProfile", "Please fill all fields");
            request.getRequestDispatcher("WEB-INF/views/users/UserProfile.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute(MyContext.LOGIN_ATTRIBUTE);
        UserDto userDto = userProfileService.getUserDto(login);

        if (login == null) {
            request.setAttribute("errorMessage", "Please login first");
            request.getRequestDispatcher("/login").forward(request, response);
        }
        if (userDto != null) {
            request.getSession().setAttribute("userDto", userDto);
            request.getRequestDispatcher("WEB-INF/views/users/UserProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Login first");
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }
}

