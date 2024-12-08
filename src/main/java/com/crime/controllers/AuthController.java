package com.crime.controllers;

import com.crime.dtos.LoginRequestDTO;
import com.crime.dtos.LoginResponse;
import com.crime.entities.Users;
import com.crime.http.JwtService;
import com.crime.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/loginPage")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDTO loginRequest) {
        Users authenticatedUser = authenticationService.authenticate(loginRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody LoginRequestDTO userDTO) {
        Users registeredUser = authenticationService.signup(userDTO);
        return ResponseEntity.ok(registeredUser);
    }



}
