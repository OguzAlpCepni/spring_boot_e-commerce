package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.AttributeDtos.CreateAttributeRequest;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetAllAttributesResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetByIdAttributeResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.UpdateAttributeRequest;
import ecommerce.ecommerce.model.Attribute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AttributeMapper {

    public Attribute mappingDtoToAttributeEntity(CreateAttributeRequest createAttributeRequest){

        return new Attribute(
                0,
                createAttributeRequest.getName(),
                createAttributeRequest.getType()
        );

    }

    public GetAllAttributesResponse toGetAllAttributesResponse(Attribute attribute){
        return new GetAllAttributesResponse(
                attribute.getId(),
                attribute.getName(),
                attribute.getType()
        );
    }

    public List<GetAllAttributesResponse> mappingAttributesToGetAllAttributesResponse(List<Attribute> attributes){
        return attributes.stream().map(this::toGetAllAttributesResponse).collect(Collectors.toList());
    }

    public GetByIdAttributeResponse mappingAttributeToAttributeResponseDto(Attribute attribute){
        return new GetByIdAttributeResponse(
                attribute.getId(),
                attribute.getName(),
                attribute.getType()
        );
    }

    public void mappingUpdateRequestDtoToAttributeEntity(UpdateAttributeRequest updateAttributeRequest,Attribute attribute){
        attribute.setName(updateAttributeRequest.getName());
        attribute.setType(updateAttributeRequest.getType());

    }
}
