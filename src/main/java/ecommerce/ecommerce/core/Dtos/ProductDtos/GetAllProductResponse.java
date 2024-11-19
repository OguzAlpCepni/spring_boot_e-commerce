package ecommerce.ecommerce.core.Dtos.ProductDtos;

import ecommerce.ecommerce.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {

    private String productId;
    private String productName;
    private BigDecimal price;
    private String description;
    private int unitInStock;
    private String sku;
    private Set<Category> categories = new HashSet<Category>();
}
