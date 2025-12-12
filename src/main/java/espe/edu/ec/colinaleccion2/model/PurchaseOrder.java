package espe.edu.ec.colinaleccion2.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    private String supplierName;

    @Enumerated(EnumType.STRING) // Guarda el nombre (ej: "DRAFT") en la BD
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDate expectedDeliveryDate;

    // Asigna la fecha del sistema antes de guardar
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}