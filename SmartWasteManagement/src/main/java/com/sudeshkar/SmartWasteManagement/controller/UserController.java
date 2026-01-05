package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.Enum.Role;
import com.sudeshkar.SmartWasteManagement.dto.UserDto;
import com.sudeshkar.SmartWasteManagement.mapper.UserMapper;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.sevice.UserService;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<User> users = userService.getAllUsers();
		
		List<UserDto> userDtos = users.stream()
		        .map(UserMapper::toDto)
		        .toList();
		return ResponseEntity.ok(userDtos);
		
	}
	
	@GetMapping("/me")
	public ResponseEntity<UserDto> getMyProfile(Authentication authentication){
		return ResponseEntity.ok(userService.getMyProfile(authentication));
		
	}
	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getByEmail(@PathVariable String email){
		User users  = userService.getUserByEmail(email);
		UserDto dto = UserMapper.toDto(users);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> userById(@PathVariable Long id){
		try {
			User user=userService.getUserById(id);
			 UserDto userDto = UserMapper.toDto(user);
			 return ResponseEntity.ok(userDto);
		} catch (Exception e) {
			return new ResponseEntity<String>("User Not found ",HttpStatus.NOT_FOUND);
		}	
		 
		 
	}
	
	@PostMapping
	 public ResponseEntity<String> addUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));
        Role role = Role.valueOf(body.get("role"));

        if (userService.checkUserExistByEmail(email)){
            return new ResponseEntity<>("Email Already Exist",HttpStatus.CONFLICT);
        }
        userService.createUser(User.builder().email(email).passwordHash(password).role(role).build());
        return new ResponseEntity<>("Successfully created",HttpStatus.OK);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@RequestBody Map<String, String> body,@PathVariable Long id) {
		if (!userService.existsById(id)) {
			return new ResponseEntity<String>("Please register the user first",HttpStatus.CONFLICT);
		}
	    

	    User existingUser = userService.getUserById(id);

	     
	    if (body.containsKey("email")) {
	        existingUser.setEmail(body.get("email"));
	    }
	    if (body.containsKey("password")) {
	        existingUser.setPasswordHash( passwordEncoder.encode(body.get("password")));
	    }

	    if (body.containsKey("role")) {
	        try {
	            existingUser.setRole(Role.valueOf(body.get("role").toUpperCase()));
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>("Invalid role value", HttpStatus.BAD_REQUEST);
	        }
	    }

	    userService.createUser(existingUser);

	    return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		if (userService.existsById(id)) {
			userService.deleteUser(id);
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
	}
	
}
