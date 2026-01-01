package com.sudeshkar.SmartWasteManagement.controller;

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

import com.sudeshkar.SmartWasteManagement.dto.CreateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.UpdateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.sevice.RouteStepService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes/{routeId}/steps")
@RequiredArgsConstructor
public class RouteStepController {

    private final RouteStepService stepService;

     
    @PostMapping
    public ResponseEntity<?> addStep(
            @PathVariable Long routeId,
            @RequestBody CreateRouteStepRequestDto request) {
    	try {
    		return ResponseEntity.ok(stepService.createStep(routeId, request));
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
        
    }

    
    @GetMapping
    public ResponseEntity<?> getStepsByRoute(
            @PathVariable Long routeId) {
    	try {
    		return ResponseEntity.ok(stepService.getStepsByRoute(routeId));
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
        
    }

    
    @GetMapping("/{stepId}")
    public ResponseEntity<?> getStepById(@PathVariable Long stepId) {
    	try {
    		 return ResponseEntity.ok(stepService.getStepById(stepId));
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
       
    }

     
    @PutMapping("/{stepId}")
    public ResponseEntity<?> updateStep(
            @PathVariable Long routeId,
            @PathVariable Long stepId,
            @RequestBody UpdateRouteStepRequestDto request) {
    	try {
    		return ResponseEntity.ok(stepService.updateStep(routeId, stepId, request));
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
        
    }

    
    @DeleteMapping("/{stepId}")
    public ResponseEntity<Void> deleteStep(
            @PathVariable Long routeId,
            @PathVariable Long stepId) {
        stepService.deleteStep(routeId, stepId);
        return ResponseEntity.noContent().build();  
    }
}
