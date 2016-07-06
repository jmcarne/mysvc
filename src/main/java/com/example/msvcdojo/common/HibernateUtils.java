package com.example.msvcdojo.common;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Josep Maria on 06/07/2016.
 */
public class HibernateUtils {

    private static final Logger logger = Logger.getLogger(HibernateUtils.class);
    private static String[] connSrRodo = {"jdbc:mysql://localhost:3306/endesa", "root", "root"};
    private Connection srRodo;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            srRodo = DriverManager.getConnection(connSrRodo[0], connSrRodo[1], connSrRodo[2]);

        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (srRodo != null) {
                closeConn();
            }
        }

        return srRodo;
    }

    public void closeConn() {
        PreparedStatement pstmt = null;
        ResultSet rsIns = null;

        try {
            rsIns.close();
            pstmt.close();
            srRodo.close();
        } catch (SQLException e) {
            Logger.getLogger("Close connection: ");
            e.getMessage();
        }
    }
}
