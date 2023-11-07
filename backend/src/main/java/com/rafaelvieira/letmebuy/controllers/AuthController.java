package com.rafaelvieira.letmebuy.controllers;

import javax.validation.Valid;
import com.rafaelvieira.letmebuy.dto.EmailDTO;
import com.rafaelvieira.letmebuy.dto.NewPasswordDTO;
import com.rafaelvieira.letmebuy.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author rafae
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService service) {
        this.authService = service;
    }

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO dto) {
        authService.createRecoverToken(dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/new-password")
    public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDTO dto) {
        authService.saveNewPassword(dto);
        return ResponseEntity.noContent().build();
    }

//    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
//    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
//        service.sendNewPassword(objDto.getEmail());
//        return ResponseEntity.noContent().build();
//    }
}
