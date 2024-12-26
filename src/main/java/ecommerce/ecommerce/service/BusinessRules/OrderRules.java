package ecommerce.ecommerce.service.BusinessRules;

import ecommerce.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderRules {

    private final UserRepository userRepository;

    public OrderRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



}
