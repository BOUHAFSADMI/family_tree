/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author DarkSnow
 */
public class AddFormModel extends FormModel{
    

    public int getPersonID(String name,String lastName,LocalDate birth,int fatherID,int motherID) throws SQLException{
        String query ="select personID from person where name=? and lastName=? and birth=? and fatherID=? and motherID=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,birth.toString());
            preparedStatement.setInt(4,fatherID);
            preparedStatement.setInt(5,motherID);           
            resultSet= preparedStatement.executeQuery();
            if(!resultSet.isClosed())
                return resultSet.getInt(1);
            else return 0;
        }catch (Exception e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    
     
        
     public void insertPerson(String name,String lastName,char sexe,LocalDate birth,
        String place,LocalDate death,byte[] image,int fatherID,int motherID) throws SQLException
     {
         PreparedStatement preparedStatement;
         String query; 
         query = "insert into person(name,lastName,sexe,birth,place,death,image,fatherID,motherID) values('"+ name + "','" + lastName + "','" 
                + sexe + "','" + birth + "','" + place + "','" + death+ "',?,'" + fatherID +"','" + motherID +"')";
         try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBytes(1, image);
            preparedStatement.executeUpdate();
            System.out.println("person inserted");
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
          
}
