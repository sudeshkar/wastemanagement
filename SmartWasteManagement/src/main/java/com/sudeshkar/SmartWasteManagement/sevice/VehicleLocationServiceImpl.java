package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.VehicleLocationRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.dto.AllVehicleLocationDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationResponseDto;
import com.sudeshkar.SmartWasteManagement.mapper.VehicleLocationMapper;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.VehicleLocation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleLocationServiceImpl implements VehicleLocationService {
	
	private final VehicleRepository vehicleRepository;
	private final VehicleLocationRepository vehicleLocationRepository;
	
	@Override
	public VehicleLocationResponseDto saveLocation(Long vehicleId, VehicleLocationRequestDto requestDto) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new RuntimeException("Vehicle Not found With the ID "+ vehicleId));
		VehicleLocation vehicleLocation= VehicleLocationMapper.toEntity(requestDto, vehicle);
		vehicleLocation.setVehicle(vehicle);
		vehicleLocationRepository.save(vehicleLocation);
		return VehicleLocationMapper.toDto(vehicleLocation);
	}

	@Override
	public VehicleLocationResponseDto getLatestLocation(Long vehicleId) {
		if (!vehicleRepository.existsById(vehicleId)) {
		    throw new RuntimeException("Vehicle not found with ID " + vehicleId);
		}
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new RuntimeException("Vehicle Not found With the ID "+ vehicleId));
		
		VehicleLocation location = vehicleLocationRepository.findTopByVehicleVehicleIdOrderByTimestampDesc(vehicle.getVehicleId());
		if (location == null) {
	        throw new RuntimeException(
	                "No location data found for vehicle ID " + vehicleId
	        );
	    }
		
		return VehicleLocationMapper.toDto(location);
	
	}

	@Override
	public List<AllVehicleLocationDto> getAllLocation() {
		List<VehicleLocation> latestLocations = vehicleLocationRepository.findLatestLocationForAllVehicles();
		return latestLocations.stream()
                .map(loc -> AllVehicleLocationDto.builder()
                        .vehicleId(loc.getVehicle().getVehicleId())
                        .vehicleNumber(loc.getVehicle().getVehicleNumber())
                        .status(loc.getVehicle().getStatus())
                        .latitude(loc.getLatitude())
                        .longitude(loc.getLongitude())
                        .timestamp(loc.getTimestamp())
                        .build()
                )
                .toList();
	}

}
