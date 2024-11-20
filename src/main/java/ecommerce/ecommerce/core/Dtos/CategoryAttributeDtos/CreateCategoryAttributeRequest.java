package ecommerce.ecommerce.core.Dtos.CategoryAttributeDtos;

import ecommerce.ecommerce.model.Attribute;
import ecommerce.ecommerce.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryAttributeRequest {



    private Category category;


    private Attribute attribute;

}
