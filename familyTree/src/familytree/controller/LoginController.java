/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.LoginModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DarkSnow
 */
public class LoginController implements Initializable {

    
    @FXML private Label message;
    private BorderPane rootLayout;
    private Stage primaryStage;
    @FXML private TextField username;
    @FXML private PasswordField password;
    public LoginModel loginModel = new LoginModel();
    
   
    
      /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert message != null :"fx:id=\"message";
        assert username != null :"fx:id=\"username";
        assert password != null :"fx:id=\"password";
        
        
        if(!loginModel.isDBConnected())
        {
            message.setText("Connection échouée");
            message.setTextFill(Color.rgb(210, 39, 30));
        }
        else 
        {
            message.setText("Connecté");
            message.setTextFill(Color.rgb(21, 117, 84));
        }
    }    
    
  
    
    
    @FXML
    public void loginButtonAction(ActionEvent event) throws IOException{
        try {
            if( true || loginModel.isLogin(username.getText(),password.getText())){ //TO CORRECT
                initRootLayout();
                showPersonOverview(); 
                //hide login window
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }else{
                message.setText("Donnée(s) erronée(s)!");
                message.setTextFill(Color.rgb(210, 39, 30));
            } 
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
    
    public void initRootLayout(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("/familytree/app/app.fxml"));
            rootLayout = (BorderPane) loader.load();
           
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/familytree/img/tree.png"));
            primaryStage.show();
            //close all windows when the primary window have been closed
            primaryStage.setOnCloseRequest(e->Platform.exit());
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public void showPersonOverview(){
        try {
            // Load personOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("/familytree/view/personOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set personOverview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
}
