package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.CategoryDtos.CreateCategoryRequest;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetAllCategoriesResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetByIdCategoryResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.UpdateCategoryRequest;
import ecommerce.ecommerce.model.Category;
import ecommerce.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public GetAllCategoriesResponse ToGetAllCategoriesResponse(Category category) {
        return new GetAllCategoriesResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getProducts(),
                category.getCategoryAttributes()
                );
    }
    public List<GetAllCategoriesResponse> mappingToGetAllCategoryResponse(List<Category> categories){
        return categories.stream().map(this::ToGetAllCategoriesResponse).collect(Collectors.toList());
    }

    public GetByIdCategoryResponse MappingCategoryToCategoryByIdResponse(Category category){
        return new GetByIdCategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getProducts(),
                category.getCategoryAttributes()
        );
    }

    public Category mappingCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest){
        return new Category(
                0,
                createCategoryRequest.getCategoryName(),
                createCategoryRequest.getProducts(),
                createCategoryRequest.getCategoryAttributes()
        );



    }
    public void MappingToCategoryForUpdateById(UpdateCategoryRequest updateCategoryRequest,Category category){
        category.setCategoryName(updateCategoryRequest.getCategoryName());
        category.setProducts(updateCategoryRequest.getProducts());
        category.setCategoryAttributes(updateCategoryRequest.getCategoryAttributes());
    }
}
