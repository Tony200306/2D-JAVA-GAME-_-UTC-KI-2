package main;

import java.sql.*;

public class DataBase {



    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost/btl?serverTimezone=UTC";
        String USERNAME = "root";
        String PASSWORD = "";
        Connection c = null ;
         c = DriverManager.getConnection(URL , USERNAME , PASSWORD);

        return  c ;
    }

    public static  void closeConnection( Connection c) throws SQLException {
        if( c!= null){
            c.close();
        }
    }

}
