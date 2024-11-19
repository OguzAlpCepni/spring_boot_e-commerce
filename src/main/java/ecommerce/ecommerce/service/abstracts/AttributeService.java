package ecommerce.ecommerce.service.abstracts;

import ecommerce.ecommerce.core.Dtos.AttributeDtos.CreateAttributeRequest;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetAllAttributesResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.UpdateAttributeRequest;
import ecommerce.ecommerce.model.Attribute;

import java.util.List;

public interface AttributeService {

    void addAttribute(CreateAttributeRequest createAttributeRequest);
    List<GetAllAttributesResponse> getAllAttributes();

    Attribute GetById(int id);
    void updateAttribute(UpdateAttributeRequest updateAttributeRequest);
    void deleteAttribute(int id);

}
