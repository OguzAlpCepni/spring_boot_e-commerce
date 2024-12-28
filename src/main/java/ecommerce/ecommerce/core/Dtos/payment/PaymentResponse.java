package ecommerce.ecommerce.core.Dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponse {
    private boolean successful;
    private String message;
}
