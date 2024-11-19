package ecommerce.ecommerce.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handle(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Hata mesajlarını tutmak için bir map
        Map<String, String> errorMessages = new HashMap<>();

        // Tüm hataları alıyoruz ve her birini işliyoruz
        ex.getBindingResult().getAllErrors().forEach(error -> {
            // Hata türüne göre (field) mesajları haritaya ekliyoruz
            String fieldName = ((FieldError) error).getField(); // Alan adını alıyoruz
            String errorMessage = error.getDefaultMessage(); // Hata mesajını alıyoruz
            errorMessages.put(fieldName, errorMessage); // Hata mesajını haritaya ekliyoruz
        });

        // Hata mesajlarını içeren bir ResponseEntity döndürüyoruz
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handle(NoProductFoundException noProductFoundException){
        return new ResponseEntity<>(noProductFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SkuIsNotUnique.class)
    public ResponseEntity<?> handle(SkuIsNotUnique skuIsNotUnique){
        return new ResponseEntity<>(skuIsNotUnique.getMessage(), HttpStatus.BAD_REQUEST);
    }



    // Category

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handle(CategoryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


}

