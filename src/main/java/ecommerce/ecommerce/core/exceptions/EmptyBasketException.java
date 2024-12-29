package ecommerce.ecommerce.core.exceptions;

public class EmptyBasketException extends RuntimeException{

    public EmptyBasketException(String message){
        super(message);
    }
}
