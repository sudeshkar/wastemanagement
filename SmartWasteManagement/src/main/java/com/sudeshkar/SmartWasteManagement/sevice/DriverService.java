package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.CreateDriverRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;

public interface DriverService {
	
	DriverResponseDto createDriver(CreateDriverRequestDto dto);

    List<DriverResponseDto> getAllDrivers();

    DriverResponseDto getDriverById(Long driverId);

    DriverResponseDto assignVehicle(Long driverId, Long vehicleId);

    DriverResponseDto removeVehicle(Long driverId);
}
