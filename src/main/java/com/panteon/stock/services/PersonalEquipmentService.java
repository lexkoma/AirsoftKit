package com.panteon.stock.services;

import com.panteon.stock.dao.ItemDao;
import com.panteon.stock.dao.UserDao;
import com.panteon.stock.dto.ItemDto;
import com.panteon.stock.dto.LoginDto;
import com.panteon.stock.dto.PersonalEquipmentDto;
import com.panteon.stock.entity.Item;
import com.panteon.stock.entity.User;

import java.sql.SQLException;

public class PersonalEquipmentService {
    private UserDao userDao;
    private ItemDao itemDao;

    public PersonalEquipmentService() {
        userDao = new UserDao();
        itemDao = new ItemDao();
    }

    public PersonalEquipmentService(UserDao userDao, ItemDao itemDao) {
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    public PersonalEquipmentDto getUserItems(LoginDto loginDto) {
        User user = null;
        PersonalEquipmentDto personalEquipmentDto = null;
        try {
            user = userDao.getByField(User.USER_LOGIN, loginDto.getLogin()).get(0);
            personalEquipmentDto = new PersonalEquipmentDto(user.getLogin());
            for (Item item : itemDao.getAll()) {
                System.out.println(item.getUserId());
                System.out.println(user.getId());
                if (item.getUserId() == user.getId()) {
                    ItemDto itemDto = new ItemDto(item.getId(), item.getTitle(), item.getDescription(), item.getPrice(),
                            item.getQuantity(), item.getUserId());
                    personalEquipmentDto.addItemDtO(itemDto);
                }
            }
            return personalEquipmentDto;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
