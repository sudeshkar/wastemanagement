package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.sevice.BinService;

@RestController
@RequestMapping("/api/v1/bins")
public class BinController {
	@Autowired
	private BinService binService;
	@GetMapping
	public ResponseEntity<List<Bin>> getAllBins(){
		List<Bin> bins= binService.getAllBins();
		return new ResponseEntity<List<Bin>>(bins,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBinById(@PathVariable Long id){
		try {
			Bin bin=binService.getBinById(id);
			 return ResponseEntity.ok(bin);
			 
		} catch (Exception e) {
			return new ResponseEntity<String>("bin Not found ",HttpStatus.NOT_FOUND);
		}	
		 
		 
	}
	
	@PostMapping
	public ResponseEntity<String> addBin(@RequestBody Map<String, String> body) {
	    String locationLatStr = body.get("location_lat");
	    String locationLngStr = body.get("location_lng");
	    String capacityStr = body.get("capacity");
	    String current_fill_levelStr = body.get("current_fill_level");
	    String status = body.get("status");
	   
	    
	   
	    if (locationLatStr == null || locationLngStr == null ||capacityStr == null||current_fill_levelStr == null||status == null) {
	        return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
	    }

	   
	    Double location_lat = null;
	    Double location_lng = null;
	    Double capacity = null;
	    Double current_fill_level = null;
	    
	    try {
	        location_lat = Double.valueOf(locationLatStr);
	        location_lng = Double.valueOf(locationLngStr);
	        capacity = Double.valueOf(capacityStr);
	        current_fill_level = Double.valueOf(current_fill_levelStr);
	    } catch (NumberFormatException e) {
	        return new ResponseEntity<>("Invalid number format for location", HttpStatus.BAD_REQUEST);
	    }

	   

	    
	    Bin bin = new Bin();
	    bin.setLocation_lat(location_lat);
	    bin.setLocation_lng(location_lng);
	    bin.setCapacity(capacity);
	    bin.setCurrentFillLevel(current_fill_level);
	    
	    binService.createbin(bin);  

	    return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateBin(
	        @PathVariable Long id,
	        @RequestBody Map<String, String> body) {

	    
	    Bin existingBin = binService.getBinById(id);
	    if (existingBin == null) {
	        return new ResponseEntity<>("Bin not found", HttpStatus.NOT_FOUND);
	    }

	
	    String locationLatStr = body.get("location_lat");
	    String locationLngStr = body.get("location_lng");
	    String capacityStr = body.get("capacity");
	    String current_fill_levelStr = body.get("current_fill_level");
	    String status = body.get("status");

	   
	    
	    Double location_lat;
	    Double location_lng;
	    Double capacity;
	    Double current_fill_level;

	    try {
	        location_lat = Double.valueOf(locationLatStr);
	        location_lng = Double.valueOf(locationLngStr);
	        capacity = Double.valueOf(capacityStr);
	        current_fill_level = Double.valueOf(current_fill_levelStr);
	    } catch (NumberFormatException e) {
	        return new ResponseEntity<>("Invalid number format", HttpStatus.BAD_REQUEST);
	    }

	     
	    existingBin.setLocation_lat(location_lat);
	    existingBin.setLocation_lng(location_lng);
	    existingBin.setCapacity(capacity);
	    existingBin.setCurrentFillLevel(current_fill_level);
	    existingBin.setStatus(status);

	     
	    binService.createbin(existingBin);

	    return new ResponseEntity<>("Bin updated successfully", HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBin(@PathVariable Long id){
		if (binService.existsById(id)) {
			binService.deleteBin(id);
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/{id}/assign-device")
	public ResponseEntity<?> assignDevice(@PathVariable Long id,@RequestParam String deviceId) {

	    Bin updatedBin = binService.assignDevice(id, deviceId);
	    return ResponseEntity.ok("Device assigned successfully to bin " + id);
	}
	
	@PostMapping("/{id}/mark-empty")
	public ResponseEntity<?> markEmpty(
	        @PathVariable Long id,
	        @RequestParam Long vehicleId,
	        @RequestParam double wasteWeight,
	        @RequestParam String notes) {
	    
	    Bin updatedBin = binService.markEmpty(id, vehicleId, wasteWeight, notes);
	    return ResponseEntity.ok(updatedBin);
	}



}
