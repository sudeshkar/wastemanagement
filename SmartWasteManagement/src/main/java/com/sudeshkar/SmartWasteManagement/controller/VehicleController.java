package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;
import com.sudeshkar.SmartWasteManagement.dto.VehicleResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.sevice.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
	
	private final VehicleService vehicleService;
	
	@PostMapping
    public ResponseEntity<String> createVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok("Created Successfully");
    }

     
    @GetMapping
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
    	try {
    		VehicleResponseDto vehicle= vehicleService.getVehicleById(id);
    		return new ResponseEntity<VehicleResponseDto>(vehicle,HttpStatus.OK);
		} catch (Exception e) {
			 return new ResponseEntity<String>("Not Found Vehicle ID- "+id,HttpStatus.NOT_FOUND);
		}
    }

     
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(vehicleService.updateStatus(id,VehicleStatus.valueOf(status)));
    }

     
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Deleted");
    }
}
