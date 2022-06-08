package com.rafaelvieira.letmebuy.controllers;

import javax.validation.Valid;
import com.rafaelvieira.letmebuy.dto.EmailDTO;
import com.rafaelvieira.letmebuy.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private AuthService service;

//    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
//    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
//        User user = service.authenticated();
//        response.addHeader("Authorization", "Bearer ");
//        response.addHeader("access-control-expose-headers", "Authorization");
//        return ResponseEntity.noContent().build();
//    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
