package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.RouteAssignmentMapper;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.RouteAssignmentRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateRouteAssignmentRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteAssignmentResponseDto;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteAssignmentServiceImpl implements RouteAssignmentService{
	
	private final RouteAssignmentRepository assignmentRepo;
    private final CollectionRouteRepository routeRepo;
    private final VehicleRepository vehicleRepo;
   

	
	@Override
	public RouteAssignmentResponseDto assignRoute(Long routeId, CreateRouteAssignmentRequestDto request) {
		CollectionRoute route = routeRepo.findById(routeId)
                .orElseThrow(() ->
                        new RuntimeException("Route not found with id: " + routeId));

        
        Vehicle vehicle = vehicleRepo.findById(request.getVehicleId())
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found with id: " + request.getVehicleId()));

        
        boolean alreadyAssigned = assignmentRepo
                .existsByRouteAndVehicleAndAssignedDate(
                        route, vehicle, LocalDate.now());

        if (alreadyAssigned) {
            throw new RuntimeException(
                    "This vehicle is already assigned to the route today");
        }
 
        RouteAssignment assignment = new RouteAssignment();
        assignment.setRoute(route);
        assignment.setVehicle(vehicle);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setStatus("ASSIGNED");

        
        RouteAssignment saved = assignmentRepo.save(assignment);

        
        return RouteAssignmentMapper.toDto(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RouteAssignmentResponseDto> getAssignmentsByRoute(Long routeId) {
		 CollectionRoute route = routeRepo.findById(routeId)
	                .orElseThrow(() ->
	                        new RuntimeException("Route not found with id: " + routeId));

	         
	        List<RouteAssignment> assignments =
	                assignmentRepo.findByRouteOrderByAssignedDateDesc(route);

	       
	        return assignments.stream()
	                .map(RouteAssignmentMapper::toDto)
	                .toList();
	}
	
	
}
