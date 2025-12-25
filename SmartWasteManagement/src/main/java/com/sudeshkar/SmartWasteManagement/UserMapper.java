package com.sudeshkar.SmartWasteManagement;
import com.sudeshkar.SmartWasteManagement.dto.UserDto;
import com.sudeshkar.SmartWasteManagement.model.User;

public class UserMapper {
	
	public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
	public static User toDto(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }
}
