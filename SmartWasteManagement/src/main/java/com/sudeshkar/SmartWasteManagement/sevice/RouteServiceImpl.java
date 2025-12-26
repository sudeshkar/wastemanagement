package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.RouteMapper;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.dto.CollectionRouteResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.createCollectionRouteDto;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.Zone;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteServiceImpl implements RouteService{
	private final CollectionRouteRepository routeRepository;
    private final ZoneRepository zoneRepository;
    private  final SmartRouteEngine smartRouteEngine;
	@Override
	public CollectionRouteResponseDto createRoute(createCollectionRouteDto dto) {
		Zone zone = zoneRepository.findById(dto.getZoneId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Zone not found with id: " + dto.getZoneId()));

        CollectionRoute route = RouteMapper.toEntity(dto);
        route.setZone(zone);

        CollectionRoute savedRoute = routeRepository.save(route);

        return RouteMapper.toDto(savedRoute);
	}
	@Override
	@Transactional(readOnly = true)
	public List<CollectionRouteResponseDto> getAllRoutes() {
		return routeRepository.findAll().stream()
                .map(RouteMapper::toDto)
                .collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public CollectionRouteResponseDto getRouteById(Long routeId) {
		CollectionRoute route = routeRepository.findById(routeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Route not found with id: " + routeId));

        return RouteMapper.toDto(route);
	}
	@Override
	public CollectionRouteResponseDto updateRoute(Long routeId, createCollectionRouteDto dto) {
		CollectionRoute existingRoute = routeRepository.findById(routeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Route not found with id: " + routeId));

        Zone zone = zoneRepository.findById(dto.getZoneId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Zone not found with id: " + dto.getZoneId()));

        existingRoute.setRouteName(dto.getRouteName());
        existingRoute.setDescription(dto.getDescription());
        existingRoute.setZone(zone);

        CollectionRoute updatedRoute = routeRepository.save(existingRoute);

        return RouteMapper.toDto(updatedRoute);
	}
	@Override
	public void deleteRoute(Long routeId) {
		if (!routeRepository.existsById(routeId)) {
            throw new IllegalArgumentException("Route not found with id: " + routeId);
        }

        routeRepository.deleteById(routeId);
		
	}
	@Override
	public CollectionRouteResponseDto generateSmartRoute() {
	
		CollectionRoute smartRoute = smartRouteEngine.generate();

	    CollectionRoute savedRoute = routeRepository.save(smartRoute);

	    return RouteMapper.toDto(savedRoute);
	}

    

}
