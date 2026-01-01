package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.AssignVehicleRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.CreateDriverRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;

public interface DriverService {
	
	String createDriver(CreateDriverRequestDto dto);

    List<DriverResponseDto> getAllDrivers();

    DriverResponseDto getDriverById(Long driverId);

    DriverResponseDto assignVehicle(AssignVehicleRequestDTO dto);

    DriverResponseDto removeVehicle(Long driverId);
}
