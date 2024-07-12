package bank;

import java.sql.Connection;
import java.sql.DriverManager;
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

  // main method

  public static void main(String[] args) {
    connect();
  }

  // retreiving the data from the database

  // getCustomer method

  public static Customer getCustomer(String username) {
    String sql = "select * from Customer where username=?";

    // try-with resorces
    try (Connection connection = connect()) {
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
