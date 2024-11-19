package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.CategoryDtos.CreateCategoryRequest;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetAllCategoriesResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.GetByIdCategoryResponse;
import ecommerce.ecommerce.core.Dtos.CategoryDtos.UpdateCategoryRequest;
import ecommerce.ecommerce.service.abstracts.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllCategoriesResponse>> getAllCategories() {
            return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetByIdCategoryResponse> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        categoryService.addCategory(createCategoryRequest);
        return ResponseEntity.ok("Category added successfully");
    }
    @PutMapping ("/id/{id}")
    ResponseEntity updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest, @PathVariable int id) {
        categoryService.UpdateCategory(updateCategoryRequest,id);
        return ResponseEntity.ok("Category updated successfully");
    }
    @DeleteMapping("/id/{id}")
    ResponseEntity deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
