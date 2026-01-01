package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.dto.AssignZoneToCollectionRDTO;
import com.sudeshkar.SmartWasteManagement.dto.CollectionRouteResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.createCollectionRouteDto;
import com.sudeshkar.SmartWasteManagement.mapper.RouteMapper;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.Zone;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteServiceImpl implements RouteService{
	
	private final CollectionRouteRepository routeRepository;
    private  final SmartRouteEngine smartRouteEngine;
    private final ZoneRepository zoneRepository;
	@Override
	public CollectionRouteResponseDto createRoute(createCollectionRouteDto dto) {
		

        CollectionRoute route = RouteMapper.toEntity(dto);
         

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

        

        existingRoute.setRouteName(dto.getRouteName());
        existingRoute.setDescription(dto.getDescription());
        
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
	
	
	@Override
	public String assignZone(AssignZoneToCollectionRDTO dto) {
		 Zone zone = zoneRepository.findById(dto.getZoneId())
				 .orElseThrow(()->new RuntimeException("Zone Not found with ID "+dto.getZoneId()));
		 
		 CollectionRoute route = routeRepository.findById(dto.getRouteId())
	                .orElseThrow(() ->
	                        new IllegalArgumentException("Route not found with id: " + dto.getRouteId()));
		 
		 zone.getRoutes().add(route);
		 route.setZone(zone);
		 routeRepository.save(route);
		 return "Assigned Sucessfully";
		 
		 
	}

    

}
