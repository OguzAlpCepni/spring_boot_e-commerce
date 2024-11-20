package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.CategoryAttributeDtos.CreateCategoryAttributeRequest;
import ecommerce.ecommerce.model.CategoryAttribute;
import ecommerce.ecommerce.repository.CategoryAttributeRepository;
import ecommerce.ecommerce.service.abstracts.CategoryAttributeService;
import ecommerce.ecommerce.service.mapper.CategoryAttributeMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryAttributeManager implements CategoryAttributeService {

    private final CategoryAttributeRepository categoryAttributeRepository;
    private final CategoryAttributeMapper categoryAttributeMapper;
    public CategoryAttributeManager(CategoryAttributeRepository categoryAttributeRepository, CategoryAttributeMapper categoryAttributeMapper) {
        this.categoryAttributeRepository = categoryAttributeRepository;
        this.categoryAttributeMapper = categoryAttributeMapper;
    }

    @Override
    public void addCategoryAttribute(CreateCategoryAttributeRequest createCategoryAttributeRequest) {
        CategoryAttribute categoryAttribute = categoryAttributeMapper.mappingRequestToCategoryAttributeEntity(createCategoryAttributeRequest);
        categoryAttributeRepository.save(categoryAttribute);
    }
}
