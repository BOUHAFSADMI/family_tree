/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.AppModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class AppController implements Initializable {
    
    public String Username="";
    @FXML private Button sortir;
    @FXML private MenuItem sortirItem;
    
    AppModel appModel = new AppModel();
   
   
    
    
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert sortir !=null :"fx:id\"sortir";
        assert sortirItem !=null :"fx:id\"sortirItem";
       
    }
    
    public void setUser(String username){
        Username =  username;
    }
     
    @FXML
    public void ajouterButtonAction(ActionEvent event) throws IOException{
        Parent rootView = FXMLLoader.load(getClass().getResource("/familytree/view/addForm.fxml"));
        Scene rootScene = new Scene(rootView);
        Stage appStage = new Stage();     
        appStage.setScene(rootScene);
        appStage.show(); 
    }
    
    @FXML
    public void modifierButtonAction(ActionEvent event) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/familytree/view/modificationForm.fxml"));
            Parent rootView = loader.load();
            Scene rootScene = new Scene(rootView);
            Stage appStage = new Stage();     
            appStage.setScene(rootScene);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
    }
    
    @FXML
    public void supprimerButtonAction(ActionEvent event) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/familytree/view/confirmation.fxml"));
            Parent rootView = loader.load();
            Scene rootScene = new Scene(rootView);
            Stage appStage = new Stage();     
            appStage.setScene(rootScene);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void configurerButtonAction(ActionEvent event) throws IOException{ 
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/familytree/view/configuration.fxml"));
            Parent rootView = loader.load();
            ConfigurationController configurationController = (ConfigurationController)loader.getController();
            configurationController.setUser(this.Username);

            Scene rootScene = new Scene(rootView);
            Stage appStage = new Stage();     
            appStage.setScene(rootScene);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void sortirButtonAction(ActionEvent event) throws IOException{
        try {
            if(event.getSource().equals(sortir)) ((Node)(event.getSource())).getScene().getWindow().hide();
            //else if (event.getSource().equals(sortirItem))  ((Node)(event.getSource())).getScene().getWindow().hide();
            Pane root= FXMLLoader.load(getClass().getResource("/familytree/login/login.fxml"));
            //get the login controller
       
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
            //close all windows when the primary window have been closed
            primaryStage.setOnCloseRequest(e->Platform.exit());
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }   
    
    
    public void aboutItemAction(ActionEvent event)throws IOException{
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/familytree/view/about.fxml"));
            Parent rootView = loader.load();          
            Scene rootScene = new Scene(rootView);
            Stage appStage = new Stage();     
            appStage.setScene(rootScene);
            appStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
