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
            ObjectHelper.setter(entity, "Id", id);

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

            if(ObjectHelper.getter(o,"id") == null){
                return null;
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

    public Object getByUsername(Class theClass, String username) {
        String query = QueryHelper.createQuerySELECTbyUsername(theClass, username);
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
            if(ObjectHelper.getter(o,"id") == null){
                return null;
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
        String query = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);

            int i = 1;
            for (String field : ObjectHelper.getAllFieldsButId(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.setObject(i++, ObjectHelper.getter(object, "id"));

            pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Object object) {
        String query = QueryHelper.createQueryDELETE(object);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);

            pstm.setObject(1, ObjectHelper.getter(object, "id"));

            pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object> findAll(Class theClass) {
        String query = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm = null;

        ResultSet rs;
        List<Object> l = new LinkedList<>();
        try {
            pstm = conn.prepareStatement(query);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();

            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                l.add(o);

            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Object> findAll(Class theClass, HashMap params) {
        String query = QueryHelper.createQuerySELECTwithParameters(theClass, params);
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(query);


            int i = 1;
            for(Object v : params.values()){
                pstm.setObject(i++, v);
            }


            pstm.executeQuery();

            ResultSet rs = pstm.getResultSet();

            ResultSetMetaData metadata = rs.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            List<Object> l = new LinkedList<>();


            while (rs.next()){
                Object o = theClass.newInstance();
                for (int j=1; j<=numberOfColumns; j++){
                    String columnName = metadata.getColumnName(j);
                    ObjectHelper.setter(o, columnName, rs.getObject(j));
                }
                l.add(o);
            }


            return l;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    //query personalitzades - fer login aqui
    public List<Object> query(String query, Class theClass, HashMap params) {



        return null;
    }

    public String saveNewUser(Object entity){
        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;


        try {
            pstm = conn.prepareStatement(insertQuery);

            String id = UUID.randomUUID().toString();
            ObjectHelper.setter(entity, "Id", id);

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
}
