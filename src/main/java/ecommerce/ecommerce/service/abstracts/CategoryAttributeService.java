package ecommerce.ecommerce.service.abstracts;

import ecommerce.ecommerce.core.Dtos.CategoryAttributeDtos.CreateCategoryAttributeRequest;

public interface CategoryAttributeService {

    void addCategoryAttribute(CreateCategoryAttributeRequest createCategoryAttributeRequest);
}
