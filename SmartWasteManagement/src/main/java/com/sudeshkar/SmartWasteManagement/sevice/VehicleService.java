package com.sudeshkar.SmartWasteManagement.sevice;



import java.util.List;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;
import com.sudeshkar.SmartWasteManagement.dto.CreateVehicleDTO;
import com.sudeshkar.SmartWasteManagement.dto.VehicleResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

public interface VehicleService {


	void createVehicle(CreateVehicleDTO dto);

	List<VehicleResponseDto> getAllVehicles();

 
	VehicleResponseDto getVehicleById(Long id);

 
	String updateStatus(Long id, VehicleStatus status);

	String deleteVehicle(Long id);
	
	List<VehicleResponseDto> getUnassignedVehicles();

}
