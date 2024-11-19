package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.ProductDtos.*;
import ecommerce.ecommerce.service.abstracts.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<GetByIdResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(productService.geyById(id));
    }
    @PostMapping
    public ResponseEntity AddProduct(@RequestBody @Validated CreateProductRequest createProductRequest){
        productService.addProduct(createProductRequest);
        return ResponseEntity.ok("Product added successfully");
    }
    @PutMapping
    public ResponseEntity UpdateProduct(@RequestBody @Validated UpdateProductRequest updateProductRequest){
        productService.UpdateProduct(updateProductRequest);
        return ResponseEntity.ok("Product updated successfully");
    }
    @PutMapping("/id/{id}")
    public ResponseEntity UpdateProductById(@RequestBody @Validated UpdateProductByIdRequest updateProductByIdRequest,@PathVariable String id){
        productService.UpdateproductById(updateProductByIdRequest,id);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity DeleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
