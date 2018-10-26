package com.ligh.utils;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 操作mysql的工具类
 */

public class MysqlUtil {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123456";

    private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://ip:3306/imooc_project";

    /**
     *
     * 获取数据库的连接
     */

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(DRIVERCLASS);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     *
     * 释放资源
     */
    public static void release(Connection connection, PreparedStatement pstmt, ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(pstmt != null){
            try {
                pstmt.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(connection != null){
            try{
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
