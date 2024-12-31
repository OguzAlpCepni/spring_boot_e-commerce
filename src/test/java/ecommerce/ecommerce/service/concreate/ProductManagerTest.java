package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.ProductDtos.CreateProductRequest;
import ecommerce.ecommerce.core.exceptions.SkuIsNotUnique;
import ecommerce.ecommerce.model.Product;
import ecommerce.ecommerce.repository.ProductRepository;
import ecommerce.ecommerce.service.BusinessRules.ProductBusinessRules;
import ecommerce.ecommerce.service.abstracts.ProductService;
import ecommerce.ecommerce.service.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

class ProductManagerTest {

    private ProductManager productManager;
    private ProductRepository productRepository;
    private ProductBusinessRules productBusinessRules;
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productBusinessRules = Mockito.mock(ProductBusinessRules.class);
        productMapper = Mockito.mock(ProductMapper.class);
        productManager = new ProductManager(productRepository,productMapper,productBusinessRules);
    }
    // adım bir test isminin yazılması
    @DisplayName("should add product successfully when sku is unique")
    @Test
    void shouldAddProductSuccesfullyWhenSkuIsUnique() {
        //adım 2 test verilerinin ayarlanması
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setSku("unique-sku");
        createProductRequest.setProductName("Test Product");
        createProductRequest.setPrice(new BigDecimal(100));

        Product product = new Product();
        product.setProductId("adsf");
        product.setSku("unique-sku");
        product.setProductName("Test Product");
        product.setPrice(new BigDecimal(100));

        // adım 3 mock servislerinin davranışlarının belirlenmes,
        Mockito.doNothing().when(productBusinessRules).CheckProductSkuIsUnique(createProductRequest.getSku());
        Mockito.when(productMapper.toProduct(createProductRequest)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        // Metodu çalıştırma
        productManager.addProduct(createProductRequest);

        // Doğrulamalar
        Mockito.verify(productBusinessRules).CheckProductSkuIsUnique(createProductRequest.getSku());
        Mockito.verify(productMapper).toProduct(createProductRequest);
        Mockito.verify(productRepository).save(product);
    }

    @DisplayName("should Throw Exception when SKU is Not Unique")
    @Test
    void shouldThrowException_whenSkuIsNotUnique() {
        // Test verisi hazırlama
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setSku("gg");  // Aynı SKU kullanılacak

        // Adım 3: Bağımlı servislerin davranışlarının belirlenmesi
        Mockito.doThrow(new RuntimeException("SKU must be unique"))
                .when(productBusinessRules)
                .CheckProductSkuIsUnique(createProductRequest.getSku());

        assertThrows(RuntimeException.class, () -> productManager.addProduct(createProductRequest));

        // Doğrulamalar
        Mockito.verify(productBusinessRules).CheckProductSkuIsUnique(createProductRequest.getSku());
        Mockito.verifyNoInteractions(productMapper);
        Mockito.verifyNoInteractions(productRepository);
    }

    @DisplayName("should Fail to Add Product when SKU is Not Unique")
    @Test
    void shouldFailToAddProduct_whenSkuIsNotUnique() {
        // Adım 2: Test verilerinin hazırlanması
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setSku("sdfsdf");

        // Adım 3: Bağımlı servislerin davranışlarının belirlenmesi
        // Burada mock yanlış bir davranış döndürmeli.
        Mockito.doNothing().when(productBusinessRules).CheckProductSkuIsUnique(createProductRequest.getSku());

        // Adım 4: Test edilecek metodun çalıştırılması
        // Bu testin exception atmadan çalıştığını görmek istiyoruz.
        assertThrows(RuntimeException.class, () -> productManager.addProduct(createProductRequest));

        // Adım 6: Mock'ların doğru çağrıldığının doğrulanması
        Mockito.verify(productBusinessRules).CheckProductSkuIsUnique(createProductRequest.getSku());
        Mockito.verifyNoInteractions(productMapper);
        Mockito.verifyNoInteractions(productRepository);
    }
}