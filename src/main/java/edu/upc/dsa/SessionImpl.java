package edu.upc.dsa;

import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.*;


public class SessionImpl implements Session {
    private static SessionImpl instance;
    final static Logger logger = Logger.getLogger(SessionImpl.class);
    private final Connection conn;
    private Boolean open;

    private SessionImpl(Connection conn) {
        this.conn = conn;
        open = conn != null;
    }

    public static SessionImpl getInstance(){
        if(instance==null){
            logger.info("New instace of: " + SessionImpl.class.getName());
            Connection conn = FactorySession.getConnection();
            instance = new SessionImpl(conn);
        }
        return instance;
    }

    public Boolean isOpen() {
        return open;
    }

    public String save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;


        try {
            pstm = conn.prepareStatement(insertQuery);

            String id = UUID.randomUUID().toString();
            ObjectHelper.setter(entity, "id", id);

            int i = 1;
            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

            return id;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void close() {

    }

    public Object get(Class theClass, String id) {
        String query = QueryHelper.createQuerySELECT(theClass, id);
        ResultSet rs;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            Object o = theClass.newInstance();

            while (rs.next()){
                for (int i=1; i<=numberOfColumns; i++){
                    String columnName = metadata.getColumnName(i);
                    ObjectHelper.setter(o, columnName, rs.getObject(i));
                }
            }
            return o;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    public List<Object> findAll(Class theClass) {
        return null;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }

    //query personalitzades - fer login aqui
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
