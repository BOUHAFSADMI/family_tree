/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.model;
import java.sql.*;

/**
 *
 * @author DarkSnow
 */
public class sqliteConnection {
    public static Connection Connector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
