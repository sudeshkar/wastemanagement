package com.sudeshkar.SmartWasteManagement.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.Enum.Role;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.sevice.UserService;
import com.sudeshkar.SmartWasteManagement.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
	
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");
        if (!userService.checkUserExistByEmail(email)){
            return new ResponseEntity<>("Not Registered Yet.Please Register And Try Again!",HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getUserByEmail(email);

        if (!passwordEncoder.matches(password, user.getPasswordHash())){
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }
	
	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));
        Role role = Role.valueOf(body.get("role"));

        if (userService.checkUserExistByEmail(email)){
            return new ResponseEntity<>("Email Already Exist",HttpStatus.CONFLICT);
        }
        userService.createUser(User.builder().email(email).passwordHash(password).role(role).build());
        return new ResponseEntity<>("Successfully Registered",HttpStatus.CREATED);
    }

	
}
