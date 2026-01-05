package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateVehicleDTO;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleResponseDto;
import com.sudeshkar.SmartWasteManagement.mapper.DriverMapper;
import com.sudeshkar.SmartWasteManagement.mapper.VehicleMapper;
import com.sudeshkar.SmartWasteManagement.model.Driver;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor

public class VehicleServiceImpl implements VehicleService{
	
	private final VehicleRepository vehicleRepository;

	
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

	@Override
	public void createVehicle(CreateVehicleDTO dto) {
		Vehicle vehicle = VehicleMapper.toEntity(dto);
		vehicleRepository.save(vehicle);
		
		
	}

	@Override
	public List<VehicleResponseDto> getUnassignedVehicles() {
	    List<Vehicle> vehicles = vehicleRepository.findAll();
	    List<VehicleResponseDto> dtos = new ArrayList<>();

	    for (Vehicle vehicle : vehicles) {
	        if (vehicle.getDriver() == null) {
	            VehicleResponseDto dto = VehicleMapper.toDto(vehicle);
	            dtos.add(dto);
	        }
	    }
	    return dtos;
	}


}
