package com.rafaelvieira.letmebuy.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rafaelvieira.letmebuy.controllers.exceptions.FieldMessage;
import com.rafaelvieira.letmebuy.dto.UserInsertDTO;
import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.repository.UserRepository;


public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    private final UserRepository repository;

    public UserInsertValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(UserInsertValid ann) {
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        // Area onde efetua os testes de validação
        User user = repository.findByEmail(dto.getEmail());
        if (user != null) {
            list.add(new FieldMessage("email", "Email já existe"));
        }

        //Implementa no fieldmensage os erros de validação
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
