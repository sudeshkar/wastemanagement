package com.sudeshkar.SmartWasteManagement;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sudeshkar.SmartWasteManagement.dto.CollectionRouteResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteAssignmentDto;
import com.sudeshkar.SmartWasteManagement.dto.createCollectionRouteDto;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class RouteMapper {
	 

    public static CollectionRoute toEntity(createCollectionRouteDto dto) {
        CollectionRoute route = new CollectionRoute();
        route.setRouteName(dto.getRouteName());
        route.setDescription(dto.getDescription());
        return route;
    }

     

    public static CollectionRouteResponseDto toDto(CollectionRoute route) {
        CollectionRouteResponseDto dto = new CollectionRouteResponseDto();
        dto.setRouteId(route.getRouteId());
        dto.setRouteName(route.getRouteName());
        dto.setDescription(route.getDescription());

        if (route.getZone() != null) {
            dto.setZone(ZoneMapper.toDtoResponse(route.getZone()));
        }

        if (route.getAssignments() != null) {
            dto.setAssignments(
                route.getAssignments()
                     .stream()
                     .map(RouteMapper::toAssignmentDto)
                     .collect(Collectors.toList())
            );
        }

        return dto;
    }

   

    private static RouteAssignmentDto toAssignmentDto(RouteAssignment assignment) {
        RouteAssignmentDto dto = new RouteAssignmentDto();
        dto.setAssignmentId(assignment.getAssignmentId());
        dto.setAssignedDate(assignment.getAssignedDate());
        dto.setStatus(assignment.getStatus());

        if (assignment.getVehicle() != null) {
            dto.setVehicleId(assignment.getVehicle().getVehicleId());
        }

        return dto;
    }
	
    
}
