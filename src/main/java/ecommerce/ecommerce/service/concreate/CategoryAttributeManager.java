package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.CategoryAttributeDtos.CreateCategoryAttributeRequest;
import ecommerce.ecommerce.core.exceptions.AttributeNotFoundException;
import ecommerce.ecommerce.core.exceptions.CategoryNotFoundException;
import ecommerce.ecommerce.model.Attribute;
import ecommerce.ecommerce.model.Category;
import ecommerce.ecommerce.model.CategoryAttribute;
import ecommerce.ecommerce.repository.AttributeRepository;
import ecommerce.ecommerce.repository.CategoryAttributeRepository;
import ecommerce.ecommerce.repository.CategoryRepository;
import ecommerce.ecommerce.service.abstracts.CategoryAttributeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CategoryAttributeManager implements CategoryAttributeService {

    private final CategoryAttributeRepository categoryAttributeRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;

    public CategoryAttributeManager(CategoryAttributeRepository categoryAttributeRepository, CategoryRepository categoryRepository, AttributeRepository attributeRepository) {
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
        this.categoryAttributeRepository = categoryAttributeRepository;

    }

    @Override
    public void addCategoryAttribute(CreateCategoryAttributeRequest createCategoryAttributeRequest) {
        Category category = categoryRepository.findById(createCategoryAttributeRequest.getCategory().getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        Attribute attribute = attributeRepository.findById(createCategoryAttributeRequest.getAttribute().getId())
                .orElseThrow(() -> new AttributeNotFoundException("Attribute not found"));

        CategoryAttribute categoryAttribute = new CategoryAttribute(0, category, attribute);
        categoryAttributeRepository.save(categoryAttribute);
    }
}
