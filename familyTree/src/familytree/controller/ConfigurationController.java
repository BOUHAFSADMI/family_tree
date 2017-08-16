/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.ConfigurationModel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class ConfigurationController implements Initializable {
     
    @FXML private PasswordField oldPass;
    @FXML private PasswordField newPass;
    @FXML private PasswordField newPassConfirmation;
    @FXML private Label message;
    
    public ConfigurationModel configurationModel = new ConfigurationModel();
    public String Username="";
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert oldPass != null : "fx:id=\"oldPass";
        assert newPass != null : "fx:id=\"newPass";
        assert newPassConfirmation != null : "fx:id=\"newPassConfirmation";
        assert message != null : "fx:id=\"message";
    }    
    
    
    public void setUser(String username){
        this.Username =  username;
    }
    
    public void validerButtonAction(ActionEvent event) throws SQLException
    {
       if(configurationModel.isPassword(Username, oldPass.getText()))
        {
            if (!newPass.getText().equals("")) {
                if(newPass.getText().equals(newPassConfirmation.getText()))
                {
                configurationModel.updatePassword(Username,newPass.getText());
                ((Node)(event.getSource())).getScene().getWindow().hide();    
                }else{
                    message.setText("Deux mot de passe non identiques");
                }
            }else{
                 message.setText("Mot de passe non valide");
            }
        }else{
           message.setText("Ancien mot de passe erroné");
        } 
    }
    
    public void annulerButtonAction(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
}
