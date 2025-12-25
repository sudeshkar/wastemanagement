package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.UserRepository;
import com.sudeshkar.SmartWasteManagement.model.User;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Boolean checkUserExistByEmail(String email) {
    Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"+id));
    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void  deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
