package edu.mobileservice;

import edu.mobileservice.utils.ConnectionUtil;
import edu.mobileservice.utils.Menu;

import java.sql.Connection;
import java.sql.SQLException;


public class App {

    public static void main(String[] args) {
        new Menu().show();
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
