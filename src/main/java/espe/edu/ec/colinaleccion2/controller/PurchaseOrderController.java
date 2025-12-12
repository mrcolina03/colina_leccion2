package espe.edu.ec.colinaleccion2.controller;

import espe.edu.ec.colinaleccion2.model.Currency;
import espe.edu.ec.colinaleccion2.model.OrderStatus;
import espe.edu.ec.colinaleccion2.model.PurchaseOrder;
import espe.edu.ec.colinaleccion2.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAll(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) Currency currency,
            @RequestParam(required = false) BigDecimal minTotal,
            @RequestParam(required = false) BigDecimal maxTotal,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        // VALIDACIONES DE NEGOCIO (Retornan 400 Bad Request)

        // 4. y 5. Validación de montos negativos
        if ((minTotal != null && minTotal.compareTo(BigDecimal.ZERO) < 0) ||
                (maxTotal != null && maxTotal.compareTo(BigDecimal.ZERO) < 0)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los montos no pueden ser negativos");
        }

        // 6. Validación cruzada de fechas (from <= to)
        if (from != null && to != null && from.isAfter(to)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha 'from' no puede ser mayor que 'to'");
        }

        // Llamada al servicio
        List<PurchaseOrder> orders = service.getOrdersWithFilters(q, status, currency, minTotal, maxTotal, from, to);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder purchaseOrder) {
        return new ResponseEntity<>(service.createOrder(purchaseOrder), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable Long id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}