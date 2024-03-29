package com.rafaelvieira.letmebuy.services.validation;

import com.rafaelvieira.letmebuy.controllers.exceptions.FieldMessage;
import com.rafaelvieira.letmebuy.dto.UserDTO;
import com.rafaelvieira.letmebuy.entities.User;

import com.rafaelvieira.letmebuy.repository.UserRepository;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CostumerUpdateValidator implements ConstraintValidator<CostumerUpdate, UserDTO> {


    private final HttpServletRequest request;
    private final UserRepository repo;

    public CostumerUpdateValidator(HttpServletRequest request, UserRepository repo) {
        this.request = request;
        this.repo = repo;
    }

    @Override
    public void initialize(CostumerUpdate ann) {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EC_UNRELATED_TYPES")
    @Override
    public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        User aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
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
