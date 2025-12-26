package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.CreateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteStepResponseDto;
import com.sudeshkar.SmartWasteManagement.sevice.RouteStepService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes/{routeId}/steps")
@RequiredArgsConstructor
public class RouteStepController {
	
	private final RouteStepService stepService;
	
	@PostMapping
    public ResponseEntity<RouteStepResponseDto> addStep(
            @PathVariable Long routeId,
            @RequestBody CreateRouteStepRequestDto request) {

        return ResponseEntity.ok(
                stepService.createStep(routeId, request)
        );
    }

    
    @GetMapping
    public ResponseEntity<List<RouteStepResponseDto>> getSteps(
            @PathVariable Long routeId) {

        return ResponseEntity.ok(
                stepService.getStepsByRoute(routeId)
        );
    }
}
