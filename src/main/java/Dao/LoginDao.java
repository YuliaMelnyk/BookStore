package Dao;

import Entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author andrescabrera, yuliiamelnyk
 */
public class LoginDao extends Dao{

    // Put our database url, username and password in final variables

    // create query
    private static final String SELECT_QUERY = "SELECT * FROM user WHERE email = ? and password = ?";
    private static final String GET_USER_QUERY = "SELECT * FROM user WHERE email = ?";

    // Create method with connecting to database, doing query and select our values from database.

    public boolean validate(String emailId, String password) throws SQLException {

        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
        preparedStatement.setString(1, emailId);
        preparedStatement.setString(2, password);

        System.out.println(preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return true;
        }
        } catch (SQLException e) {
        printSQLException(e);
    }finally {
            connection.close();
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
                user.setName((String) resultSet.getObject(4));
                user.setAdmin((boolean)resultSet.getObject(7));
            }
            return user;
        }
    }


}