package ecommerce.ecommerce.service.concreate;

import ecommerce.ecommerce.core.Dtos.UserDtos.CreateUserRequest;
import ecommerce.ecommerce.model.Basket;
import ecommerce.ecommerce.model.user.User;
import ecommerce.ecommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserManager implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserManager(final UserRepository userRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(CreateUserRequest createUserRequest) {

        Basket basket = new Basket();
        basket.setBasketItems(new ArrayList<>());
        basket.setTotalPrice(BigDecimal.ZERO);
        User user = new User(
                null, // ID için, çünkü otomatik atanacak.
                createUserRequest.getName(),
                createUserRequest.getUsername(),
                bCryptPasswordEncoder.encode(createUserRequest.getPassword()),
                true, true, true, true, // UserDetails'in boolean alanları.
                createUserRequest.getAuthorities(),
                basket,
                new ArrayList<>()
        );


        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
