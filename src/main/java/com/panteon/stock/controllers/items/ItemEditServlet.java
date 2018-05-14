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

@WebServlet("/itemedit")
public class ItemEditServlet extends HttpServlet {
    private static final long serialVersionUID = 4975571627108220501L;
    boolean result = false;
    private static UserProfileService userProfileService = MyContext.getInstance().getUserProfileService();
    private static ItemService itemService = MyContext.getInstance().getItemService();
    private static PersonalEquipmentService personalEquipmentService = MyContext.getInstance().getPersonalEquipmentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = (String) request.getSession().getAttribute("login");
        if ((request.getParameter("title") != null) && (!request.getParameter("title").isEmpty())) {
            UserDto userDto = userProfileService.getUserDto(login);
            Long id = (Long) request.getSession().getAttribute("itemId");
            ItemDto itemDto = itemService.getItemDto(id);

            itemDto.setTitle(request.getParameter("title"));
            System.out.println("title " + request.getParameter("title"));

            itemDto.setDescription(request.getParameter("description"));
            System.out.println("description " + request.getParameter("description"));

            itemDto.setDescription(request.getParameter("price"));
            System.out.println("price " + request.getParameter("price"));

            itemDto.setDescription(request.getParameter("quantity"));
            System.out.println("quantity " + request.getParameter("quantity"));
            System.out.println("Edit " + itemDto);
            result = itemService.setItemDto(itemDto);
        }

        if (result) {
            LoginDto loginDto = new LoginDto(login, new String());
            PersonalEquipmentDto personalEquipmentDto = personalEquipmentService.getUserItems(loginDto);
            request.getSession().setAttribute("personalEquipment", personalEquipmentDto);
            response.sendRedirect("/personalEquipment");
        } else {
            request.setAttribute("errorMessage", "Cant edit item");
            request.getRequestDispatcher("WEB-INF/views/item/ItemProfile.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null || request.getSession().getAttributeNames() == null) {
            request.setAttribute("errorMessage", "Log in first!");
            request.getRequestDispatcher("/login").forward(request, response);
        }

        UserDto userDto = userProfileService.getUserDto(request.getParameter("login"));
        ItemDto itemDto = itemService.getItemDto(Long.parseLong(request.getParameter("id")));

        if (itemDto != null) {
            request.getSession().setAttribute("login", userDto.getLogin());
            request.getSession().setAttribute("itemDto", itemDto);
            request.getSession().setAttribute("itemId", itemDto.getIdItem());
            request.getRequestDispatcher("/WEB-INF/views/item/ItemProfile.jsp").forward(request, response);
        }

    }
}
