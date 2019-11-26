package Dao;

import Entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author andres
 */
public class LoginDao {

    // Put our database url, username and password in final variables
    private static final String DATABASE_URL = "jdbc:mysql://85.214.120.213:3306/bookstoredam";
    private static final String DATABASE_USERNAME = "bookstore";
    private static final String DATABASE_PASSWORD = "1dam";

    // create query
    private static final String SELECT_QUERY = "SELECT * FROM user WHERE email = ? and password = ?";
    private static final String GET_USER_QUERY = "SELECT * FROM user WHERE email = ?";

    // Create method with connecting to database, doing query and select our values from database.

    public boolean validate(String emailId, String password) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public User getUserByEmail(String email) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = new User();
            while (resultSet.next()) {
                user.setAdmin((boolean)resultSet.getObject(7));
            }
            return user;
        }
    }

    public static void printSQLException(SQLException ex) {
        SignUpDao.getException(ex);
    }
}