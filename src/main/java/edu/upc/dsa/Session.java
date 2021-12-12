package edu.upc.dsa;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    Boolean isOpen();
    String save(Object entity);
    void close();
    Object get(Class theClass, String id);
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap<String, Object> params);
    List<Object> query(String query, Class theClass, HashMap params);
}
