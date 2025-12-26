package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.CollectionRouteResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.createCollectionRouteDto;

public interface RouteService {
	CollectionRouteResponseDto createRoute(createCollectionRouteDto dto);

    List<CollectionRouteResponseDto> getAllRoutes();

    CollectionRouteResponseDto getRouteById(Long routeId);

    CollectionRouteResponseDto updateRoute(Long routeId, createCollectionRouteDto dto);

    void deleteRoute(Long routeId);

    CollectionRouteResponseDto generateSmartRoute();
}
