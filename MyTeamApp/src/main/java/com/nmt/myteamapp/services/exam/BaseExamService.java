/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.services.exam;

import com.nmt.myteamapp.pojo.Exam;
import com.nmt.myteamapp.pojo.Question;
import com.nmt.myteamapp.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public abstract class BaseExamService {
    public abstract List<Question> getQuestions() throws SQLException;
    
    public void addExam(List<Question> questions) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO exam(Title, created_date) VALUES(?,?)";
        PreparedStatement stm = conn.prepareCall(sql);
        Exam ex = new Exam(questions);
        stm.setString(1, ex.getTitle());
        stm.setString(2, ex.getDate().toString());
       
        if(stm.executeUpdate()>0){
            conn.commit();
        }
        else{
            conn.rollback();
        }
        
    }
    
}
