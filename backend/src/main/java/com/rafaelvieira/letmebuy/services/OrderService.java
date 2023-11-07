package com.rafaelvieira.letmebuy.services;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.rafaelvieira.letmebuy.dto.*;
import com.rafaelvieira.letmebuy.entities.*;
import com.rafaelvieira.letmebuy.enums.OrderStatus;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import com.rafaelvieira.letmebuy.repository.OrderItemRepository;
import com.rafaelvieira.letmebuy.repository.OrderRepository;
import com.rafaelvieira.letmebuy.repository.PaymentRepository;
import com.rafaelvieira.letmebuy.services.email.EmailService;
import com.rafaelvieira.letmebuy.services.handlers.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rafae
 */

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TicketService ticketService;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CostumerService costumerService;
    private final UserService userService;
//    @Autowired(required = false)
    private final EmailService emailService;
    private final AuthService authService;

    public OrderService(OrderRepository orderRepository, TicketService ticketService, PaymentRepository paymentRepository,
                        OrderItemRepository orderItemRepository, ProductService productService, CostumerService costumerService,
                        UserService userService, EmailService emailService, AuthService authService) {
        this.orderRepository = orderRepository;
        this.ticketService = ticketService;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.costumerService = costumerService;
        this.userService = userService;
        this.emailService = emailService;
        this.authService = authService;
    }


    public Order find(Integer id) {
        User user = authService.authenticated();
        authService.validateSelfOrAdmin(user.getId());
        Optional<Order> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));
    }

    public Order insert(Order obj) {
        obj.setId(null);
        obj.setDate(obj.getDate());
        obj.setUser(userService.find(obj.getUser().getId()));
        obj.getPayment().setTypePayment(TypePayment.PENDENTE);
        obj.setStatus(OrderStatus.PENDENTE);
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentTicket) {
            PaymentTicket ticket = (PaymentTicket) obj.getPayment();
            TicketService.fillPaymentWithTicket(ticket, Instant.from(obj.getDate() == null ? LocalDate.now() : obj.getDate()));
        }
        obj = orderRepository.save(obj);
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
        User user = authService.authenticated();
        authService.validateSelfOrAdmin(user.getId());
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        User user1 =  userService.find(user.getId());
        return orderRepository.findByUser(user1, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> orders(String minDate, String maxDate, String status, Pageable pageable) {
        LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
        LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
        TypePayment typePayment = "".equals(status) ? null : TypePayment.valueOf(status);
        Page<Order> page = orderRepository.searchPage(min, max, typePayment, pageable);
        orderRepository.orderWithOtherEntities(page.getContent());
        return page.map(OrderDTO::new);
    }

    @Transactional(readOnly = true)
    public List<OrderByCostumerDTO> orderByCostumer(String minDate, String maxDate, String status) {
        LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
        LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
        TypePayment typePayment = "".equals(status) ? null : TypePayment.valueOf(status);
        return orderRepository.orderByCostumer(min, max, typePayment);
    }

    @Transactional(readOnly = true)
    public List<OrderByPaymentMethodDTO> orderByPaymentMethod(String minDate, String maxDate, String status) {
        LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
        LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
        TypePayment typePayment = "".equals(status) ? null : TypePayment.valueOf(status);
        return orderRepository.orderByPaymentMethod(min, max, typePayment);
    }

    @Transactional(readOnly = true)
    public List<OrderByDateDTO> orderByDate(String minDate, String maxDate, String status) {
        LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
        LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
        TypePayment typePayment = "".equals(status) ? null : TypePayment.valueOf(status);
        return orderRepository.orderByDate(min, max, typePayment);
    }

    @Transactional(readOnly = true)
    public OrderSummaryDTO orderSummary(String minDate, String maxDate, String status) {
        LocalDate min = "".equals(minDate) ? null : LocalDate.parse(minDate);
        LocalDate max = "".equals(maxDate) ? null : LocalDate.parse(maxDate);
        TypePayment typePayment = "".equals(status) ? null : TypePayment.valueOf(status);
        return orderRepository.orderSummary(min, max, typePayment);
    }
}
