package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.RouteAssignmentRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteAssignmentServiceImpl implements RouteAssignmentService{
	
	private final RouteAssignmentRepository assignmentRepository;
    private final CollectionRouteRepository routeRepository;
    private final VehicleRepository vehicleRepository;
	@Override
	public RouteAssignment assignRoute(Long routeId, Long vehicleId) {
		CollectionRoute route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        RouteAssignment assignment = new RouteAssignment();
        assignment.setRoute(route);
        assignment.setVehicle(vehicle);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setStatus("ASSIGNED");

        return assignmentRepository.save(assignment);
	}

	@Override
	public List<RouteAssignment> getAssignmentsByRoute(Long routeId) {
		return assignmentRepository.findByRouteRouteId(routeId);
	}

}
