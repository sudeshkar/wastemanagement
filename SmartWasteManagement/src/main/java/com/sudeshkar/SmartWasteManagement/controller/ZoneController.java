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
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.ZoneRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ZoneResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Zone;
import com.sudeshkar.SmartWasteManagement.sevice.ZoneService;
import com.sudeshkar.SmartWasteManagement.sevice.ZoneServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/zones")
@RequiredArgsConstructor
public class ZoneController {
	 
	private final ZoneService zoneService;
	
	
	@GetMapping
	public ResponseEntity<List<ZoneResponseDto>> getAllZones(){
		 return new ResponseEntity<List<ZoneResponseDto>>(zoneService.getAllZones(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getZoneById(@PathVariable Long id){
		try {
			ZoneResponseDto response =zoneService.getZoneById(id);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("Zone ID Not Found: " + id);
		}
		
		
		  
	}
	
	@PostMapping
	 public ResponseEntity<String> createZone(@RequestBody ZoneRequestDto dto){
        zoneService.createZone(dto);
       return new ResponseEntity<String>("Zone Created Successfully",HttpStatus.CREATED);
       
   }	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateZone(@RequestBody ZoneRequestDto dto,@PathVariable Long id) {
		if (!zoneService.existsById(id)) {
			return new ResponseEntity<String>("Zone Not Found",HttpStatus.NOT_FOUND);
		}
	    

	     zoneService.updateZone(id,dto);


	    return new ResponseEntity<>("Zone updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteZone(@PathVariable Long id){
		if (zoneService.existsById(id)) {
			zoneService.deleteZone(id);
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
	}
	
	

}
