package com.rafaelvieira.letmebuy.controllers;

import com.rafaelvieira.letmebuy.dto.EmailDTO;
import com.rafaelvieira.letmebuy.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody EmailDTO dto){
        service.sendEmail(dto);
        return ResponseEntity.noContent().build();
    }
}