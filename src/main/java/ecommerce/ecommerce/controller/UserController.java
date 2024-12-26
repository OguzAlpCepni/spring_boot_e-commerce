package ecommerce.ecommerce.controller;

import ecommerce.ecommerce.core.Dtos.UserDtos.AuthRequest;
import ecommerce.ecommerce.core.Dtos.UserDtos.CreateUserRequest;
import ecommerce.ecommerce.service.concreate.UserManager;
import ecommerce.ecommerce.service.jwtService.JwtService;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    private final UserManager service;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserController(UserManager service, JwtService jwtService,AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World! this is E-Commerce project";
    }

    @PostMapping("/addNewUser")
    public ResponseEntity addUser(@RequestBody @Validated CreateUserRequest request) {
        service.createUser(request);
        return ResponseEntity.ok("User added successfully");
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        }
        throw new UsernameNotFoundException("invalid username {} " + request.getUsername());
    }

    @GetMapping("/user")
    public String getUserString() {
        return "This is USER!";
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "This is ADMIN!";
    }
}
