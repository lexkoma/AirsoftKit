package com.panteon.stock.controllers.common;

import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancel")
public class CancelServlet extends HttpServlet {
    private static final long serialVersionUID = 7251348656939552748L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(MyContext.LOGIN_ATTRIBUTE) != null) {
            response.sendRedirect("/personalEquipment");
        }else{
            response.sendRedirect("/login");
        }
    }
}
