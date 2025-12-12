package espe.edu.ec.colinaleccion2.repository;

import espe.edu.ec.colinaleccion2.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    // Puedes agregar búsqueda por orderNumber si lo necesitas
    // Optional<PurchaseOrder> findByOrderNumber(String orderNumber);
}