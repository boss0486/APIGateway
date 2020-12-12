/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibot.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Allen
 */
@RestController
public class TestAPI {
    @GetMapping("/api/test")
    public ResponseEntity<String> Test01() {
        return ResponseEntity.ok("Success fully");
    }
}
