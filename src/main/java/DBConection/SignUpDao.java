package DBConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String URL = "jdbc:mysql://85.214.120.213:3306/bookstoredam";
    private static final String NAME = "bookstore";
    private static final String PASSWORD = "1dam";
    private static final String QUERY = "INSERT INTO user (name , email, password, address, phone ) VALUES (?, ?, ?, ?, ?)";


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
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
