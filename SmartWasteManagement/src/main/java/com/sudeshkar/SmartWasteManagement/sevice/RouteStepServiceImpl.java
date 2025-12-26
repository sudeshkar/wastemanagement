package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.RouteStepMapper;
import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.RouteStepRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteStepResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteStep;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteStepServiceImpl implements RouteStepService{
	
	private final RouteStepRepository stepRepo;
    private final CollectionRouteRepository routeRepo;
    private final BinRepository binRepo;
    
	@Override
	public RouteStepResponseDto createStep(Long routeId, CreateRouteStepRequestDto request) {
		   CollectionRoute route = routeRepo.findById(routeId)
	                .orElseThrow(() -> new RuntimeException("Route not found with id: " + routeId));
 
	        Bin bin = binRepo.findById(request.getBinId())
	                .orElseThrow(() -> new RuntimeException("Bin not found with id: " + request.getBinId()));

	        
	        boolean stepOrderExists = stepRepo.existsByRouteAndStepOrder(route, request.getStepOrder());
	        if (stepOrderExists) {
	            throw new RuntimeException(
	                "Step order " + request.getStepOrder() + " already exists for this route"
	            );
	        }

	        
	        RouteStep step = RouteStepMapper.toEntity(
	                route,
	                bin,
	                request.getStepOrder(),
	                request.getNote()
	        );

	        
	        RouteStep savedStep = stepRepo.save(step);

	        
	        return RouteStepMapper.toDto(savedStep);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RouteStepResponseDto> getStepsByRoute(Long routeId) {
   
	        List<RouteStep> steps = stepRepo.findByRouteRouteIdOrderByStepOrder(routeId);

	        
	        return steps.stream()
	                .map(RouteStepMapper::toDto)
	                .toList();
	    }
	
	

}
