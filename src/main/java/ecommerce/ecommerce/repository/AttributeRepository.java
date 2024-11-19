package ecommerce.ecommerce.repository;


import ecommerce.ecommerce.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute,Integer> {
}
