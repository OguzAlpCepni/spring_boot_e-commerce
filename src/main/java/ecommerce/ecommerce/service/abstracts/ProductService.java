package ecommerce.ecommerce.service.abstracts;

import ecommerce.ecommerce.core.Dtos.ProductDtos.*;

import java.util.List;

public interface ProductService {
    List<GetAllProductResponse> getAllProducts();

    void addProduct(CreateProductRequest createProductRequest);
    void UpdateProduct(UpdateProductRequest updateProductRequest);
    void UpdateproductById(UpdateProductByIdRequest updateProductByIdRequest,String id);

    GetByIdResponse geyById(String id);
    void deleteProduct(String id);


}
