package ecommerce.ecommerce.service.product.mapper;

import ecommerce.ecommerce.core.Dtos.ProductDtos.CreateProductRequest;
import ecommerce.ecommerce.core.Dtos.ProductDtos.GetAllProductResponse;
import ecommerce.ecommerce.core.Dtos.ProductDtos.GetByIdResponse;
import ecommerce.ecommerce.core.Dtos.ProductDtos.UpdateProductRequest;
import ecommerce.ecommerce.model.Product;
import org.springframework.stereotype.Component;

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
        // this.toHetAllProductResponse(product) labnda ile donusümü bu
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
