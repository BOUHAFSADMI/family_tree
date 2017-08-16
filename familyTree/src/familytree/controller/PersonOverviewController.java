/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.PersonOverviewModel;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class PersonOverviewController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private ListView<Person> listView;
    @FXML
    private ImageView photo;
    @FXML
    private Label lastName;
    @FXML
    private Label name;
    @FXML
    private Label birth;
    @FXML
    private Label father;
    @FXML
    private Label mother;
    @FXML
    private Label partners;
    @FXML
    private Label death;
    @FXML
    private Label brothers;
    @FXML
    private Label sons;
 
    
    PersonOverviewModel personOverviewModel = new PersonOverviewModel();
    private static int ID;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert searchField !=null :"fx:id\"searchField";
        assert listView !=null :"fx:id\"listView";
        assert photo !=null :"fx:id\"photo";
        assert lastName !=null :"fx:id\"lastName";
        assert name !=null :"fx:id\"name";
        assert birth !=null :"fx:id\"birth";
        assert father !=null :"fx:id\"father";
        assert mother !=null :"fx:id\"mother";
        assert partners !=null :"fx:id\"partners";
        assert death !=null :"fx:id\"gender";
        assert brothers !=null :"fx:id\"brothers";
        assert sons !=null :"fx:id\"sons";
        
        
        try {
            loadPerons(personOverviewModel.getPersons(),listView);
        } catch (SQLException ex) {
            Logger.getLogger(PersonOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getSelectedID()
    {
        return ID;
    }
     
    public void setSelectedID()
    {
        ID = listView.getSelectionModel().getSelectedItem().getPersonID(); //Integer.parseInt___split(" ")[0]
    }
    
     public void loadPerons(ResultSet resultSet,ListView list) throws SQLException{
        if(resultSet!=null)
            while(resultSet.next())
                list.getItems().add(new Person(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), ' ', null, null, null, null, null, null, null, null));
    }
     
   
     
    @FXML
    public void refreshButtonAction(ActionEvent event)
     {
         try {
            listView.getItems().clear();
            loadPerons(personOverviewModel.getPersons(),listView);
        } catch (SQLException ex) {
            Logger.getLogger(PersonOverviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
   
    
     @FXML
     public void listClicked(MouseEvent event) throws SQLException, IOException, ClassNotFoundException
     {
        ResultSet resultSet;
        setSelectedID();
        resultSet = personOverviewModel.getPerson(ID);
        lastName.setText(resultSet.getString(2));
        name.setText(resultSet.getString(3));
        if(!resultSet.getString(6).equals("")) 
            if(resultSet.getString(4).equals("M"))
                birth.setText(
                        resultSet.getString(4)
                                +"âle"+" Né le "+
                                resultSet.getString(5)
                                +" à "+
                                resultSet.getString(6));
            else birth.setText(resultSet.getString(4)+"emelle"+" Né le "+resultSet.getString(5)+" à "+resultSet.getString(6));
        else 
            if(resultSet.getString(4).equals("M"))
                birth.setText(resultSet.getString(4)+"âle"+" Né le "+resultSet.getString(5)+" à ?");
            else birth.setText(resultSet.getString(4)+"emelle"+" Né le "+resultSet.getString(5)+" à ?");
        
         if(resultSet.getString(7)!=null) death.setText(resultSet.getString(7));
        else death.setText("?");
        
        photo.setImage(null);
        try 
        { 
            byte[] data = resultSet.getBytes(8);
            Image image = new Image(new ByteArrayInputStream(data));
            photo.setImage(image); 
        } catch (Exception e) 
        {
            switch (resultSet.getString(4)) {
                case "M":
                    {
                        Image img = new Image("/familytree/img/male.png");
                        photo.setImage(img);
                        break;
                    }
                case "F":
                    {
                        Image img = new Image("/familytree/img/female.png");//TODO change the image
                        photo.setImage(img);
                        break;
                    }
            }
        }
        
        if(resultSet.getInt(9)!=0){
            ResultSet fatherResultSet;
            fatherResultSet=personOverviewModel.getPerson(resultSet.getInt(9));
            father.setText(fatherResultSet.getString(2));
        }else father.setText("?");
        
        if(resultSet.getInt(10)!=0){
            ResultSet motherResultSet;
            motherResultSet=personOverviewModel.getPerson(resultSet.getInt(10));
            mother.setText(motherResultSet.getString(2)+" "+motherResultSet.getString(3));
        }else  mother.setText("?");
        
       
      
        ResultSet partnerResultSet;
        String part="";
        partnerResultSet=personOverviewModel.getPartners(ID);
        while(partnerResultSet.next())
            part+=partnerResultSet.getString(1)+" "+partnerResultSet.getString(2)+"| ";
        partners.setText(part);
        
        ResultSet sonResultSet;
        String son="";
        sonResultSet= personOverviewModel.getSons(ID);
        while(sonResultSet.next())
            son+=sonResultSet.getString(1)+" | ";
        sons.setText(son);
        
        brothers.setText("");
        if(resultSet.getInt(10)!=0 && resultSet.getInt(9)!=0){
            ResultSet brotherResultSet;
            String brother="";
            brotherResultSet=personOverviewModel.getBrothers(ID,resultSet.getInt(9),resultSet.getInt(8));
            while(brotherResultSet.next())
                brother+=brotherResultSet.getString(1)+" | ";
            brothers.setText(brother);
        }
     }
     
    public void searchField_Released() throws SQLException
    {
        ResultSet resultSet;
        Person person=null;
        resultSet = personOverviewModel.getMatches(searchField.getText());
        if(resultSet!=null) 
        {
            listView.getItems().clear();
            while(resultSet.next())
            {
                person.setPersonID(resultSet.getInt(1));
                person.setlastName(resultSet.getString(2));
                person.setName(resultSet.getString(3));
                listView.getItems().add(person);
            }
        }
    }
}
