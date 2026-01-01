package com.sudeshkar.SmartWasteManagement.mapper;

import org.springframework.stereotype.Component;

import com.sudeshkar.SmartWasteManagement.dto.RouteAssignmentResponseDto;
import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

@Component
public class RouteAssignmentMapper {
	public static RouteAssignmentResponseDto toDto(RouteAssignment assignment) {
        
		RouteAssignmentResponseDto dto = new RouteAssignmentResponseDto();

        dto.setAssignmentId(assignment.getAssignmentId());
        dto.setAssignedDate(assignment.getAssignedDate());
        dto.setStatus(assignment.getStatus());

        Vehicle vehicle = assignment.getVehicle();
        if (vehicle != null) {
            dto.setVehicleId(vehicle.getVehicleId());
            dto.setVehicleNumber(vehicle.getVehicleNumber());
        }

        return dto;
    }
}
