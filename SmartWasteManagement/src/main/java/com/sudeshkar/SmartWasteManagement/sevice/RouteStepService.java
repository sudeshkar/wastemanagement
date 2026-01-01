package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.CreateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteStepResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.UpdateRouteStepRequestDto;

public interface RouteStepService {
	RouteStepResponseDto createStep(
            Long routeId,
            CreateRouteStepRequestDto request
    );

    List<RouteStepResponseDto> getStepsByRoute(Long routeId);
    
    RouteStepResponseDto getStepById(Long stepId);
    
    RouteStepResponseDto updateStep(Long routeId, Long stepId, UpdateRouteStepRequestDto request);
    void deleteStep(Long routeId, Long stepId);
}
