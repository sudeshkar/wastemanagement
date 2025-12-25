package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.model.RouteStep;
import com.sudeshkar.SmartWasteManagement.sevice.RouteStepService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes/{routeId}/steps")
@RequiredArgsConstructor
public class RouteStepController {
	private final RouteStepService stepService;
	
	@PostMapping
    public ResponseEntity<RouteStep> addStep(
            @PathVariable Long routeId,
            @RequestParam Long binId,
            @RequestParam int order,
            @RequestParam(required = false) String note) {

        return ResponseEntity.ok(
                stepService.createStep(routeId, binId, order, note)
        );
    }

    @GetMapping
    public ResponseEntity<List<RouteStep>> getSteps(
            @PathVariable Long routeId) {

        return ResponseEntity.ok(
                stepService.getStepsByRoute(routeId)
        );
    }
}
