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

/**
 *
 * @author DarkSnow
 */
public class ConfigurationModel {
    
    Connection connection;
    
    
     public ConfigurationModel() 
    {
        connection = sqliteConnection.Connector();
        if(connection==null) System.exit(1);
    }
    
    public boolean isPassword(String user, String oldPass) throws SQLException
    {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;
        String query = "select password from login where username =? and password =?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, oldPass);
            resultSet =  preparedStatement.executeQuery();
            if(resultSet.next()) return oldPass.equals(resultSet.getString(1)); 
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void updatePassword(String user, String newPass) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query = "update login set password =? where username =?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPass);
            preparedStatement.setString(2, user);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }    
}
