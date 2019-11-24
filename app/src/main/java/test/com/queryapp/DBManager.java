//package test.com.queryapp;
//
//import java.sql.*;
//
///**
// * Created by Administrator on 2018/6/2.
// */
//
//public class DBManager{
//
//    public static final String DRIVER = "com.mysql.jdbc.Driver";
//    public static final String USER = "root";
//    public static final String PASS = "root";
//    public static final String URL = "jdbc:mysql://192.168.1.102:3306/data";
//
//    private static DBManager per = null;
//    private Connection connection = null;
//    private Statement statement = null;
//
//    private DBManager(){
//
//    }
//
//    public static DBManager createInstance(){
//        if(per == null){
//            per = new DBManager();
//            per.initDB();
//        }
//        return per;
//    }
//
//    public void initDB(){
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void connectDB(){
//        System.out.println("Connecting to database...");
//        try {
//            connection = DriverManager.getConnection(URL,USER,PASS);
//            statement = connection.createStatement();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("SqlManager:Connect to database successful.");
//    }
//
//    public void closeDB(){
//        System.out.println("Close connection to database...");
//        try {
//            statement.cancel();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Close connection successful.");
//    }
//
//    public ResultSet executeQuery(String sql){
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }
//
//    public int executeUpdate(String sql){
//        int ret = 0;
//        try {
//            ret = statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }
//
//}
