/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.myteamapp.utils;

import com.nmt.myteamapp.pojo.User;

/**
 *
 * @author DELL
 */
public class MySession {
    private static MySession instance;
    private User u;
    
    private MySession(){
        
    }
    public static MySession getInstance(){
        if(instance == null)
            instance = new MySession();
        return instance;
    }

    /**
     * @return the u
     */
    public User getU() {
        return u;
    }

    /**
     * @param u the u to set
     */
    public void setU(User u) {
        this.u = u;
    }
    
    
    
}
