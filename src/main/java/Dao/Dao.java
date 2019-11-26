package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class Dao {
    protected static final String DATABASE_URL = "jdbc:mysql://85.214.120.213:3306/bookstoredam";
    protected static final String DATABASE_USERNAME = "bookstore";
    protected static final String DATABASE_PASSWORD = "1dam";

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
