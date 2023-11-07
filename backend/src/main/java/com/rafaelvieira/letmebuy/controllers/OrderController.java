package com.rafaelvieira.letmebuy.controllers;

import com.rafaelvieira.letmebuy.dto.*;
import com.rafaelvieira.letmebuy.entities.Order;
import com.rafaelvieira.letmebuy.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author rafae
 */

@RestController
@RequestMapping(value="/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> find(@PathVariable Integer id) {
        Order obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Order obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Order>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Order> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/by-orders")
    public ResponseEntity<Page<OrderDTO>> oders(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "status", defaultValue = "") String status,
            Pageable pageable) {
        Page<OrderDTO> page = service.orders(minDate, maxDate, status, pageable);
        return ResponseEntity.ok(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/by-costumer")
    public ResponseEntity<List<OrderByCostumerDTO>> orderByCostumer(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "status", defaultValue = "") String status) {
        List<OrderByCostumerDTO> list = service.orderByCostumer(minDate, maxDate, status);
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/by-payment-method")
    public ResponseEntity<List<OrderByPaymentMethodDTO>> orderByPaymentMethod(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "method", defaultValue = "") String method) {
        List<OrderByPaymentMethodDTO> list = service.orderByPaymentMethod(minDate, maxDate, method);
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/by-date")
    public ResponseEntity<List<OrderByDateDTO>> orderByDate(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "status", defaultValue = "") String status) {
        List<OrderByDateDTO> list = service.orderByDate(minDate, maxDate, status);
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/summary")
    public ResponseEntity<OrderSummaryDTO> orderSummary(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(value = "status", defaultValue = "") String status) {
        OrderSummaryDTO obj = service.orderSummary(minDate, maxDate, status);
        return ResponseEntity.ok(obj);
    }
}
