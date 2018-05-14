package com.panteon.stock.dao;

import com.panteon.stock.entity.Item;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends ADaoCRUD<Item> {
    public ItemDao() {
        super("item", "item_id");
    }

    @Override
    protected Item createEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("item_id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        Integer quantity = resultSet.getInt("quantity");
        Long userId = resultSet.getLong("userId");
        return new Item(id, title, description, price, quantity, userId);
    }



    @Override
    protected List<Object> getEntityParams(String operation, Item item) {
        List<Object> params = new ArrayList<>();
        switch (operation) {
            case CREATE:
                params.add(item.getId());               //0
                params.add(item.getTitle());            //1
                params.add(item.getDescription());      //2
                params.add(item.getPrice());            //3
                params.add(item.getQuantity());         //4
                params.add(item.getUserId());           //5
                break;
            case UPDATE:

                params.add(item.getTitle()); //0
                params.add(item.getDescription()); //1
                params.add(item.getPrice()); //2
                params.add(item.getQuantity()); //3
                params.add(item.getUserId()); //4
                params.add(item.getId());               //5

                break;
        }
        return params;
    }
}
