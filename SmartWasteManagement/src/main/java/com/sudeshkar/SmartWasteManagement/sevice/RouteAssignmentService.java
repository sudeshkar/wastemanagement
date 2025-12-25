package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;

public interface RouteAssignmentService {
	RouteAssignment assignRoute(Long routeId, Long vehicleId);
    List<RouteAssignment> getAssignmentsByRoute(Long routeId);
}
