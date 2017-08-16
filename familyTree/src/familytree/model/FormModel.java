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
import java.time.LocalDate;
/**
 *
 * @author DarkSnow
 */
public class FormModel {
    
    
    Connection connection;
    
    
    public FormModel() 
    {
       connection = sqliteConnection.Connector();
       if (connection==null) System.exit(1);
    }
    
    
    public void insertWedding(int maleID,int femaleID,LocalDate date) throws SQLException
     {
         PreparedStatement preparedStatement;
         String query ="insert into wedding(maleID,femaleID,weddingDate) values('"+ maleID + "','" + femaleID + "','"+ date +"')"; 
         try {
             preparedStatement = connection.prepareStatement(query);
             preparedStatement.executeUpdate();
             System.out.println("wedding inserted");
         } catch (Exception e) {
             System.out.println("wedding not inserted");
         }
     }
      
      
    public ResultSet getFathers(String lastName,LocalDate birth) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query="select personID,name,birth from person where lastName=? and birth <? and sexe='M'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, birth.toString());
            resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }
    public ResultSet getMothers(LocalDate date) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query;
        query = "select personID,lastName,name,birth from person where birth <? and sexe='F' and personID in(select femaleID from wedding)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date.toString());            
            resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ResultSet getPartners(String gender) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query="select personID,lastName,name,birth from person where sexe=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gender);            
            resultSet=preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            return null;
        }
    }
    
     public ResultSet getInfos(int id)
    {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;
        
        String query = "select * from Person where personID=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet =  preparedStatement.executeQuery();           
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
    public ResultSet getWedding1(int ID) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query;
        query = "select femaleID,weddingDate from wedding where maleID=?";
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
    
    public ResultSet getWedding2(int ID) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        String query;
        query = "select maleID,weddingDate from wedding where femaleID=?";
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
}
