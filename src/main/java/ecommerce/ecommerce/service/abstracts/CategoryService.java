package ecommerce.ecommerce.service.abstracts;

import ecommerce.ecommerce.core.Dtos.CategoryDtos.CreateCategoryRequest;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetAllCategoriesResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetByIdCategoryResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<GetAllCategoriesResponse> getAllCategory();
    GetByIdCategoryResponse getById(int id);
    void addCategory(CreateCategoryRequest createCategoryRequest);
    void UpdateCategory(UpdateCategoryRequest updateCategoryRequest,int id);
    void deleteCategory(int id);
}
