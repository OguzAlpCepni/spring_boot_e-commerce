package ecommerce.ecommerce.service.BusinessRules;

import ecommerce.ecommerce.core.exceptions.NoProductFoundException;
import ecommerce.ecommerce.core.exceptions.SkuIsNotUnique;
import ecommerce.ecommerce.model.Product;
import ecommerce.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;

@Component
public class ProductBusinessRules {
    private final ProductRepository productRepository;

    public ProductBusinessRules(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void checkProducts(List<Product> products) {
        if(products.isEmpty()){
            throw new NoProductFoundException("No products avaible in the repository");
        }
    }

    public void CheckProductSkuIsUnique(String sku){
        boolean exists = productRepository.existsById(sku);
        if(exists){
            throw new SkuIsNotUnique("Product Sku is already in use , sku must be unique: "+sku);

        }
    }
}
