package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.CategoryDtos.CreateCategoryRequest;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetAllCategoriesResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetByIdCategoryResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.UpdateCategoryRequest;
import ecommerce.ecommerce.core.exceptions.CategoryNotFoundException;
import ecommerce.ecommerce.model.Category;
import ecommerce.ecommerce.repository.CategoryRepository;
import ecommerce.ecommerce.service.abstracts.CategoryService;
import ecommerce.ecommerce.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryManager(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<GetAllCategoriesResponse> getAllCategory() {
    List<Category> categories = categoryRepository.findAll();
        return categoryMapper.mappingToGetAllCategoryResponse(categories);
    }

    @Override
    public GetByIdCategoryResponse getById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category could not found by id : "+ id));
        return categoryMapper.MappingCategoryToCategoryByIdResponse(category);
    }

    @Override
    public void addCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.mappingCreateCategoryRequestToCategory(createCategoryRequest);
        categoryRepository.save(category);
    }
 
    @Override
    public void UpdateCategory(UpdateCategoryRequest updateCategoryRequest,int id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category could not found by id : "+ id));
        categoryMapper.MappingToCategoryForUpdateById(updateCategoryRequest, category);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
