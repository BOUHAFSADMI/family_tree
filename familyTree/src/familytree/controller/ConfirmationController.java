/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.ConfirmationModel;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class ConfirmationController implements Initializable {
    private int ID=0;

    ConfirmationModel confirmationModel= new ConfirmationModel();
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        this.getSelectedID();
        System.out.println("idc="+ID);
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
    
    public void setSelectedID(int id){
        this.ID=id;
    }
    
    @FXML 
    public void deleteButtonOnAction(ActionEvent event) throws SQLDataException, SQLException
    {
        if(ID!=0) confirmationModel.delete(ID);
        else 
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sélectionner une personne d'abord!");
            alert.showAndWait();
        }
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    @FXML 
    public void cancelButtonOnAction(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
