package com.sudeshkar.SmartWasteManagement.mapper;

import java.time.LocalDateTime;

import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.VehicleLocation;

public class VehicleLocationMapper {
	
	public static VehicleLocation toEntity(
            VehicleLocationRequestDto dto,
            Vehicle vehicle) {

        VehicleLocation location = new VehicleLocation();
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setVehicle(vehicle);
        location.setTimestamp(LocalDateTime.now());

        return location;
    }
	
	public static VehicleLocationResponseDto toDto(VehicleLocation entity) {

        return VehicleLocationResponseDto.builder()
                .vehicleId(entity.getVehicle().getVehicleId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .timestamp(entity.getTimestamp())
                .build();
    }
}
