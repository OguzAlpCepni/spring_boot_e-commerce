package ecommerce.ecommerce.core.Dtos.CategoryDtos;

import ecommerce.ecommerce.model.CategoryAttribute;
import ecommerce.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {

    private String categoryName;


    private Set<Product> products = new HashSet<Product>();


    private Set<CategoryAttribute> categoryAttributes;
}
