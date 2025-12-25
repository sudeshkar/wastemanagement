package com.sudeshkar.SmartWasteManagement;

import org.springframework.stereotype.Component;

import com.sudeshkar.SmartWasteManagement.dto.CreateDriverRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Driver;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

@Component
public class DriverMapper {
	public static Driver toEntity(CreateDriverRequestDto dto, User user, Vehicle vehicle) {
        Driver driver = new Driver();
        driver.setUser(user);
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setAssignedVehicle(vehicle);
        return driver;
    }

    public static DriverResponseDto toDTO(Driver driver) {
        DriverResponseDto dto = new DriverResponseDto();
        dto.setDriverId(driver.getId());
        dto.setUserId(driver.getUser().getId());
        dto.setEmail(driver.getUser().getEmail());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setPhoneNumber(driver.getPhoneNumber());
        if (driver.getAssignedVehicle() != null) {
            dto.setAssignedVehicleId(driver.getAssignedVehicle().getVehicleId());
            dto.setAssignedVehicleNumber(driver.getAssignedVehicle().getVehicleNumber());
        }
        return dto;
    }
}
