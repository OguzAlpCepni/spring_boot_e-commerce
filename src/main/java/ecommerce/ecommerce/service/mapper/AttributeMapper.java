package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.AttributeDtos.CreateAttributeRequest;
import ecommerce.ecommerce.model.Attribute;
import org.springframework.stereotype.Component;


@Component
public class AttributeMapper {

    public Attribute mappingDtoToAttributeEntity(CreateAttributeRequest createAttributeRequest){

        return new Attribute(
                0,
                createAttributeRequest.getName(),
                createAttributeRequest.getType()
        );

    }
}
