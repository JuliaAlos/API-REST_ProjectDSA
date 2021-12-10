package edu.upc.dsa.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {
//
//        StringBuffer sb = new StringBuffer("INSERT INTO ");
//        sb.append(entity.getClass().getSimpleName()).append(" ");
//        sb.append("(");
//
//        String [] fields = ObjectHelper.getFields(entity);
//
//        sb.append(fields[0]);
//        for (int i =1; i<fields.length; i++) {
//            sb.append(", ").append(fields[i]);
//        }
//
//        sb.append(") VALUES (");
//        try {
//            Object value = ObjectHelper.getter(entity, fields[0]);
//            if (entity.getClass().getDeclaredField(fields[0]).getType().equals(String.class) && value != null) {
//                sb.append("'").append(value).append("'");
//            } else {
//                sb.append(ObjectHelper.getter(entity, fields[0]));
//            }
//
//            for (int i = 1; i < fields.length; i++) {
//                sb.append(", ");
//                Object v = ObjectHelper.getter(entity, fields[i]);
//                if (entity.getClass().getDeclaredField(fields[i]).getType().equals(String.class) && v != null) {
//                    sb.append("'").append(v).append("'");
//                } else {
//                    sb.append(ObjectHelper.getter(entity, fields[i]));
//                }
//            }
//        }catch (NoSuchFieldException e){
//            e.printStackTrace();
//        }
//
//        sb.append(")");
//
//        return sb.toString();

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

   //     sb.append("ID");
        sb.append(fields[0]);

        for (int i = 1; i< fields.length ;i++){
         //   if(!fields[i].equals("id"))
                sb.append(", ").append(fields[i]);

        }

        sb.append(") VALUES (?");

        for (int i = 1; i< fields.length ;i++){
            sb.append(", ?");
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

}
