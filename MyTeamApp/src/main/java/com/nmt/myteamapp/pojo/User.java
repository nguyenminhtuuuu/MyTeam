/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.pojo;

/**
 *
 * @author DELL
 */
public class User {
    private String username;
    private String password;

    private User(Builder b){
        this.username = b.username;
        this.password = b.password;
    }
    
    public static class Builder{
        private String username;
        private String password;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public User build(){
            return new User(this);
        }
        
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
