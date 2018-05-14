package com.panteon.stock.controllers.items;

import com.panteon.stock.dto.ItemDto;
import com.panteon.stock.dto.LoginDto;

import com.panteon.stock.dto.PersonalEquipmentDto;
import com.panteon.stock.dto.UserDto;
import com.panteon.stock.services.ItemService;
import com.panteon.stock.services.PersonalEquipmentService;
import com.panteon.stock.services.UserProfileService;
import com.panteon.stock.tools.MyContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/itemcreate")
public class ItemCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 5364564316374639533L;
    private static final ItemService itemService = MyContext.getInstance().getItemService();
    private static final UserProfileService userProfileService = MyContext.getInstance().getUserProfileService();
    private static final PersonalEquipmentService personalEquipmentService = MyContext.getInstance().getPersonalEquipmentService();
    boolean result;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = (String) request.getSession().getAttribute("userLogin");

        if (request.getParameter("title") != null
                && !request.getParameter("title").isEmpty()
                && request.getParameter("description") != null
                && !request.getParameter("description").isEmpty()) {


            UserDto userDto = userProfileService.getUserDto(userLogin);
            ItemDto itemDto = new ItemDto(-1L, request.getParameter("title"),
                    request.getParameter("description"), new BigDecimal(request.getParameter("price")),
                    Integer.parseInt(request.getParameter("quantity")), userDto.getId());
            System.out.println(itemDto);
            result = itemService.setItemDto(itemDto);
//            UserDto userDto = userProfileService.getUserDto(userLogin);
//            ItemDto itemDto = new ItemDto(-1L, request.getParameter("title"),
//                    request.getParameter("description"), null,
//                    1, userDto.getId());
//            result = itemService.setItemDto(itemDto);

        }

        if (result) {
            LoginDto loginDto = new LoginDto(userLogin, new String());
            PersonalEquipmentDto personalEquipmentDto = personalEquipmentService.getUserItems(loginDto);
            request.getSession().setAttribute("personalEquipmentDto", personalEquipmentDto);
            response.sendRedirect("/personalEquipment");
        } else {
            request.setAttribute("errorMessage", "Cant save your item");
            request.getRequestDispatcher("/WEB-INF/views/item/ItemProfile.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        request.getSession().removeAttribute("itemDto");
        UserDto userDto = userProfileService.getUserDto(login);
        if (userDto != null) {
            request.getSession().setAttribute("userLogin", userDto.getLogin());
            request.getRequestDispatcher("WEB-INF/views/item/ItemProfile.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Sign up first");
            request.getRequestDispatcher("/login").forward(request, response);
        }

    }
}
