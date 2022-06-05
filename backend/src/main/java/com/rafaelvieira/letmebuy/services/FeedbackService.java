package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.FeedbackDTO;
import com.rafaelvieira.letmebuy.entities.Feedback;
import com.rafaelvieira.letmebuy.entities.Product;
import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.repository.FeedbackRepository;
import com.rafaelvieira.letmebuy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthService authService;

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        copyDtoToEntity(feedbackDTO, feedback);
        feedback = feedbackRepository.save(feedback);
        return new FeedbackDTO(feedback);
    }
    private void copyDtoToEntity(FeedbackDTO dto, Feedback entity) {
        Product product = productRepository.getOne(dto.getProductId());
        User user = authService.authenticated();
        authService.validateSelfOrAdmin(user.getId());

        entity.setProduct(product);
        entity.setUser(user);
        entity.setText(dto.getText());
    }
}
