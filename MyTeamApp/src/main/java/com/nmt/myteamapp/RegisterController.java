/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nmt.myteamapp;

import com.nmt.myteamapp.pojo.User;
import com.nmt.myteamapp.services.updateUser;
import com.nmt.myteamapp.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegisterController implements Initializable {
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    
    public void handleRegister(ActionEvent event) throws SQLException{
        User.Builder b = new User.Builder(this.txtUsername.getText()
                                , this.txtPassword.getText());
        User u = b.build();
        try{
            updateUser.addUser(u);
            MyAlert.getInstance().showMsg("Đăng ký thành công!", Alert.AlertType.INFORMATION);
        }catch(SQLException ex){
            MyAlert.getInstance().showMsg("Đăng ký thất bại!" + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
