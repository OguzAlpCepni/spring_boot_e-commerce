package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String > {
}
