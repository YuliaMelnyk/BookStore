package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author andrescabrera, yuliiamelnyk
 */
public abstract class Dao {
    protected static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Books";
    protected static final String DATABASE_USERNAME = "root";
    protected static final String DATABASE_PASSWORD = "Proroknsg12";

    public static void getException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                Throwable p = ex.getCause();
                while (p != null) {
                    System.out.println(p);
                    p = p.getCause();
                }
            }
        }
    }

    public static void printSQLException(SQLException ex) {
        getException(ex);
    }
}
