package com.panteon.stock.controllers.items;

import com.panteon.stock.dto.ItemDto;
import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/itemdelete")
public class ItemDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 407733348003073587L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        System.out.println(login);


        if (login != null
                && !login.isEmpty()
                && (request.getParameter("id")) != null
                && (!request.getParameter("id").isEmpty())) {

            Long id = Long.parseLong(request.getParameter("id"));
            MyContext.getInstance().getItemService().deleteItemDtoById(id);
            response.sendRedirect("/personalEquipment");
        } else {
            request.setAttribute("errorMessage", "Access Denied. You Must be Logged");
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }
}
