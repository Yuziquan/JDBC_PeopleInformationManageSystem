package com.wuchangi.Model_Layer;

import java.sql.*;

/*
 * @program: JDBC_PeopleInformation
 * @description: Tool Class to get the connection to the MySQL Database.
 * @author: WuchangI
 * @create: 2018-05-13-10-23
 **/

public class JDBC_Connection
{

    private static final String URL = "jdbc:mysql://localhost:3306/mysql?serverTimezone=GMT&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static Connection connection = null;

    static
    {
        //1.Loading driver using class name by reflection
        //Old loading method： "com.mysql.jdbc.Driver"
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }


        //2.Get the connection to the MySQL Database
        try
        {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static Connection getConnection()
    {
        return connection;
    }

}
