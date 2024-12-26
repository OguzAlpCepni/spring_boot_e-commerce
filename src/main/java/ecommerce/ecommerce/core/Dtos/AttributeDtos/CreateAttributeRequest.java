package ecommerce.ecommerce.core.Dtos.AttributeDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttributeRequest {

    @NotBlank(message = "Attribute name cannot be empty")
    private String name;
    @NotBlank(message = "attribute type cannot be empty")
    private String type; // (color, size, weight gibi)
}
