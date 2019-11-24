//package test.com.queryapp;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * Created by Administrator on 2018/6/2.
// */
//
//public class Service {
//
//    public String readData(){
//        String readSql = "select * from data order by id desc limit 1";
//        DBManager sql = DBManager.createInstance();
//        sql.connectDB();
//
//        ResultSet rs = sql.executeQuery(readSql);
//        try {
//            if(rs.next())
//                System.out.println(rs.getString(2));
//                return rs.getString(2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Boolean writeData(String column,String value){
//        String writeSql = "insert into senddata ("+column+") values('"+value+"')";
//        DBManager sql = DBManager.createInstance();
//        sql.connectDB();
//        int ret = sql.executeUpdate(writeSql);
//        if(ret != 0){
//            sql.closeDB();
//            return true;
//        }
//        sql.closeDB();
//        return false;
//    }
//
//}
