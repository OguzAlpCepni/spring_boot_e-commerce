package ecommerce.ecommerce.core.Dtos.AttributeDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttributeRequest {


    private String name;

    private String type; // (color, size, weight gibi)
}
