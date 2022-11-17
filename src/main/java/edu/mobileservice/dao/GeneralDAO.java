package edu.mobileservice.dao;

import java.util.List;


public interface GeneralDAO<T, K> {

    List<T> findAll() throws Exception;

    T findByID(K k) throws Exception;

    boolean create(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(K key) throws Exception;
}
