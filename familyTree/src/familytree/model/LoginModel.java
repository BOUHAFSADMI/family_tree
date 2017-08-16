/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DarkSnow
 */
public class LoginModel {
    
    Connection connection;
    
    public LoginModel() 
    {
       connection = sqliteConnection.Connector();
       if (connection==null) System.exit(1);
    }
    
public boolean isDBConnected()
    {
        try {
            return !connection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean isLogin(String user, String pass) throws SQLException
    {
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null;
        String query = "select * from login where username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
