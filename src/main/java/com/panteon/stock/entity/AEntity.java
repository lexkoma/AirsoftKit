package com.panteon.stock.entity;

public abstract class AEntity {
    private Long id;

    public AEntity() {
    }

    public AEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
