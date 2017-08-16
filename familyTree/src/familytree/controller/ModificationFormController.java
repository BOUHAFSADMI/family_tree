/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class ModificationFormController extends FormController implements Initializable {
    
    public Person person;
    private int ID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert lastName != null : "fx:id=\"lastName";
        assert name != null : "fx:id=\"name";
        assert birth != null : "fx:id=\"birth";
        assert place != null : "fx:id=\"place";
        assert death != null : "fx:id=\"death";
        assert image != null : "fx:id=\"image";
        assert sexe != null : "fx:id=\"sexe";
        assert father != null : "fx:id=\"father";
        assert mother != null : "fx:id=\"mother";
        assert partner != null : "fx:id=\"partner";
        assert weddingDate != null : "fx:id=\"weddingDate";
        
        
        sexe.getItems().addAll("MALE", "FEMELLE");
        image.setText(null);
        
        loadPersons(lastName,birth, father);
        loadPersons(birth, mother);
        loadPersons(sexe, partner);
        
        BooleanBinding booleanBinding = new BooleanBinding() {
            {
                super.bind(name.textProperty(),
                        lastName.textProperty(),
                        sexe.valueProperty(),
                        birth.valueProperty());
            }

            @Override
            protected boolean computeValue() {
                return (name.getText().isEmpty()
                        || lastName.getText().isEmpty()
                        || (sexe.getValue()==null)
                        || (birth.getValue()==null));
            }
        };
         
        BooleanBinding bb= new BooleanBinding() {

            {
                super.bind(partner.valueProperty());
            }
            @Override
            protected boolean computeValue() {
               return (partner.getValue()==null);
            }
        };

        btnValider.disableProperty().bind(booleanBinding);
        father.disableProperty().bind(booleanBinding);
        mother.disableProperty().bind(booleanBinding);
        partner.disableProperty().bind(booleanBinding);
        weddingDate.disableProperty().bind(bb);
    
        
        this.getSelectedID();     
       
        System.out.println("id ="+ID);
         
        
        try {
            setPerson(formModel.getInfos(ID));
        } catch (SQLException ex) {
            Logger.getLogger(ModificationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
         
         
        name.setText(person.getName());
        lastName.setText(person.getlastName());
        //birth=person.getBirthDate();
        birth.setValue(LocalDate.MIN);
        sexe.setValue(person.getSexe());
        place.setText(person.getBirthPlace());
        //death=person.getDeathDate();
        death.setValue(LocalDate.MIN);
        mother.setValue(person.getMother());
        partner.setValue(person.getPartner());
        //weddingDate=person.getWeddingDate();
        weddingDate.setValue(LocalDate.MIN);
        father.setValue(person.getFather());
        
    }    
    
    
    public void getSelectedID()
    {
        try{
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/familytree/view/personOverview.fxml").openStream());
            PersonOverviewController personOverviewController = (PersonOverviewController)loader.getController();
            setSelectedID(personOverviewController.getSelectedID());
           }catch (Exception e) {
            e.printStackTrace();
           } 
    }
    
    public void setSelectedID(int id)
    {
        this.ID=id;
    }
    
    
    public void setPerson(ResultSet resultSet) throws SQLException 
    {
         ResultSet partnerResSet=null;
           while(resultSet.next())
            {
                byte[] data = resultSet.getBytes(8);
                if(resultSet.getString(3).charAt(0)=='M'){partnerResSet=formModel.getWedding1(ID);}
                else {partnerResSet=formModel.getWedding2(ID);}
                person.setAll(ID, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4).charAt(0),
                        resultSet.getDate(5),resultSet.getString(6),resultSet.getDate(7),
                        new Image(new ByteArrayInputStream(data)), (Person)resultSet.getObject(9),
                        (Person)resultSet.getObject(10), (Person)partnerResSet.getObject(1),partnerResSet.getDate(2));
            }
    }
    
    public void setPerson(Person person)
    {
        this.person=person;
    }

    @FXML
    private void validerButtonAction(ActionEvent event) 
    {
        
    }
    
}
