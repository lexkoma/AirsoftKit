package com.panteon.stock.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDaoCRUD<TEntity> {

    //Create
    void insert(TEntity entity) throws SQLException;

    //Read
    TEntity getByID(Long id) throws SQLException;

    List<TEntity> getAll() throws SQLException;

    List<TEntity> getByField(String fieldName, Object param) throws SQLException;

    //Update
    void update(TEntity entity) throws SQLException;

    //Delete
    void delete(TEntity entity) throws SQLException;

    void deleteById(Long id) throws SQLException;


}
