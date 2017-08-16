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
public class PersonOverviewModel {    
   
    Connection connection;
    
    public ResultSet getPersons() throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query;
        query = "select personID,lastName,name from person";
        connection = sqliteConnection.Connector();
        try {
            preparedStatement=connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                //connection.close();
            } catch (Exception e) {
            }
        }     
    }
    
    public ResultSet getPerson(int ID) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query;
        query = "select * from person where personID=?";
        connection = sqliteConnection.Connector();
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ResultSet getBrothers(int personID,int fatherID,int motherID) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query;
        query = "select name from person where personID!=? and (fatherID=? or motherID=?)";
        connection = sqliteConnection.Connector();
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, personID);
            preparedStatement.setInt(2, fatherID);
            preparedStatement.setInt(3, motherID);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                //connection.close();
            } catch (Exception e) {
            }
        }    
    }

    public ResultSet getSons(int personID)throws SQLException{
        PreparedStatement preparedStatement = null;
        String query;
        query = "select name from person where fatherID=? or motherID=?";
        connection = sqliteConnection.Connector();
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, personID);
            preparedStatement.setInt(2, personID);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }
    
    public ResultSet getPartners(int personID) throws SQLException{
        PreparedStatement preparedStatement = null;
        String query;
        query = "select lastName,name from person where personID in(select femaleID ID from wedding where maleID=? "
                + "union select maleID ID from wedding where femaleID=?)";
        connection = sqliteConnection.Connector();
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1, personID);
            preparedStatement.setInt(2, personID);
            return preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                //connection.close();
            } catch (Exception e) {
            }
        }
    }
    
    public ResultSet getMatches(String SearchedText)
    {
        PreparedStatement preparedStatement = null;
        String query;
        query = "select personID,lastName,name from person where personID like ? or name like ? or lastName like ?";
        connection = sqliteConnection.Connector();
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1, SearchedText+"%");
            preparedStatement.setString(2, SearchedText+"%");
            preparedStatement.setString(2, SearchedText+"%");
            return preparedStatement.executeQuery();
        }catch(Exception ex)
        {
            return null;
        }      
    }
}
