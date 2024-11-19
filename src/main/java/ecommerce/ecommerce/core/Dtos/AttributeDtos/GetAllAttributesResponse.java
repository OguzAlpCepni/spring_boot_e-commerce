package ecommerce.ecommerce.core.Dtos.AttributeDtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAttributesResponse {

    private int id;

    private String name;

    private String type;
}
