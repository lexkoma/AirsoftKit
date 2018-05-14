package com.panteon.stock.dao;

import com.panteon.stock.db.ConnectionManager;
import com.panteon.stock.entity.AEntity;
import com.panteon.stock.tools.PropertiesReader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class ADaoCRUD<TEntity extends AEntity> implements IDaoCRUD<TEntity> {
    protected final static String QUERY_NOT_FOUND = "Query not found %s";
    protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
    protected final static String DATABASE_READING_ERROR = "Database Reading Error";
    protected final static String DATABASE_INPUT_ERROR = "Database Input Error";
    protected final static String DATABASE_DELETE_ERROR = "Database Delete Error";

    private static final ConnectionManager connManager = ConnectionManager.getInstance();

    private String nameOfDBTable;
    private String idNaming;
    public static final String CREATE = "insert";
    public static final String UPDATE = "update";


    protected abstract TEntity createEntity(ResultSet resultSet) throws SQLException;

    protected abstract List<Object> getEntityParams(String operation, TEntity entity);


    protected ADaoCRUD(String nameOfDBTable, String idNaming) {
        this.nameOfDBTable = nameOfDBTable;
        this.idNaming = idNaming;
        // good to insert table if not exist
    }


    @Override
    public void insert(TEntity tEntity) throws SQLException {
        String query = PropertiesReader.getPropertyFromFile(nameOfDBTable+".insert");
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, query));
        }

        try (PreparedStatement preparedStatement = connManager.getConnection().prepareStatement(query)) {
            List<Object> entityParams = getEntityParams(ADaoCRUD.CREATE, tEntity);
            for (int i = 1; i < entityParams.size(); i++) {
                preparedStatement.setObject(i, entityParams.get(i));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        }
    }

    @Override
    public TEntity getByID(Long id) throws SQLException {
        List<TEntity> entities = getByField(idNaming, id);
        if (entities.isEmpty()){
            return null;
        }
        return entities.get(0);
    }

    @Override
    public List<TEntity> getAll() throws SQLException {
        List<TEntity> entities = new ArrayList<>();
        String query = PropertiesReader.getPropertyFromFile(nameOfDBTable + ".findAll");
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, query));
        }
        try (Statement statement = connManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        if (entities.isEmpty()) {
            // throw new GeneralCustomException(String.format(EMPTY_RESULTSET,
            // query));
            throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
        }

        return entities;

    }

    @Override
    public List<TEntity> getByField(String fieldName, Object param) throws SQLException {
        List<TEntity> entities = new ArrayList<>();
        String query = PropertiesReader.getPropertyFromFile(nameOfDBTable + ".getByField")
                .replace("$field$", fieldName);
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, query));
        }

        try (PreparedStatement preparedStatement = connManager.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(createEntity(resultSet));
            }
//            preparedStatement.execute();
            resultSet.close();
            return entities;


        } catch (SQLException e) {
            e.printStackTrace();
        }
//        if (entities.isEmpty()) {
//            System.out.println("Нема такого у полі");
//            // throw new GeneralCustomException(String.format(EMPTY_RESULTSET,
//            // query));
////            throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
//        }

        return null;

    }




    @Override
    public void update(TEntity tEntity) throws SQLException {
        String query = PropertiesReader.getPropertyFromFile(nameOfDBTable + ".update");
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, query));
        }

        try (PreparedStatement preparedStatement = connManager.getConnection().prepareStatement(query)) {
            List<Object> entityParams = getEntityParams(ADaoCRUD.UPDATE, tEntity);
            for (int i = 1; i < entityParams.size(); i++) {
                preparedStatement.setObject(i, entityParams.get(i));

            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_INPUT_ERROR, e);
//            System.out.println("not update");
        }
    }

    @Override
    public void delete(TEntity tEntity) throws SQLException {
        deleteById(tEntity.getId());


    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String query = PropertiesReader.getPropertyFromFile(nameOfDBTable + ".deleteById");
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, PropertiesReader.getPropertyFromFile(query)));
        }
        try (PreparedStatement preparedStatement = connManager.getConnection().prepareStatement(query)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_DELETE_ERROR, e);
        }

    }
}
