package com.panteon.stock.dto;

import java.math.BigDecimal;

public class ItemDto {
    private long idItem;
    private String title;
    private String description;
    private BigDecimal price;
    private int quantity;
    private long userId;

    public ItemDto(long idItem, String title, String description, BigDecimal price, int quantity, long userId) {

        this.idItem = idItem;
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "idItem=" + idItem +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", userId=" + userId +
                '}';
    }
}
