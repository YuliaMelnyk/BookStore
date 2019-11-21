package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yuliiamelnyk
 */
public class SignUpDao {

    // Put our database url, username and password in final variables

    private static final String URL = "jdbc:mysql://85.214.120.213:3306/bookstoredam";
    private static final String NAME = "bookstore";
    private static final String PASSWORD = "1dam";

    // create query
    private static final String QUERY = "INSERT INTO user (name , email, password, address, phone ) VALUES (?, ?, ?, ?, ?)";


    // Create method with connecting to database, doing query and put(insert) our values in database.
    public void insertRecord(String name, String email, String password, String address, String phone) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(URL, NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, phone);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        getException(ex);
    }

    static void getException(SQLException ex) {
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
}
