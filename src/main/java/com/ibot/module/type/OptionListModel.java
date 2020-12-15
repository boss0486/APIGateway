/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.type;

/**
 *
 * @author Allen
 */
public class OptionListModel {

    public int id;
    public String code;
    public String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public OptionListModel(int id, String code, String Title) {
        this.id = id;        
        this.code = code; 
        this.title = Title;
    }

}
