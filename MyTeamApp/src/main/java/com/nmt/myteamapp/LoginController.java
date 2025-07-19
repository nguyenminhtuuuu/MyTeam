/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nmt.myteamapp;

import com.nmt.myteamapp.pojo.User;
import com.nmt.myteamapp.services.updateUser;
import com.nmt.myteamapp.utils.MyAlert;
import com.nmt.myteamapp.utils.MySession;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginController implements Initializable {
    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;
    
    public void handleLogin(ActionEvent event){
        try {
            User.Builder b = new User.Builder(this.txtUsername.getText()
                                , this.txtPassword.getText());
            User u = b.build();
            MySession.getInstance().setU(u);
            updateUser.checkUser(u);
            MyAlert.getInstance().showMsg("Đăng nhập thành công!", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Đăng nhập thất bại!" + ex.getMessage(), Alert.AlertType.ERROR);
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
