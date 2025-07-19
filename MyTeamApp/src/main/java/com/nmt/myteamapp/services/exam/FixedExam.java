/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.services.exam;

import com.nmt.myteamapp.pojo.Question;
import com.nmt.myteamapp.services.updateQuestion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class FixedExam extends BaseExamService{
    double [] rates = {0.4,0.4,0.2};
    

    @Override
    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        for(var i = 0; i < rates.length;i++ ){
            questions.addAll(updateQuestion.getQuestion(i+1, (int)(rates[i] * 10)));
        }
        return questions;
    }
    
}
