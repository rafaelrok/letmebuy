package com.rafaelvieira.letmebuy.services;

import java.util.Date;
import java.util.Optional;

import com.rafaelvieira.letmebuy.entities.*;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import com.rafaelvieira.letmebuy.repository.OrderItemRepository;
import com.rafaelvieira.letmebuy.repository.OrderRepository;
import com.rafaelvieira.letmebuy.repository.PaymentRepository;
import com.rafaelvieira.letmebuy.services.email.EmailService;
import com.rafaelvieira.letmebuy.services.handlers.ObjectNotFoundException;
import com.rafaelvieira.letmebuy.services.handlers.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * @author rafae
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CostumerService costumerService;

    @Autowired
    private EmailService emailService;

    public Order find(Integer id) {
        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
    }

    public Order insert(Order obj) {
        obj.setId(null);
        obj.setInstant(new Date().toInstant());
        obj.setCostumer(costumerService.find(obj.getCostumer().getId()));
        obj.getPayment().setStatus(TypePayment.PENDENTE);
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentTicket) {
            PaymentTicket ticket = (PaymentTicket) obj.getPayment();
            TicketService.fillPaymentWithTicket(ticket, obj.getInstant());
        }
        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for (OrderItem orderItem : obj.getItens()) {
            orderItem.setDiscount(0.0);
            orderItem.setProduct(productService.find(orderItem.getProduct().getId()));
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setOrder(obj);
        }
        orderItemRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }

    public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        User user = UserService.authenticated();
        if (user == null) {
            throw new UnauthorizedException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Costumer costumer =  costumerService.find(user.getId());
        return repo.findByCostumer(costumer, pageRequest);
    }
}
