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

import com.sudeshkar.SmartWasteManagement.dto.AssignZoneToCollectionRDTO;
import com.sudeshkar.SmartWasteManagement.dto.CollectionRouteResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.createCollectionRouteDto;
import com.sudeshkar.SmartWasteManagement.sevice.RouteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
	
	private final RouteService routeService;

	@PostMapping
    public ResponseEntity<?> createRoute(
            @RequestBody createCollectionRouteDto dto) {
		try {
			 return ResponseEntity.ok(routeService.createRoute(dto));
		} catch (Exception e) {
			
			return new ResponseEntity<String>("error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
       
    }

    
    @GetMapping
    public ResponseEntity<List<CollectionRouteResponseDto>> getAllRoutes() {
   
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

     
    @GetMapping("/{id}")
    public ResponseEntity<?> getRouteById(
            @PathVariable Long id) {
    	try {
    		return ResponseEntity.ok(routeService.getRouteById(id));
		} catch (Exception e) {
			return new ResponseEntity<String>("error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
        
    }

    // UPDATE ROUTE
    @PutMapping("/{id}")
    public ResponseEntity<CollectionRouteResponseDto> updateRoute(
            @PathVariable Long id,
            @RequestBody createCollectionRouteDto dto) {

        return ResponseEntity.ok(routeService.updateRoute(id, dto));
    }

    // DELETE ROUTE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/assignZone")
    public ResponseEntity<String> assignZone(@RequestBody AssignZoneToCollectionRDTO dto){
    	try {
			routeService.assignZone(dto);
			return new ResponseEntity<String>("Assigned Successfully",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Assigned Failed "+e.getMessage(),HttpStatus.CONFLICT);
		}
    }

    // SMART ROUTE GENERATION
    @PostMapping("/generate")
    public ResponseEntity<?> generateRoute() {
    	
    	try {
    		 return ResponseEntity.ok(routeService.generateSmartRoute());
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed "+e.getMessage(),HttpStatus.CONFLICT);
		}
       
    }
}
