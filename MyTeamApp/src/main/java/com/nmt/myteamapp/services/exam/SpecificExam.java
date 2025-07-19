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
public class SpecificExam extends BaseExamService{
    private int num;

    public SpecificExam(int num) {
        this.num = num;
    }
    
    

    @Override
    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>()  ;
        questions.addAll(updateQuestion.getQuestion(this.num));
        return questions;
    }
    
}
