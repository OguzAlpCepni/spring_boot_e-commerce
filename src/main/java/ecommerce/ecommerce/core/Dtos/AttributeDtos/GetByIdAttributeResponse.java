package ecommerce.ecommerce.core.Dtos.AttributeDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdAttributeResponse {
    private int id;

    private String name;

    private String type;
}
