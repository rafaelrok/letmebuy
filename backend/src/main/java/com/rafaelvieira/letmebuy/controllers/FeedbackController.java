package com.rafaelvieira.letmebuy.controllers;
import com.rafaelvieira.letmebuy.dto.FeedbackDTO;
import com.rafaelvieira.letmebuy.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> save(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        feedbackDTO = feedbackService.save(feedbackDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(feedbackDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(feedbackDTO);
    }
}
