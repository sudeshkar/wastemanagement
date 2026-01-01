package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.AllVehicleLocationDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.VehicleLocationResponseDto;
import com.sudeshkar.SmartWasteManagement.sevice.VehicleLocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicle-locations")
@RequiredArgsConstructor
public class VehicleLocationController {
	 
	 private final VehicleLocationService vehicleLocationService;
    
    @PostMapping("/{vehicleId}")
    public ResponseEntity<?> saveLocation(@PathVariable Long vehicleId,@RequestBody VehicleLocationRequestDto location) {
    	try {
    		
            return new ResponseEntity<VehicleLocationResponseDto>(vehicleLocationService.saveLocation(vehicleId, location),HttpStatus.OK);
            
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    	
    }
    
    @GetMapping("/{vehicleId}/latest")
    public ResponseEntity<?> getLatestLocation(@PathVariable Long vehicleId){
    	try {
    		return new ResponseEntity<VehicleLocationResponseDto>(vehicleLocationService.getLatestLocation(vehicleId),HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping
    public ResponseEntity<?> getAllLocations(){
    	try {
			return new ResponseEntity<List<AllVehicleLocationDto>>(vehicleLocationService.getAllLocation(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
    }
}
