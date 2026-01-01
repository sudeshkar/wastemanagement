package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.AllVehicleLocationDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationResponseDto;

public interface VehicleLocationService {

	VehicleLocationResponseDto saveLocation(
            Long vehicleId,
            VehicleLocationRequestDto requestDto
    );

    VehicleLocationResponseDto getLatestLocation(Long vehicleId);
    
    List<AllVehicleLocationDto> getAllLocation();
    
    
}
