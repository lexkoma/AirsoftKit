package com.panteon.stock;

import com.panteon.stock.dao.ItemDao;
import com.panteon.stock.dao.UserDao;
import com.panteon.stock.dto.ItemDto;
import com.panteon.stock.dto.LoginDto;
import com.panteon.stock.dto.UserDto;
import com.panteon.stock.services.ItemService;
import com.panteon.stock.services.LoginService;
import com.panteon.stock.services.UserProfileService;

import java.math.BigDecimal;

public class Demo {
    public static void main(String[] args) {

        UserDao userDAO = new UserDao();
        ItemDao itemDAO = new ItemDao();
        //
        LoginService loginService = new LoginService(userDAO);
        UserProfileService userProfileService = new UserProfileService(userDAO);
        ItemService itemServise = new  ItemService(itemDAO);
//        UserItemsServise userItemsServise = new UserItemsServise(userDAO, itemDAO);


        // *** loginService

        // login invalid user - OK
		LoginDto loginDTO = new LoginDto("Ivan", "qwertyqwerty"); // - FALSE, wrong password
		boolean result = loginService.isLogged(loginDTO);
		System.out.println("result = " + result);

        // login valid user - OK
//		LoginDTO loginDTO = new LoginDTO("Ivan", "qwerty"); // - TRUE
//		boolean result = loginService.isLogged(loginDTO);
//		System.out.println("result = " + result);

//		LoginDTO loginDTO = new LoginDTO("igor", "igor");
//		loginService.isLogged(loginDTO);



        // *** userProfileService

        // Create new user - OK
		UserDto userDTO = new UserDto(-1, "alehan", "a_login", "usoer1@gmail.com", "qwerty");
		userProfileService.setUserDto(userDTO);

//        userProfileService.deleteUserDto(userDTO);
//		// Delete user - OK
//		UserDTO userDTO = new UserDTO(2, "IvanName", "Ivan0", "qwerty", "a@gmail.com");
//		userProfileService.deleteUserDTO(userDTO);


        // login invalid user - OK
//		LoginDto loginDTO = new LoginDto("Ivan", "qwertyqwerty"); // - FALSE, wrong password
//		boolean result = loginService.isLogged(loginDTO);
//		System.out.println("result = " + result);

        // login valid user - OK
//		LoginDto loginDTO1 = new LoginDto("a_login", "qwerty"); //
//        boolean result1 = loginService.isLogged(loginDTO1);
//        System.out.println("result = " + result1);

        // Check existing login - OK
//		userProfileService.isExistLogin("a_login");
//        // Check not existing login - OK
//        userProfileService.isExistLogin("a_pogin");

        // Get existing user - OK
		UserDto userDTO1 = userProfileService.getUserDto("a_login");
		System.out.println(userDTO1.getId());

        // Get non existing user // NullPointerException expected - OK
//		UserDto userDTO2 = userProfileService.getUserDto("Ivan");
//		System.out.println(userDTO2.getId());


//		ItemDto itemDto =new ItemDto(-1, "HopUp", "", BigDecimal.valueOf(20), 4, 5);
//		itemServise.setItemDto(itemDto);

		// *** itemServise
		// create item
		// Correct data - OK
//		ItemDto itemDTO = new ItemDto(-1, "Title1", "Description1", BigDecimal.valueOf(45), 10, 5);
//		itemServise.setItemDto(itemDTO);

		// Invalid userId // SQLException found - OK
//		ItemDto itemDTO1 = new ItemDto(-1, "Title1", "Description1", BigDecimal.valueOf(45), 10, 1);
//		itemServise.setItemDto(itemDTO1);

		// update item - OK
		// TODO check
//		ItemDto itemDTO3 = new ItemDto(3, "Title1", "Description1", BigDecimal.valueOf(45), 10, 5);
//		itemServise.setItemDto(itemDTO3);
//
//		// is exists item
//		boolean result = itemServise.isExistItem(2);
//		System.out.println("isExistItem, result = " + result);
//		//
//
//        boolean result2 = itemServise.isExistItem(1);
//        System.out.println("isExistItem, result = " + result2);
    }
}
