/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.pojo;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Exam {
    private LocalDateTime date;
    private String title;
    private List<Question> questions;

    public Exam(List<Question> questions) {
        this.title = String.format("Exam - %s", LocalDateTime.now());
        this.date = LocalDateTime.now();
        this.questions = questions;
    }

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    
}
