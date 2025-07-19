/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.services;

import com.nmt.myteamapp.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public abstract class BaseServices<T> {
    public abstract PreparedStatement getStm(Connection conn) throws SQLException;
    public abstract List<T>getResults(ResultSet rs) throws SQLException;
    
    public List<T> list() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = this.getStm(conn);
        return this.getResults(stm.executeQuery());             
    }
}

//Truy vấn dữ liệu từ database theo đối tượng
//pub abstr class BaseServices<T> {
//    pub abs PreparedStatement getStm(Connection conn) throws ...;
//    pub abs List<T>getResults(ResultSet rs) throws...;
//  
//    pub List<T> list() throws...{
//        Connection conn = JdbcConnector.getInstance().connect();
//        PreparedStatement stm = this.getStm(conn);
//        return this.getResults(stm.executeQuery());             
//    }
//}

//pub class CategoryServices extends BaseServices<Category>{
//    @ PreparedStatement{return conn.prepareCall("SELECT * FROM category");}

//    @ getResults(ResultSet rs) {
//        List<Category> cates = new ArrayList<>();
//        while(rs.next()){
//            Category c = new Category(rs.getInt("id"), rs.getString("name"));
//            cates.add(c);
//        }
//        return cates;
//    } 
//}

