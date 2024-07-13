package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  // creating a method - connect
  // it's a static method - can be called without using an object
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";

    // making a connection to the data base

    // DriverManager.getconnection returns an object
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("Connected to the Database");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return connection;

  }

  // retreiving the data from the database

  // getCustomer method

  public static Customer getCustomer(String username) {
    String sql = "select * from Customers where username=?";

    Customer customer = null;

    // try-with resorces
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      // setting the placeholder to string - username
      statement.setString(1, username);

      // helps to execute the select query statement
      // returns a result set object
      // throws exception

      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_Id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  // main method

  public static void main(String[] args) {
    Customer customer = getCustomer("oleevesmc@naver.com");
    System.out.println(customer.getName());
    System.out.println(customer.getUsername());
    System.out.println(customer.getPassword());
    System.out.println(customer.getAccountId());

  }
}
