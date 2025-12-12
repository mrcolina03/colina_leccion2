package espe.edu.ec.colinaleccion2.service;

import espe.edu.ec.colinaleccion2.model.PurchaseOrder;
import espe.edu.ec.colinaleccion2.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository repository;

    public List<PurchaseOrder> getAllOrders() {
        return repository.findAll();
    }

    public PurchaseOrder createOrder(PurchaseOrder order) {
        // Aquí podrías validar que el status inicial sea DRAFT si quisieras
        return repository.save(order);
    }

    public Optional<PurchaseOrder> getOrderById(Long id) {
        return repository.findById(id);
    }
}