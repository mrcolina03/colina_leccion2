package espe.edu.ec.colinaleccion2.repository;

import espe.edu.ec.colinaleccion2.model.Currency;
import espe.edu.ec.colinaleccion2.model.OrderStatus;
import espe.edu.ec.colinaleccion2.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("SELECT p FROM PurchaseOrder p WHERE " +
            "(:q IS NULL OR (LOWER(p.orderNumber) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(p.supplierName) LIKE LOWER(CONCAT('%', :q, '%')))) AND " +
            "(:status IS NULL OR p.status = :status) AND " +
            "(:currency IS NULL OR p.currency = :currency) AND " +
            "(:minTotal IS NULL OR p.totalAmount >= :minTotal) AND " +
            "(:maxTotal IS NULL OR p.totalAmount <= :maxTotal) AND " +
            "(:from IS NULL OR p.createdAt >= :from) AND " +
            "(:to IS NULL OR p.createdAt <= :to)")
    List<PurchaseOrder> searchOrders(
            @Param("q") String q,
            @Param("status") OrderStatus status,
            @Param("currency") Currency currency,
            @Param("minTotal") BigDecimal minTotal,
            @Param("maxTotal") BigDecimal maxTotal,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}