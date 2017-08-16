/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.AddFormModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class AddFormController extends FormController implements Initializable {
    
    
    AddFormModel addFormModel = new AddFormModel();
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
    }    
    
   

    public byte[] extractBytes (String ImageName) throws IOException {
        // open image
        if(ImageName!=null)
        {
            File img = new File(ImageName);
            FileInputStream f;
            f = new FileInputStream(img);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            for(int i;(i=f.read(buf))!=-1;)
                bos.write(buf,0,i);
            return bos.toByteArray();
            
        }else
            return null;
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void validerButtonAction(ActionEvent event) throws IOException
    {
        int personID=0, fatherID= 0, motherID= 0, partnerID= 0;
        try {
            if(father.getValue()!=null) fatherID = father.getValue().getPersonID();
            if(mother.getValue()!=null) motherID = mother.getValue().getPersonID();
            if(partner.getValue()!=null) partnerID = partner.getValue().getPersonID();
            personID= addFormModel.getPersonID(name.getText(), lastName.getText(), birth.getValue(), fatherID, motherID);
            if(personID==0)
            {               
                addFormModel.insertPerson(name.getText(), lastName.getText(), (char)sexe.getValue().toString().charAt(0), birth.getValue(), place.getText(),death.getValue(), extractBytes(image.getText()), fatherID, motherID);
                personID= addFormModel.getPersonID(name.getText(), lastName.getText(), birth.getValue(), fatherID, motherID);
                if(partnerID!=0)
                {
                    if(sexe.getValue().toString().charAt(0)=='M') addFormModel.insertWedding(personID, partnerID, weddingDate.getValue());
                    else addFormModel.insertWedding(partnerID, personID, weddingDate.getValue());
                }
            }
           
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }
    
}


