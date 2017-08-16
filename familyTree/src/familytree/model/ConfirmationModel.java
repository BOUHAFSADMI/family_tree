/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *
 * @author DarkSnow
 */
public class ConfirmationModel {
    
    Connection connection;
    public ConfirmationModel()
    {
        connection = sqliteConnection.Connector();
        if (connection==null) System.exit(1);
    }
    
    /**
     *
     * @param ID
     * @throws SQLDataException
     * @throws SQLException
     */
    public void delete(int ID) throws SQLDataException, SQLException
    {
        PreparedStatement preparedStatement;
        String query;
        query="delete from person where personID=?";
        try {
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            System.out.println("person deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }    
   
}
