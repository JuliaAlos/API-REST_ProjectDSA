package edu.upc.dsa.util;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;

import java.util.HashMap;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append(fields[0]);
        for (int i = 1; i< fields.length ;i++){
            sb.append(", ").append(fields[i]);
        }

        sb.append(") VALUES (?");

        for (int i = 1; i< fields.length ;i++){
            sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();

    }

    public static String createQueryINSERT_encryptPassword(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append(fields[0]);
        Integer passwordPos = null;
        for (int i = 1; i< fields.length ;i++){
            if(fields[i].equals("password"))
                passwordPos = i;
            sb.append(", ").append(fields[i]);

        }

        sb.append(") VALUES (?");

        for (int i = 1; i< fields.length ;i++){
            if(passwordPos!=null && passwordPos == i){
                sb.append(", MD5(?)");
            }else {
                sb.append(", ?");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Class theClass, String id) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE id = '").append(id).append("'");

        return sb.toString();
    }

    public static String createQuerySELECTAll(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        return sb.toString();
    }

    public static String createQueryUPDATE_encryptPassword(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(entity.getClass().getSimpleName()).append(" SET ");
        String[] fields = ObjectHelper.getAllFieldsButId(entity);

        for (String field : fields){
            if(field.equals("password") ){
                sb.append(field).append(" = ").append("MD5(?)").append(",");
            }else{
                sb.append(field).append(" = ").append("?").append(",");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(entity.getClass().getSimpleName()).append(" SET ");
        String[] fields = ObjectHelper.getAllFieldsButId(entity);

        for (String field : fields){
            sb.append(field).append(" = ").append("?").append(",");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public static String createQueryDELETE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName()).append(" WHERE id = ?");

        return sb.toString();
    }

    public static String createQuerySELECTbyUsername(Class theClass, String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE ").append(theClass.getSimpleName()).append("Name = '").append(username).append("'");

        return sb.toString();
    }

    public static String createQuerySELECTwithParameters(Class theClass, HashMap<String, Object> params){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE (");

        params.forEach((k,v) ->{
            k = k.substring(0, 1).toUpperCase() + k.substring(1);
            if(k.equals("Password")){
                sb.append(k).append(" = MD5(").append("?").append(") AND ");
            }else {
                sb.append(k).append(" = ").append("?").append(" AND ");
            }

        });

        sb.delete(sb.length()-4, sb.length()-1);
        sb.append(")");

        return sb.toString();
    }

}
