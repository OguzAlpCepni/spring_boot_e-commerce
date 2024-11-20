package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.CategoryAttributeDtos.CreateCategoryAttributeRequest;
import ecommerce.ecommerce.service.abstracts.CategoryAttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/category-attribute")
public class CategoryAttributeController {

    private final CategoryAttributeService categoryAttributeService;
    public CategoryAttributeController(CategoryAttributeService categoryAttributeService) {
        this.categoryAttributeService = categoryAttributeService;
    }

    @PostMapping
    public ResponseEntity<?> addCategoryAttribute(@RequestBody CreateCategoryAttributeRequest createCategoryAttributeRequest) {
        categoryAttributeService.addCategoryAttribute(createCategoryAttributeRequest);
        return ResponseEntity.ok("category attribute added");
    }
}
