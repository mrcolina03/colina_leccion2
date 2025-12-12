package espe.edu.ec.colinaleccion2.service;

import espe.edu.ec.colinaleccion2.model.Currency;
import espe.edu.ec.colinaleccion2.model.OrderStatus;
import espe.edu.ec.colinaleccion2.model.PurchaseOrder;
import espe.edu.ec.colinaleccion2.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository repository;

    // Método original de creación
    public PurchaseOrder createOrder(PurchaseOrder order) {
        return repository.save(order);
    }

    // Método original de búsqueda por ID
    public Optional<PurchaseOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    // NUEVO: Método de búsqueda con filtros
    public List<PurchaseOrder> getOrdersWithFilters(String q, OrderStatus status, Currency currency,
                                                    BigDecimal minTotal, BigDecimal maxTotal,
                                                    LocalDateTime from, LocalDateTime to) {
        return repository.searchOrders(q, status, currency, minTotal, maxTotal, from, to);
    }
}