package ecommerce.ecommerce.service.abstracts;

import ecommerce.ecommerce.core.Dtos.AttributeDtos.CreateAttributeRequest;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetAllAttributesResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.GetByIdAttributeResponse;
import ecommerce.ecommerce.core.Dtos.AttributeDtos.UpdateAttributeRequest;


import java.util.List;

public interface AttributeService {

    void addAttribute(CreateAttributeRequest createAttributeRequest);
    List<GetAllAttributesResponse> getAllAttributes();

    GetByIdAttributeResponse GetById(int id);
    void updateAttribute(UpdateAttributeRequest updateAttributeRequest,int id);
    void deleteAttribute(int id);

}
