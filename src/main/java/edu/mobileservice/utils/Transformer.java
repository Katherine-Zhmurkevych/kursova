package edu.mobileservice.utils;

import edu.mobileservice.model.annotations.Column;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Transformer<T> {

    private static Logger logger = LogManager.getLogger("Transformer");
    private final Class<T> clazz;

    public Transformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Object fromResultSetToEntity(ResultSet rs) {
        Object entity = null;
        try {
            entity = Class.forName(clazz.getName()).getConstructor().newInstance();
            final Field[] fieldsArray = clazz.getDeclaredFields();
            for (Field field : fieldsArray) {
                field.setAccessible(true);
                field.set(entity, rs.getObject(field.getAnnotation(Column.class).name()));
            }
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            logger.info("Transformer exception: " + e.getMessage());
        }
        return entity;
    }
}
