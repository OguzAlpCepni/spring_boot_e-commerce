package ecommerce.ecommerce.core.Dtos.ProductDtos;

import ecommerce.ecommerce.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductByIdRequest {

    @NotBlank(message = "Product name cannot be empty")
    private String productName;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotNull(message = "Stock quantity cannot be null")
    @Positive(message = "Stock quantity must be greater than zero")
    private int unitInStock;
    @NotBlank(message = "SKU cannot be empty")
    private String sku;
    @NotNull(message = "Categories cannot be null")
    private Set<Category> categories = new HashSet<Category>();
}
