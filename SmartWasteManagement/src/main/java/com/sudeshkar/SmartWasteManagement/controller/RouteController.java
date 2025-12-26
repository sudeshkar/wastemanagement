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
    public ResponseEntity<CollectionRouteResponseDto> createRoute(
            @RequestBody createCollectionRouteDto dto) {

        return ResponseEntity.ok(routeService.createRoute(dto));
    }

    // GET ALL ROUTES
    @GetMapping
    public ResponseEntity<List<CollectionRouteResponseDto>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    // GET ROUTE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CollectionRouteResponseDto> getRouteById(
            @PathVariable Long id) {

        return ResponseEntity.ok(routeService.getRouteById(id));
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
