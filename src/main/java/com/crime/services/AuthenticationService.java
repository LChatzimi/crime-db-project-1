package com.crime.services;


import com.crime.dtos.LoginRequestDTO;
import com.crime.entities.Users;
import com.crime.repositories.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users authenticate(LoginRequestDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    public boolean authenticate(String username, String password) {
//        // Step 1: Retrieve the user by username
//        Optional<Users> user = userRepository.findByUsername(username);
//
//        if (user.isEmpty()) {
//            // User not found
//            return false;
//        }
//
//        // Step 2: Check if the password matches (using BCrypt password encoder for hash comparison)
//        if (passwordEncoder.matches(password, user.get().getPassword())) {
//            return true;  // Password matches
//        } else {
//            return false;  // Password doesn't match
//        }
//    }


    public Users signup(LoginRequestDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new DuplicateRequestException("User already exists");
        }

        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);

        return user;
    }
}
