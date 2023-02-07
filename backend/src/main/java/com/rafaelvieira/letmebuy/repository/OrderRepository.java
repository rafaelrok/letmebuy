package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.dto.OrderByCostumerDTO;
import com.rafaelvieira.letmebuy.dto.OrderByDateDTO;
import com.rafaelvieira.letmebuy.dto.OrderByPaymentMethodDTO;
import com.rafaelvieira.letmebuy.dto.OrderSummaryDTO;
import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.Order;
import com.rafaelvieira.letmebuy.entities.PaymentMethod;
import com.rafaelvieira.letmebuy.entities.User;
import com.rafaelvieira.letmebuy.enums.TypePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author rafae
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional(readOnly=true)
    Page<Order> findByUser(User user, Pageable pageRequest);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByCostumerDTO(obj.user, SUM(obj.amount)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "AND (:typePayment IS NULL OR obj.payment.typePayment = :typePayment) "
            + "GROUP BY obj.user")
    List<OrderByCostumerDTO> orderByCostumer(LocalDate min, LocalDate max, TypePayment typePayment);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByPaymentMethodDTO(obj.payment.paymentMethod, SUM(obj.amount)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "AND (:typePayment IS NULL OR obj.payment.typePayment = :typePayment) "
            + "GROUP BY obj.payment.paymentMethod")
    List<OrderByPaymentMethodDTO> orderByPaymentMethod(LocalDate min, LocalDate max, TypePayment typePayment);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByDateDTO(obj.date, SUM(obj.amount)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "AND (:typePayment IS NULL OR obj.payment.typePayment = :typePayment) "
            + "GROUP BY obj.date")
    List<OrderByDateDTO> orderByDate(LocalDate min, LocalDate max, TypePayment typePayment);

    @Query("SELECT obj FROM Order obj "
            + "JOIN FETCH obj.user "
            + "JOIN FETCH obj.payment "
            + "WHERE obj in :orders")
    List<Order> orderWithOtherEntities(List<Order> orders);

    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderSummaryDTO(SUM(obj.amount), MAX(obj.amount), MIN(obj.amount), AVG(obj.amount), COUNT(obj.id)) "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "AND (:typePayment IS NULL OR obj.payment.typePayment = :typePayment) ")
    OrderSummaryDTO orderSummary(LocalDate min, LocalDate max, TypePayment typePayment);

    @Query("SELECT obj "
            + "FROM Order AS obj "
            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
            + "AND (:typePayment IS NULL OR obj.payment.typePayment = :typePayment) ")
    Page<Order> searchPage(LocalDate min, LocalDate max, TypePayment typePayment, Pageable pageable);

    //Query para buscar pedidos por metodo de pagamento
//    @Query("SELECT new com.rafaelvieira.letmebuy.dto.OrderByPaymentMethodDTO(pm.description, SUM(obj.amount)) "
//            + "FROM Order AS obj "
//            + "INNER JOIN Payment AS p ON obj.payment = p "
//            + "INNER JOIN PaymentMethod AS pm ON p.paymentMethod = pm "
//            + "WHERE (CAST(:min AS date) IS NULL OR obj.date >= :min) "
//            + "AND (CAST(:max AS date) IS NULL OR obj.date <= :max) "
//            + "AND (:method IS NULL OR pm.description = :method) "
//            + "GROUP BY pm.description")
//    Page<Order> searchOrderByPaymentMethod(LocalDate min, LocalDate max, PaymentMethod method, Pageable pageable);
}


