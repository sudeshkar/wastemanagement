package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.DriverMapper;
import com.sudeshkar.SmartWasteManagement.Enum.Role;
import com.sudeshkar.SmartWasteManagement.Repository.DriverRepository;
import com.sudeshkar.SmartWasteManagement.Repository.UserRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateDriverRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Driver;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

	private final DriverRepository driverRepository;
	private final UserRepository userRepository;
	private final VehicleRepository vehicleRepository;
	
	@Override
	public DriverResponseDto createDriver(CreateDriverRequestDto dto) {
		
		User user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new RuntimeException("User Not Found"));
		if (user.getRole() != Role.DRIVER) {
	        throw new RuntimeException("User is not a DRIVER");
	    }

	     
	    if (driverRepository.existsById(user.getId())) {
	        throw new RuntimeException("Driver profile already exists for this user");
	    }

		Vehicle vehicle = vehicleRepository.findById(dto.getAssignedVehicleId()).orElseThrow(()-> new RuntimeException("Vehicle not Found"));
		Driver driver = DriverMapper.toEntity(dto, user, vehicle);
		driverRepository.save(driver);
		
		DriverResponseDto driverResponseDto = DriverMapper.toDTO(driver);
		return driverResponseDto;
		
		
		
	}

	@Override
	public List<DriverResponseDto> getAllDrivers() {
		List<Driver> drivers = driverRepository.findAll();
		List<DriverResponseDto> dtos = new ArrayList<>();
		 for (Driver driver : drivers) {
			DriverResponseDto dto = DriverMapper.toDTO(driver);
			dtos.add(dto);
		}
		return dtos; 
		 
	}

	@Override
	public DriverResponseDto getDriverById(Long driverId) {
		 Driver driver = driverRepository.findById(driverId).orElseThrow(()->new RuntimeException("Driver Not Found"));
		 return DriverMapper.toDTO(driver);
	}

	@Override
	public DriverResponseDto assignVehicle(Long driverId, Long vehicleId) {
		 Driver exDriver = driverRepository.findById(driverId).orElseThrow(()->new RuntimeException("Driver Not Found"));
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new RuntimeException("Vehicle not Found"));
		 exDriver.setAssignedVehicle(vehicle);
		 driverRepository.save(exDriver);
		 return DriverMapper.toDTO(exDriver);
	}

	@Override
	public DriverResponseDto removeVehicle(Long driverId) {
	    Driver driver = driverRepository.findById(driverId)
	            .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));

	     
	    driver.setAssignedVehicle(null);

	    
	    driverRepository.save(driver);
	    return DriverMapper.toDTO(driver);
	}

}
