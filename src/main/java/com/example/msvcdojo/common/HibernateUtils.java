package com.example.msvcdojo.common;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Josep Maria on 06/07/2016.
 */
public class HibernateUtils {

    private static final Logger logger = Logger.getLogger(HibernateUtils.class);
    private static String[] connSrRodo = {"jdbc:mysql://localhost:3306/endesa", "root", "root"};
    private static String[] connSrRodoH2 = {"jdbc:h2:~/test://localhost:3306/testdb", "sa", "Pepamaca11"};
    private static Connection srRodo, srRodoH2;

    public static void closeConn() {
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

    public Connection getConnectionh2() {
        try {
            Class.forName("org.h2.Driver");
            srRodoH2 = DriverManager.getConnection(connSrRodoH2[0], connSrRodoH2[1], connSrRodoH2[2]);

        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (srRodo != null) {
                closeConn();
            }
        }

        return srRodoH2;
    }
}
