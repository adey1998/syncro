package com.syncro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syncro.auth.AuthResponse;
import com.syncro.dtos.LoginRequestDTO;
import com.syncro.dtos.RegisterRequestDTO;
import com.syncro.model.User;
import com.syncro.security.JwtUtil;
import com.syncro.service.CustomerDetailsService;
import com.syncro.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        try {
            // check if user with email already exists
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email is already registered");
            }

            //data mapping to postgres schema
            User user = new User();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole("USER");

            // save user
            User savedUser = userService.createUser(user);

            // create jwt token
            final String token = jwtUtil.generateToken(savedUser.getEmail());

            // return token with 201 (created status)
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            //authenticate user creds
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }

        //load user detauls after auth
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        //create jwt token
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        //return token in response
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
