/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.module.entities;

/**
 *
 * @author Allen
 */
public class Api01TopupRequest { 

    public String api_key;
    public String card_seri;
    public String card_code;
    public String request_id;
    public int card_amount;
    public String card_type;
    public String signature; 
}
