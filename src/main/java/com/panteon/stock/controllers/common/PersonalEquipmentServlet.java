package com.panteon.stock.controllers.common;

import com.panteon.stock.dto.LoginDto;
import com.panteon.stock.dto.PersonalEquipmentDto;
import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personalEquipment")
public class PersonalEquipmentServlet extends HttpServlet {
    private static final long serialVersionUID = -7932596170359747392L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        request.getSession().removeAttribute("itemDto");
        if (login != null && (!login.isEmpty())) {
            LoginDto loginDto = new LoginDto(login, new String());
            PersonalEquipmentDto personalEquipmentDto = MyContext.getInstance().getPersonalEquipmentService().getUserItems(loginDto);
            request.setAttribute("personalEquipmentDto", personalEquipmentDto);
            request.getRequestDispatcher("WEB-INF/views/common/PersonalEquipment.jsp").forward(request, response);
            MyContext.getInstance().getPersonalEquipmentService().getUserItems(loginDto);
        } else {
            request.setAttribute("errorMessage", "Access Denied. You Must be Logged");
            request.getRequestDispatcher("/login").forward(request, response);
        }


    }
}
