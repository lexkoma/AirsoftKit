package com.panteon.stock.services;

import com.panteon.stock.dao.ItemDao;
import com.panteon.stock.dto.ItemDto;
import com.panteon.stock.entity.Item;

import java.sql.SQLException;

public class ItemService {
    private ItemDao itemDao;

    public ItemService() {
        this.itemDao = new ItemDao(); // bad practice
    }

    public ItemService(ItemDao itemDAO) {
        this.itemDao = itemDAO;
    }

    //analog getById from Dao
    public ItemDto getItemDto(long id) {
        Item item;
        try {
            item = itemDao.getByID(id);
            return new ItemDto(item.getId(),
                    item.getTitle(),
                    item.getDescription(),
                    item.getPrice(),
                    item.getQuantity(),
                    item.getUserId());
        } catch (SQLException e) {
            System.out.println("Item not found. SQLException: " + e.getMessage());
            return null;
        }
    }

    // analog to insert/update in DAO but in service layer
    public boolean setItemDto(ItemDto itemDto) {
        boolean result = false;
        Item item = new Item(itemDto.getIdItem(),
                itemDto.getTitle(),
                itemDto.getDescription(),
                itemDto.getPrice(),
                itemDto.getQuantity(),
                itemDto.getUserId());
        if (itemDto.getIdItem() < 0) { // if itemDTO.getIdItem == -1, we are going to create new Item
            try {
                itemDao.insert(item);
//                System.out.println("Item " + item + " created");
                result = true;
            } catch (SQLException e) {
                // TODO handle Exception
                // e.printStackTrace();
                System.out.println("Item was not created. SQLException: " + e.getMessage());
            }
        } else {
            try {
                itemDao.update(item);
                System.out.println("Item " + item + " update");
                result = true;
            } catch (SQLException e) {
                // TODO handle Exception
                // e.printStackTrace();
                System.out.println("Item was not updated. SQLException: " + e.getMessage());
            }
        }
        return result;
    }

    public boolean isExistItem(long id) {
        boolean result = false;
        try {
            itemDao.getByID(id);
            result = true;
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Item not found, message: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public void deleteItemDto(ItemDto itemDto) {
        deleteItemDtoById(itemDto.getIdItem());

    }

    public void deleteItemDtoById(long id) {
        try {
            if (isExistItem(id)) {
                itemDao.deleteById(id);
                System.out.println("Item deleted sucessesfully");
            } else {
                System.out.println("Item doesn't exists");
            }
        } catch (SQLException e) {
            // TODO handle Exception
            // e.printStackTrace();
            System.out.println("Item not found. SQLException: " + e.getMessage());
        }
    }


}
