package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.dto.OrderByCostumerDTO;
import com.rafaelvieira.letmebuy.dto.OrderByDateDTO;
import com.rafaelvieira.letmebuy.dto.OrderByPaymentMethodDTO;
import com.rafaelvieira.letmebuy.dto.OrderSummaryDTO;
import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.Order;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * @author rafae
 */

//@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional(readOnly=true)
    Page<Order> findByCostumer(Costumer costumer, Pageable pageRequest);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByCostumerDTO(obj.costumer, SUM(obj.amount)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.instant >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.instant <= :max) "
            + "AND (:genderEnum IS NULL OR obj.payment.status = :typePayment) "
            + "GROUP BY obj.costumer")
    List<OrderByCostumerDTO> orderByCostumer(Instant min, Instant max, TypePayment typePayment);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByPaymentMethodDTO(obj.payment.paymentMethod, SUM(obj.amount)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.instant >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.instant <= :max) "
            + "AND (:genderEnum IS NULL OR obj.payment.status = :typePayment) "
            + "GROUP BY obj.payment.paymentMethod")
    List<OrderByPaymentMethodDTO> orderByPaymentMethod(Instant min, Instant max, TypePayment typePayment);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByDateDTO(obj.instant, SUM(obj.amount)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.instant >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.instant <= :max) "
            + "AND (:genderEnum IS NULL OR obj.payment.status = :typePayment) "
            + "GROUP BY obj.instant")
    List<OrderByDateDTO> orderByDate(Instant min, Instant max, TypePayment typePayment);

    @Query("SELECT DISTINCT obj "
            + "FROM Order AS obj "
            + "JOIN FETCH obj.costumer "
            + "JOIN FETCH obj.payment "
            + "JOIN FETCH obj.payment "
            + "WHERE obj.id in :orders")
    List<Order> orderWithOtherEntities(List<Order> orders);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderSummaryDTO(SUM(obj.amount), MAX(obj.amount), MIN(obj.amount), AVG(obj.amount), COUNT(obj.id)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.instant >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.instant <= :max) "
            + "AND (:genderEnum IS NULL OR obj.payment.status = :typePayment) ")
    OrderSummaryDTO orderSummary(Instant min, Instant max, TypePayment typePayment);

    @Query("SELECT obj "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.instant >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.instant <= :max) "
            + "AND (:genderEnum IS NULL OR obj.payment.status = :typePayment) ")
    Page<Order> searchPage(Instant min, Instant max, TypePayment typePayment, Pageable pageable);
}


