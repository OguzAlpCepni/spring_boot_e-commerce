package ecommerce.ecommerce.core.config;

import ecommerce.ecommerce.security.JwtAuthFilter;
import ecommerce.ecommerce.service.concreate.UserManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserManager userManager;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserManager userManager, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x.requestMatchers("/auth/welcome/**", "/auth/addNewUser/**", "/auth/generateToken/**").permitAll()
                )
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/auth/user").hasRole("CUSTOMER")
                                .requestMatchers("/auth/admin").hasRole("ADMIN")
                )
                // Authorize for product
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers(HttpMethod.GET,"/v1/product/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/v1/product/**").hasAnyRole("SELLER","ADMIN")
                                .requestMatchers(HttpMethod.PUT,"v1/product/**").hasAnyRole("SELLER","ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"v1/product/**").hasAnyRole("SELLER","ADMIN")
                )
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers(HttpMethod.GET,"/v1/basket/**").hasAnyRole("ADMIN","CUSTOMER")
                                .requestMatchers(HttpMethod.POST,"/v1/basket/**").hasAnyRole("ADMIN","CUSTOMER")
                                .requestMatchers(HttpMethod.DELETE,"/v1/basket/delete/productId/{productId}/quantity/{quantity}").hasAnyRole("ADMIN","CUSTOMER")
                                .requestMatchers(HttpMethod.DELETE,"/v1/basket/delete/productId/{productId}").hasAnyRole("ADMIN","CUSTOMER")
                                .requestMatchers(HttpMethod.PUT,"/v1/basket/**").hasAnyRole("ADMIN","CUSTOMER")

                )
                .authorizeHttpRequests(x->
                        x
                                .requestMatchers(HttpMethod.POST,"/v1/orders/createOrderFromBasket").hasAnyRole("ADMIN","CUSTOMER")
                                .requestMatchers(HttpMethod.POST,"/v1/orders/process/orderId/{orderId}").hasAnyRole("ADMIN","CUSTOMER")

                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userManager);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

}
