package com.panteon.stock.filter;

import com.panteon.stock.services.UserProfileService;
import com.panteon.stock.tools.MyContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/user/*")
public class CredentialsFilter extends HttpFilter {


    private static final long serialVersionUID = -6377708870102708194L;

    private static final UserProfileService userService = MyContext.getInstance().getUserProfileService();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/login");
        } else if (session.getAttribute("userId") == null) {
            session.invalidate();
            response.sendRedirect("/login");
        } else {
            request.setAttribute("userName", userService.getUserDto(request.getParameter("login")));
            chain.doFilter(request, response);
        }
    }
}
