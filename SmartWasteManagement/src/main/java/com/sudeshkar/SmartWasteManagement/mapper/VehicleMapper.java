package com.sudeshkar.SmartWasteManagement.mapper;

import org.springframework.stereotype.Component;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;
import com.sudeshkar.SmartWasteManagement.dto.CreateVehicleDTO;
import com.sudeshkar.SmartWasteManagement.dto.VehicleResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

@Component
public class VehicleMapper {
	
	public static VehicleResponseDto toDto(Vehicle vehicle) {

        VehicleResponseDto dto = new VehicleResponseDto();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setVehicleNumber(vehicle.getVehicleNumber());
        dto.setStatus(vehicle.getStatus());

        if (vehicle.getDriver() != null) {
            dto.setDriverId(vehicle.getDriver().getId());
            dto.setDriverEmail(
                    vehicle.getDriver().getUser().getEmail());
        }

        return dto;
    }
	
	public static Vehicle toEntity(CreateVehicleDTO dto) {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleNumber(dto.getVehicleNumber());
		vehicle.setStatus(VehicleStatus.AVAILABLE);
		return vehicle;
	}
}
