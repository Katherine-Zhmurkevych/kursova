package edu.mobileservice.dao.implementations;

import edu.mobileservice.dao.GeneralDAO;
import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.Table;
import edu.mobileservice.utils.ConnectionUtil;
import edu.mobileservice.utils.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static edu.mobileservice.common.Constants.ONE;
import static edu.mobileservice.common.Constants.ZERO;


public abstract class AbstractDAOImpl<T, K> implements GeneralDAO<T, K> {

    private static Logger logger = LogManager.getLogger("AbstractDAOImpl");
    private Class entityClass;

    public AbstractDAOImpl(Class entityClass) {
        this.entityClass = entityClass;
    }

    private String prepareFindAllEntitiesQuery() {
        return "SELECT * FROM " + getTableName();
    }

    public List<T> findAll() {
        Connection connection = ConnectionUtil.getConnection();
        List<T> entities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(prepareFindAllEntitiesQuery());
            while (rs.next()) {
                entities.add((T) new Transformer(entityClass).fromResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return entities;
    }

    private String getTableName() {
        return ((Table) entityClass.getAnnotation(Table.class)).name();
    }

    public T findByID(K k) {
        Connection connection = ConnectionUtil.getConnection();
        T entity = null;
        String findByIdQuery = "SELECT * FROM " + getTableName() + " WHERE id = " + String.valueOf(k);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(findByIdQuery);
            while (rs.next()) {
                entity = (T) new Transformer(entityClass).fromResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return entity;
    }

    private String prepareCreateQuery() {
        String query = "INSERT INTO ";
        final String tableName = getTableName();
        query += tableName + "(";
        final Field[] declaredFields = entityClass.getDeclaredFields();
        StringJoiner entityFields = new StringJoiner(", ");
        StringJoiner entityValues = new StringJoiner(", ");
        for (Field field : declaredFields) {
            entityFields.add(field.getAnnotation(Column.class).name());
            entityValues.add("?");
        }
        query += entityFields.toString() + ") VALUES (" + entityValues.toString() + ")";
        return query;
    }

    public boolean create(T entity) {
        Connection connection = ConnectionUtil.getConnection();
        int numberOfUpdatedRows = ZERO;
        try (PreparedStatement statement = connection.prepareStatement(prepareCreateQuery())) {
            final Field[] declaredFields = entity.getClass().getDeclaredFields();
            int parameterNumber;
            for (int i = 0; i < declaredFields.length; i++) {
                parameterNumber = i + ONE;
                declaredFields[i].setAccessible(true);
                statement.setString(parameterNumber, String.valueOf(declaredFields[i].get(entity)));
            }
            numberOfUpdatedRows = statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return numberOfUpdatedRows > ZERO;
    }

    private String prepareUpdateQuery(T entity) {
        String updateEntityQuery = StringUtils.EMPTY;
        try {
            updateEntityQuery = "UPDATE " + getTableName() + " SET ";
            final Field[] declaredFields = entityClass.getDeclaredFields();
            StringJoiner entityFields = new StringJoiner(", ");
            for (Field field : declaredFields) {
                field.setAccessible(true);
                final String fieldName = field.getAnnotation(Column.class).name();
                entityFields.add(fieldName + " = ?");
            }
            final Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            updateEntityQuery += entityFields.toString() + " WHERE id = ?";
        } catch (NoSuchFieldException e) {
            logger.error(e.getMessage());
        }
        return updateEntityQuery;
    }

    public boolean update(T entity) {
        Connection connection = ConnectionUtil.getConnection();
        int numberOfUpdatedRows = ZERO;
        try (PreparedStatement statement = connection.prepareStatement(prepareUpdateQuery(entity))) {
            final Field[] declaredFields = entityClass.getDeclaredFields();
            int parameterNumber = ZERO;
            for (int i = 0; i < declaredFields.length; i++) {
                parameterNumber = i + ONE;
                declaredFields[i].setAccessible(true);
                statement.setString(parameterNumber, String.valueOf(declaredFields[i].get(entity)));
            }
            statement.setInt(parameterNumber + ONE, (Integer) declaredFields[0].get(entity));
            numberOfUpdatedRows = statement.executeUpdate();
        } catch (IllegalAccessException | SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return numberOfUpdatedRows > ZERO;
    }

    private String prepareDeleteQuery(K key) {
        return "DELETE FROM " + getTableName() + " WHERE id = " + String.valueOf(key);
    }

    public boolean delete(K key) {
        int numberOfUpdatedRows = ZERO;
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            numberOfUpdatedRows = statement.executeUpdate(prepareDeleteQuery(key));
        } catch (SQLException e) {
            logger.error("SQL exception: " + e.getMessage());
        }
        return numberOfUpdatedRows > ZERO;
    }
}
