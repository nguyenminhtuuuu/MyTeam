/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.services;

import com.nmt.myteamapp.pojo.Category;
import com.nmt.myteamapp.pojo.Choice;
import com.nmt.myteamapp.pojo.Question;
import com.nmt.myteamapp.utils.JdbcConnector;
import com.nmt.myteamapp.utils.MyAlert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author DELL
 */
public class updateQuestion {
    
    
    public static List<Choice> getChoiceByQuestion(String questionId) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT* FROM  choice WHERE question_id=?";
        conn.setAutoCommit(false);
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, questionId);
        
        ResultSet rs = stm.executeQuery();
        List<Choice> choices = new ArrayList<>();
        while(rs.next()){
            Choice c = new Choice(rs.getString("id"), rs.getString("content")
                    , rs.getBoolean("is_corect"), rs.getString("question_id"));
            choices.add(c);
        }
        
        conn.commit();
        return choices;
        
    }
    public static List<Question> getQuestion(Category category) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM question WHERE category_id=?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setInt(1, category.getId());
        ResultSet rs = stm.executeQuery();
        List<Question> questions = new ArrayList<>();
        while(rs.next()){
            Question.Builder b = new Question.Builder(rs.getString("id")
                    , rs.getString("content"), rs.getInt("category_id"));
            Question q = b.build();
            List<Choice> choices = getChoiceByQuestion(q.getQuestionId());
            q.setChoices(choices);
            questions.add(q);
        }
        return questions;
        
    }
    public static List<Question> getQuestion(String kw) throws SQLException{
        String sql = "SELECT * FROM question";
        if(!kw.isEmpty())
            sql += " WHERE content like ?";
        
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall(sql);
        if(!kw.isEmpty())
            stm.setString(1,"%" + kw + "%");//String.format("%%S%%", kw)
        
        ResultSet rs = stm.executeQuery();
        
        List<Question> questions = new ArrayList<>();
        while(rs.next()){
            Question.Builder b = new Question.Builder(rs.getString("id")
                    , rs.getString("content"), rs.getInt("category_id"));
            Question q = b.build();
            List<Choice> choices = getChoiceByQuestion(q.getQuestionId());
            q.setChoices(choices);
            questions.add(q);
        }
        return questions;
    }
    public static void deleteQuestion(String questionId) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sqlChoice = "DELETE FROM choice WHERE  question_id=?";
        String sqlQuestion = "DELETE FROM question WHERE id=?";
        PreparedStatement stmChoice = conn.prepareCall(sqlChoice);
        PreparedStatement stmQuestion = conn.prepareCall(sqlQuestion);
        
        //xoa choice
        stmChoice.setString(1, questionId);
        stmChoice.executeUpdate();
        //xoa cau hoi
        stmQuestion.setString(1, questionId);      
        stmQuestion.executeUpdate();
        conn.commit();
    }
    public static void addQuestion(Question q) throws SQLException{
        String sql = "INSERT INTO question(id, content, category_id) VALUES(?, ?,?)";
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getQuestionId());
        stm.setString(2, q.getContent());
        stm.setInt(3, q.getCategory().getId());
        
        if(stm.executeUpdate()> 0 ){
            int questionId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next())
                questionId = rs.getInt(1);
            
            String sql2 = "INSERT INTO choice(id, content, is_corect, question_id) VALUES(?,?,?,?)";
            PreparedStatement st = conn.prepareCall(sql2);
            for(var c: q.getChoices()){
                conn.prepareCall(sql);
                st.setString(1, c.getId());
                st.setString(2, c.getContent());
                st.setBoolean(3, c.isIsCorrect());
                st.setString(4, c.getQuestionId());
                st.executeUpdate();
            }
        }
        else
            conn.rollback();
        
        conn.commit();
        
    }
}
