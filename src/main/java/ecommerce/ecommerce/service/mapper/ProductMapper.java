package ecommerce.ecommerce.service.mapper;

import ecommerce.ecommerce.core.Dtos.ProductDtos.*;
import ecommerce.ecommerce.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    // GetallProduct
    public GetAllProductResponse toGetAllProductResponse(Product product) {
        return new GetAllProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getDescription(),
                product.getUnitInStock(),
                product.getSku(),
                product.getCategories()
        );
    }
    public List<GetAllProductResponse> toGetAllProductResponse(List<Product> products) {
        // this::toGetAllProductResponse burada bit method referancı tutuyor yani
        // this.toGetAllProductResponse(product) labnda ile donusümü bu
        return products.stream().map(this::toGetAllProductResponse).collect(Collectors.toList());
    }


    // CreateProcut

    public Product toProduct(CreateProductRequest createProductRequest){
        return new Product(
                null,
                createProductRequest.getProductName(),
                createProductRequest.getPrice(),
                createProductRequest.getDescription(),
                createProductRequest.getUnitInStock(),
                createProductRequest.getSku(),
                new ArrayList<>(),
                new ArrayList<>(),
                createProductRequest.getCategories()
        );
    }

    // update

    public void mappingToProductForUpdate(UpdateProductRequest updateProductRequest,Product product){
        product.setProductName(updateProductRequest.getProductName());
        product.setPrice(updateProductRequest.getPrice());
        product.setDescription(updateProductRequest.getDescription());
        product.setUnitInStock(updateProductRequest.getUnitInStock());
        product.setSku(updateProductRequest.getSku());
        product.setCategories(updateProductRequest.getCategories());
    }
    public void mappingToProductForUpdateById(UpdateProductByIdRequest updateProductRequest, Product product){
        product.setProductName(updateProductRequest.getProductName());
        product.setPrice(updateProductRequest.getPrice());
        product.setDescription(updateProductRequest.getDescription());
        product.setUnitInStock(updateProductRequest.getUnitInStock());
        product.setSku(updateProductRequest.getSku());
        product.setCategories(updateProductRequest.getCategories());
    }


    //getById
    public GetByIdResponse toGetByIdDResponseDto(Product product) {
        return new GetByIdResponse(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getDescription(),
                product.getUnitInStock(),
                product.getSku(),
                product.getCategories()
        );
    }
}
