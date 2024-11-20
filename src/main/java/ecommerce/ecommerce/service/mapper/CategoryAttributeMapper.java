package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.CategoryAttributeDtos.CreateCategoryAttributeRequest;
import ecommerce.ecommerce.model.CategoryAttribute;
import org.springframework.stereotype.Component;

@Component
public class CategoryAttributeMapper {


    public CategoryAttribute mappingRequestToCategoryAttributeEntity(CreateCategoryAttributeRequest createCategoryAttributeRequest) {
        return new CategoryAttribute(
                0,
                createCategoryAttributeRequest.getCategory(),
                createCategoryAttributeRequest.getAttribute()
        );
    }


}
