package com.panteon.stock.entity;

import java.math.BigDecimal;

public class Item extends AEntity {

    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Long userId;

    public static final String ITEM_ENTITY_USER_ID = "userId";

    public Item(String title, String description, BigDecimal price, Integer quantity, Long userId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
    }

    public Item(Long id, String title, String description, BigDecimal price, Integer quantity, Long userId) {
        super(id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
