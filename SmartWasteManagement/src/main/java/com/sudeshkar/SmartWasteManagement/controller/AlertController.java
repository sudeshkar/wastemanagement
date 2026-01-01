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
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.AlertDto;
import com.sudeshkar.SmartWasteManagement.dto.CreateAlertRequestDTO;
import com.sudeshkar.SmartWasteManagement.sevice.AlertService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
	private final AlertService alertService;
	
	@GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(alertService.getById(id));
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> createAlert(@RequestBody CreateAlertRequestDTO dto){
		try {
			alertService.createAlert(dto.getBinId(), dto.getAlertType(), dto.getMessage());
			return ResponseEntity.ok("Created Successfully!");
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}
	}
     
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAlert(@PathVariable Long id){
		try {
			alertService.deleteById(id);
			return ResponseEntity.ok("Deleted Successfully");
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
    @PostMapping("/ack/{id}")
    public ResponseEntity<AlertDto> acknowledgeAlert(
            @PathVariable Long id) {

        return ResponseEntity.ok(alertService.acknowledgeAlert(id));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<String> updateAlert(@PathVariable Long id,@RequestBody CreateAlertRequestDTO dto){
    	try {
    		alertService.updateAlert(id,dto);
			return ResponseEntity.ok("Update Successfully");
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
    }
}
