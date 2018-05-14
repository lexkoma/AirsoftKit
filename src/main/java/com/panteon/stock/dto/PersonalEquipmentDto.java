package com.panteon.stock.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonalEquipmentDto {
    private List<ItemDto> itemsList;
    private String userLogin;

    public PersonalEquipmentDto(String userLogin) {
        this.userLogin = userLogin;
        itemsList = new ArrayList<>();
    }

    public PersonalEquipmentDto(String userLogin, List<ItemDto> itemsList) {
        this.userLogin = userLogin;
        this.itemsList = itemsList;
    }

    public List<ItemDto> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemDto> itemsList) {
        this.itemsList = itemsList;
    }

    public void addItemDtO(ItemDto item) {
        getItemsList().add(item);
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
