/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafx.update.tool;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTool {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javafx_update", "root", "root");

        } catch (Exception e) {
            System.out.println("db connect fail" + e.getMessage());
        }
        return con;
    }
}
