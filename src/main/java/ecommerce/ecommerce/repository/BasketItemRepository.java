package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, String> {

}
