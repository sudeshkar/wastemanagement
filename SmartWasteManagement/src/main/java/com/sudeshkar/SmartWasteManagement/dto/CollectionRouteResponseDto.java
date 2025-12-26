package com.sudeshkar.SmartWasteManagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class CollectionRouteResponseDto {
	private Long routeId;
    private String routeName;
    private String description;
    private ZoneResponseDto zone;
    private List<RouteAssignmentDto> assignments;
}
