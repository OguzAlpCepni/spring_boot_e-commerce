package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.ProductDtos.*;
import ecommerce.ecommerce.core.exceptions.ProductNotFoundException;
import ecommerce.ecommerce.model.Product;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.service.BusinessRules.ProductBusinessRules;
import ecommerce.ecommerce.service.abstracts.ProductService;
import ecommerce.ecommerce.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductBusinessRules productBusinessRules;

    public ProductManager(final ProductRepository productRepository,ProductMapper productMapper,ProductBusinessRules productBusinessRules) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productBusinessRules = productBusinessRules;
    }

    @Override
    public List<GetAllProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        productBusinessRules.checkProducts(products);
        return productMapper.toGetAllProductResponse(products);

    }

    @Override
    public void addProduct(CreateProductRequest createProductRequest) {
        productBusinessRules.CheckProductSkuIsUnique(createProductRequest.getSku());
        Product product = productMapper.toProduct(createProductRequest);
        productRepository.save(product);
    }

    @Override
    public void UpdateProduct(UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(updateProductRequest.getProductId()).orElseThrow(()-> new ProductNotFoundException("Product could not found by product id : " +updateProductRequest.getProductId()));
        productMapper.mappingToProductForUpdate(updateProductRequest,product);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void UpdateproductById(UpdateProductByIdRequest updateProductByIdRequest,String id) {
        productBusinessRules.CheckProductSkuIsUnique(updateProductByIdRequest.getSku());
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product could not found by id : " +id));
        productMapper.mappingToProductForUpdateById(updateProductByIdRequest,product);
        productRepository.save(product);
    }

    @Override
    public GetByIdResponse geyById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product could not found by product id : " +id));
        GetByIdResponse getByIdResponse = productMapper.toGetByIdDResponseDto(product);
        return getByIdResponse;
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }


}
