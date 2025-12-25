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


import com.sudeshkar.SmartWasteManagement.model.Zone;
import com.sudeshkar.SmartWasteManagement.sevice.ZoneService;

@RestController
@RequestMapping("/api/v1/zones")
public class ZoneController {
	@Autowired
	private ZoneService zoneService;
	@GetMapping
	public ResponseEntity<List<Zone>> getAllZones(){
		List<Zone> zones= zoneService.getAllZones();
		return ResponseEntity.ok(zones);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getZoneById(@PathVariable Long id){
		try {
			Zone zone =zoneService.getById(id);
			return ResponseEntity.ok(zone);
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("Zone ID Not Found: " + id);
		}
		
		
		  
	}
	
	@PostMapping
	 public ResponseEntity<String> addZone(@RequestBody Map<String,String> body){
       String name = body.get("name");
       String description =  body.get("description");
       Zone zone = new Zone();
       zone.setZoneName(name);
       zone.setDescription(description);
       zoneService.createZone(zone);
       return new ResponseEntity<String>("Zone Created Successfully",HttpStatus.CREATED);
       
   }	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateZone(@RequestBody Map<String, String> body,@PathVariable Long id) {
		if (!zoneService.existsById(id)) {
			return new ResponseEntity<String>("Zone Not Found",HttpStatus.NOT_FOUND);
		}
	    

	    Zone existingZone = zoneService.getById(id);

	     
	    if (body.containsKey("zoneName")) {
	    	existingZone.setZoneName(body.get("zoneName"));
	    }
	    if (body.containsKey("description")) {
	    	existingZone.setDescription(body.get("description"));
	    }

	    zoneService.createZone(existingZone);

	    return new ResponseEntity<>("Zone updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteZone(@PathVariable Long id){
		if (zoneService.existsById(id)) {
			zoneService.deleteUser(id);
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
	}
	
	

}
