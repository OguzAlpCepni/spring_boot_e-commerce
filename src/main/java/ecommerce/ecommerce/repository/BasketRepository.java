package ecommerce.ecommerce.repository;

import ecommerce.ecommerce.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, String> {


}
