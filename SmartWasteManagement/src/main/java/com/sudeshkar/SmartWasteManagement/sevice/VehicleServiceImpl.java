package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.DriverMapper;
import com.sudeshkar.SmartWasteManagement.VehicleMapper;
import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Driver;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor

public class VehicleServiceImpl implements VehicleService{
	
	private final VehicleRepository vehicleRepository;

	@Override
	public VehicleResponseDto createVehicle(Vehicle vehicle) {
		   vehicleRepository.save(vehicle);
		   return VehicleMapper.toDto(vehicle);
	}

	@Override
	public List<VehicleResponseDto> getAllVehicles() {
		
		List<Vehicle> vehicles = vehicleRepository.findAll();
		List<VehicleResponseDto> dtos = new ArrayList<>();
		 for (Vehicle vehicle : vehicles) {
			 VehicleResponseDto dto = VehicleMapper.toDto(vehicle);
			dtos.add(dto);
		}
		return dtos; 
		 
	}

	@Override
	public VehicleResponseDto getVehicleById(Long id) {
		
		   Vehicle vehicle =vehicleRepository.findById(id).orElseThrow(()-> new RuntimeException("Vehicle not found"));
		   return VehicleMapper.toDto(vehicle);
	}

	@Override
	public String updateStatus(Long id, VehicleStatus status) {
		 Vehicle exVehicle= vehicleRepository.findById(id).orElseThrow(()-> new RuntimeException("Vehicle not found"));
		 exVehicle.setStatus(status);
		 vehicleRepository.save(exVehicle);
		 return "Vehicle Status Updated";
	}

	@Override
	public String deleteVehicle(Long id) {
		try {
			vehicleRepository.deleteById(id);
			return "Deleted Successfully";
		} catch (Exception e) {
			return "Unable to Delete Vehicle "+id;
		}
		 
		
	}

}
