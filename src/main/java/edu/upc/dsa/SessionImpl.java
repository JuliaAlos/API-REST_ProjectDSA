package edu.upc.dsa;

import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public Integer save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
          //  pstm.setObject(1, 0);
//            int i = 1;
//
//            for (String field: ObjectHelper.getFields(entity)) {
//                pstm.setObject(i++, ObjectHelper.getter(entity, field));
//            }

            pstm.executeQuery();
            //get Assigned id
            ResultSet res = pstm.getGeneratedKeys();

            res.next();

            return (int) res.getLong(1);


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void close() {

    }

    public Object get(Class theClass, int id) {
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

                    List<Method> methods = new ArrayList<>(Arrays.asList(theClass.getDeclaredMethods()));
                    try {
                        Method m = methods.stream().filter((Method method) -> method.getName().contains("set" + columnName)).findFirst().get();
                        m.invoke(o,  rs.getObject(i));
                    }catch (NoSuchElementException e){
                        logger.warn("No setter found for: " + columnName + " in " + theClass.getName());
                    }


                    System.out.println(columnName);

                }
            }
            return o;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}
