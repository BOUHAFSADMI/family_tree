/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familytree.controller;

import familytree.model.FormModel;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author DarkSnow
 */
public class FormController {
    
     @FXML
    public TextField name;
    @FXML
    public TextField lastName;
    @FXML
    public DatePicker birth;
    @FXML
    public ComboBox sexe;
    @FXML
    public TextField place;
    @FXML
    public DatePicker death;
    @FXML
    public TextField image;
    @FXML
    public ComboBox<Person> father;
    @FXML
    public ComboBox<Person> mother;
    @FXML
    public ComboBox<Person> partner;
    @FXML
    public DatePicker weddingDate;
    @FXML
    public Button btnValider;
  
    
    public FormModel formModel= new FormModel();
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
   
    
    public void loadPersons(final TextField lastName,final DatePicker datePicker, ComboBox fathers ) { 
        lastName.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            ResultSet rs = null;
            try {
                rs = formModel.getFathers(lastName.getText(),datePicker.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fathers.getItems().clear();
            try {
                if(rs!=null)
                    while(rs.next())
                        fathers.getItems().add(new Person(rs.getInt(1)," ", rs.getString(2),' ', rs.getDate(3),
                                null,null,null,null,null,null,null));
            } catch (SQLException ex) {
                Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        datePicker.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            ResultSet rs = null;
            try {
                rs = formModel.getFathers(lastName.getText(),datePicker.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fathers.getItems().clear();
            try {
                if(rs!=null)
                    while(rs.next())
                        fathers.getItems().add(new Person(rs.getInt(1)," ", rs.getString(2),' ', rs.getDate(3),
                                null,null,null,null,null,null,null));
            } catch (SQLException ex) {
                Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
     public void loadPersons(final DatePicker datePicker, ComboBox mothers) { 
        datePicker.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            ResultSet rs=null;
            try {
                rs = formModel.getMothers(datePicker.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mothers.getItems().clear();
            try {
                if(rs!=null)
                    while(rs.next())                        
                        mothers.getItems().add(new Person(rs.getInt(1),rs.getString(2),rs.getString(3),' ',rs.getDate(4),
                                null,null,null,null,null,null,null));
            } catch (SQLException ex) {
                Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
     
    public void loadPersons(final ComboBox sexe,ComboBox partners) { 
        sexe.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                ResultSet rs = null ;
                if(sexe.getValue().toString().charAt(0)=='M') try {
                    rs = formModel.getPartners("F");
                } catch (SQLException ex) {
                    Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                else try {
                    rs = formModel.getPartners("M");
                } catch (SQLException ex) {
                    Logger.getLogger(FormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                partners.getItems().clear();
                try {
                    if(rs!=null)
                        while(rs.next()) 
                            partners.getItems().add(rs.getString(1) + " " + rs.getString(2)+ " " + rs.getString(3)+" "+rs.getString(4));
                } catch (SQLException ex) {
                    Logger.getLogger(AddFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void configureFileChooser(final FileChooser fileChooser) {      
            fileChooser.setTitle("Choisir une image");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(               
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"), 
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
            );
    }
 
    @FXML
    public void parcourirButtonAction(ActionEvent event)
    {
       Stage stage = new Stage();
       FileChooser fileChooser = new FileChooser();
       FormController.configureFileChooser(fileChooser);
       fileChooser.setTitle("Choisir une image");
       File file = fileChooser.showOpenDialog(stage); 
       if(file!=null) image.setText(file.getPath());     
    }
    
    
    @FXML
     public void annulerButtonAction(ActionEvent event)
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
  
}


    
    /*
    public void setUpValidation(final TextField textField) { 
        textField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                validate(textField);
            }
        });

        validate(textField);
    }

    public void validate(TextField textField) {
        ObservableList<String> styleClass = textField.getStyleClass();
        if (textField.getText().trim().length()==0) {
            if (! styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.removeAll(Collections.singleton("error"));  
        }
    }
    
    */