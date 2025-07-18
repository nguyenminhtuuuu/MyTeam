/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.pojo;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author DELL
 */
public class Question {
    private String QuestionId;
    private String content;
    private Category category;
    private int cateId;
    private List<Choice> choices;

    @Override
    public String toString() {
        return this.content;
    }
    
    
    private Question(Builder b){
        this.QuestionId = b.questionId;
        this.content = b.content;
        this.category = b.category;
        this.choices = b.choices;
        this.cateId = b.cateId;
    }
    public static class Builder{
        private String questionId;
        private String content;
        private Category category;
        private int cateId;
        private List<Choice> choices = new ArrayList<>();

        public Builder(String questionId, String content, Category category) {
            this.questionId = questionId;
            this.content = content;
            this.category = category;
        }

        public Builder(String questionId, String content,  int cateId) {
            this.questionId = questionId;
            this.content = content;
            this.cateId = cateId;
        }
        
       
        
        public Builder addChoice(Choice c){
            this.choices.add(c);
            return this;
        }
        public Question build(){
            return new Question(this);
        }   
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    /**
     * @return the QuestionId
     */
    public String getQuestionId() {
        return QuestionId;
    }

    /**
     * @param QuestionId the QuestionId to set
     */
    public void setQuestionId(String QuestionId) {
        this.QuestionId = QuestionId;
    }

    /**
     * @return the QuestionId
     */
    
    
    
    
    
    
}
