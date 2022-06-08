package com.rafaelvieira.letmebuy.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rafaelvieira.letmebuy.controllers.exceptions.FieldMessage;
import com.rafaelvieira.letmebuy.dto.CostumerNewDTO;
import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.enums.TypeCostumer;
import com.rafaelvieira.letmebuy.repository.UserRepository;
import com.rafaelvieira.letmebuy.utils.DocumentValidity;

import org.springframework.beans.factory.annotation.Autowired;

public class CostumerInsertValidator implements ConstraintValidator<CostumerInsert, CostumerNewDTO> {

    @Autowired
    private UserRepository repo;

    @Override
    public void initialize(CostumerInsert ann) {
    }

    @Override
    public boolean isValid(CostumerNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(TypeCostumer.PESSOAFISICA.getCode()) && !DocumentValidity.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getType().equals(TypeCostumer.PESSOAJURIDICA.getCode()) && !DocumentValidity.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        User aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
