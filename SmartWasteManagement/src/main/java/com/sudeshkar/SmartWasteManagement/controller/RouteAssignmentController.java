package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;
import com.sudeshkar.SmartWasteManagement.sevice.RouteAssignmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteAssignmentController {
	private final RouteAssignmentService assignmentService;
	
	@PostMapping("/{routeId}/assign")
    public ResponseEntity<RouteAssignment> assignRoute(
            @PathVariable Long routeId,
            @RequestParam Long vehicleId) {
        return ResponseEntity.ok(
                assignmentService.assignRoute(routeId, vehicleId)
        );
    }

    @GetMapping("/{routeId}/assignments")
    public ResponseEntity<List<RouteAssignment>> getAssignments(
            @PathVariable Long routeId) {
        return ResponseEntity.ok(
                assignmentService.getAssignmentsByRoute(routeId)
        );
    }
}
