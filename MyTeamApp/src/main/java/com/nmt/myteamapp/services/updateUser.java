/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.services;

import com.nmt.myteamapp.pojo.User;
import com.nmt.myteamapp.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author DELL
 */
public class updateUser {
    public static void addUser(User u) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "INSERT INTO user(username, password) VALUES(?,?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, u.getUsername());
        stm.setString(2, u.getPassword());
        
        stm.executeUpdate();
    }
    public static boolean checkUser(User u) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM user WHERE username=? AND password =?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, u.getUsername() );
        stm.setString(2, u.getPassword());
        
        ResultSet rs =  stm.executeQuery();
        return rs.next();
    }
}
