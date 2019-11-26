package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yuliiamelnyk
 */
public class SignUpDao extends Dao {

    // Put our database url, username and password in final variables




    // create query
    private static final String QUERY = "INSERT INTO user (name , email, password, address, phone ) VALUES (?, ?, ?, ?, ?)";


    // Create method with connecting to database, doing query and put(insert) our values in database.
    public void insertRecord(String name, String email, String password, String address, String phone) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
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





}
