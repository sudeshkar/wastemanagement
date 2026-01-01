package com.sudeshkar.SmartWasteManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.AssignVehicleRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.CreateDriverRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.DriverResponseDto;
import com.sudeshkar.SmartWasteManagement.sevice.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {
	
	private final DriverService driverService;
	
	@PostMapping
    public ResponseEntity<?> createDriver(@RequestBody CreateDriverRequestDto dto) {
		try {
			
			return new ResponseEntity<String>(driverService.createDriver(dto),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
        
    }

     
    @GetMapping
    public ResponseEntity<?> getAllDrivers() {
    	try {
    		return ResponseEntity.ok(driverService.getAllDrivers());	
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
        
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getDriverById(
            @PathVariable Long id) {
    	try {
    		 return ResponseEntity.ok(driverService.getDriverById(id));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
       
    }

   
    @PutMapping("/assign-vehicle")
    public ResponseEntity<?> assignVehicle(@RequestBody AssignVehicleRequestDTO dto) {
    	try {
    		return ResponseEntity.ok(
                    driverService.assignVehicle(dto)
            );
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
        
    }

    
    @PutMapping("/{driverId}/remove-vehicle")
    public ResponseEntity<?> removeVehicle(
            @PathVariable Long driverId) {
    	try {
    		 return new ResponseEntity<DriverResponseDto>(driverService.removeVehicle(driverId),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Unable to Remove Failed!",HttpStatus.BAD_REQUEST);
		}
    	
    	 
    }

}
