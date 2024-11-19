package ecommerce.ecommerce.core.Dtos.ProductDtos;

import ecommerce.ecommerce.model.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
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
    @Size(min = 3, max = 50, message = "sku must be between 3 and 50 characters.")
    private String sku;
    @NotNull(message = "Categories cannot be null")
    private Set<Category> categories = new HashSet<Category>();
}
