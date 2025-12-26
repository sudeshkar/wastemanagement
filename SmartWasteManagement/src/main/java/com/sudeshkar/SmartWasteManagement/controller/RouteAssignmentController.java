package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.CreateRouteAssignmentRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteAssignmentResponseDto;
import com.sudeshkar.SmartWasteManagement.sevice.RouteAssignmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteAssignmentController {
	private final RouteAssignmentService assignmentService;
	
	@PostMapping("/{routeId}/assign")
    public ResponseEntity<RouteAssignmentResponseDto> assignRoute(
            @PathVariable Long routeId,
            @RequestBody CreateRouteAssignmentRequestDto request) {

        return ResponseEntity.ok(
                assignmentService.assignRoute(routeId, request)
        );
    }

    
    @GetMapping("/{routeId}/assignments")
    public ResponseEntity<List<RouteAssignmentResponseDto>> getAssignments(
            @PathVariable Long routeId) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentsByRoute(routeId)
        );
    }
}
