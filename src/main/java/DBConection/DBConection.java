/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBConection;

/**
 *
 * @author 1DAM
 */
import java.sql.*;

class DBConection {

    public static Connection conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://85.214.120.213:3306/bookstoredam", "bookstore", "1dam");
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public static void main(String[] args) {
       /* try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://85.214.120.213:3306/bookstoredam", "bookstore", "1dam");
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
        
        */
        
        
        
        
        

 
        Connection connect = null;
        Statement statement = null;
 
        try {
            // Load the MySQL driver.
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            // Setup the connection to the database.
            // Take notice at the different variables that are needed here:
            //      1. The name of the database and its location (currently localhost)
            //      2. A valid username/password for the connection.
            connect = DriverManager.getConnection("jdbc:mysql://85.214.120.213:3306/bookstoredam", "bookstore", "1dam");
 
            // Create the statement to be used to get the results.
            statement = connect.createStatement();
 
            // Create a query to use.
            String query = "SELECT email FROM user ORDER BY id";
 
            // Execute the query and get the result set, which contains
            // all the results returned from the database.
            ResultSet resultSet = statement.executeQuery(query);

            // We loop through the rows that were returned, and we can access the information
            // depending on the type of each column. In this case:
            while (resultSet.next()) {
                System.out.println("Printing result...");
 
                // Now we can fetch the data by column name, save and use them!
                String email = resultSet.getString("email");
 
                System.out.println(email);
            }
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // We have to close the connection and release the resources used.
            // Closing the statement results in closing the resultSet as well.
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
 
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

