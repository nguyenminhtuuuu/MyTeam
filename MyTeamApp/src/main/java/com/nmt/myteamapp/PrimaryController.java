package com.nmt.myteamapp;

import com.nmt.myteamapp.utils.MyAlert;
import com.nmt.myteamapp.utils.MySession;
import com.nmt.myteamapp.utils.MyStage;
import com.nmt.myteamapp.utils.theme.ThemeManager;
import com.nmt.myteamapp.utils.theme.theme;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable{
    @FXML private ComboBox<theme> cbTheme;
    
    public void handleTheme(ActionEvent event){
        this.cbTheme.getSelectionModel().getSelectedItem().updateTheme();
        ThemeManager.applyTheme(this.cbTheme.getScene());
    }
    public void handleQuestion(ActionEvent event) throws IOException{
        if(MySession.getInstance().getU() == null){
            MyAlert.getInstance().showMsg("Bạn chưa đăng nhập!", Alert.AlertType.WARNING);
            
        }
        else{
            MyStage.getInstance().showStage("Question.fxml");
        }
       
    }
    public void handleRegister(ActionEvent event) throws IOException{
        
        MyStage.getInstance().showStage("register.fxml");
    }
    public void handleLogin(ActionEvent event) throws IOException{
        MyStage.getInstance().showStage("login.fxml");
    }
    
    public void handleExam(ActionEvent event) throws IOException{
        if(MySession.getInstance().getU() == null){
            MyAlert.getInstance().showMsg("Bạn chưa đăng nhập!", Alert.AlertType.WARNING);
            
        }
        else{
            MyStage.getInstance().showStage("exam.fxml");
        }
       
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTheme.setItems(FXCollections.observableArrayList(theme.values()));
        
    }
  
}
