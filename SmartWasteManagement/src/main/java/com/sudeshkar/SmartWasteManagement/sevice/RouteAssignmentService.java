package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.CreateRouteAssignmentRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteAssignmentResponseDto;

public interface RouteAssignmentService {
	RouteAssignmentResponseDto assignRoute(
            Long routeId,
            CreateRouteAssignmentRequestDto request
    );

    List<RouteAssignmentResponseDto> getAssignmentsByRoute(Long routeId);
}
