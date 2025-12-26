package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.CreateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteStepResponseDto;

public interface RouteStepService {
	RouteStepResponseDto createStep(
            Long routeId,
            CreateRouteStepRequestDto request
    );

    List<RouteStepResponseDto> getStepsByRoute(Long routeId);
}
