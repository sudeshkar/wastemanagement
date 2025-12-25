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

import com.sudeshkar.SmartWasteManagement.Enum.RequestStatus;
import com.sudeshkar.SmartWasteManagement.dto.CreateServiceRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ServiceRequestDto;
import com.sudeshkar.SmartWasteManagement.sevice.ServiceRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

     
    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody CreateServiceRequestDto dto) {
    	
    	try {
    		ServiceRequestDto created = serviceRequestService.createRequest(dto);

            return new ResponseEntity<>(created, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>( "Error "+e.getMessage(), HttpStatus.BAD_REQUEST);
		}
        
    }

   
    @GetMapping
    public ResponseEntity<List<ServiceRequestDto>> getAllRequests() {

        return ResponseEntity.ok(
                serviceRequestService.getAllRequests()
        );
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestById(
            @PathVariable Long id) {
    	try {
    		ServiceRequestDto dto = serviceRequestService.getRequestById(id);
    		return new ResponseEntity<ServiceRequestDto>(dto,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
        
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRequestStatus(
            @PathVariable Long id,
            @RequestParam RequestStatus status) {
    	
    	try {
    		return ResponseEntity.ok(
                    serviceRequestService.updateStatus(id, status)
            );
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
        
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(
            @PathVariable Long id) {
    	
    	try {
    		serviceRequestService.deleteRequest(id);
    		return new ResponseEntity<String>("Deleted",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.NOT_FOUND);
		}	
        
    }
}

