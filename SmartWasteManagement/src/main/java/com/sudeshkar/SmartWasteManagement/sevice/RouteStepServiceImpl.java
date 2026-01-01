package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.RouteStepRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.RouteStepResponseDto;
import com.sudeshkar.SmartWasteManagement.dto.UpdateRouteStepRequestDto;
import com.sudeshkar.SmartWasteManagement.mapper.RouteStepMapper;
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
	        step.setRoute(route);
	       
	        step.setBin(bin);

	        
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

	@Override
	public RouteStepResponseDto getStepById(Long stepId) {
		 
		 RouteStep step = stepRepo.findById(stepId)
				 .orElseThrow(()->new RuntimeException("RouteStep Not Found with ID "+stepId));
		 return RouteStepMapper.toDto(step);
	}

	@Override
	@Transactional
	public RouteStepResponseDto updateStep(Long routeId, Long stepId, UpdateRouteStepRequestDto request) {

	    
	    RouteStep step = stepRepo.findById(stepId)
	            .orElseThrow(() -> new RuntimeException("RouteStep with ID " + stepId + " not found"));

	     
	    if (!step.getRoute().getRouteId().equals(routeId)) {
	        throw new RuntimeException("RouteStep " + stepId + " does not belong to route " + routeId);
	    }

	    
	    if (request.getStepOrder() != null && !request.getStepOrder().equals(step.getStepOrder())) {
	        boolean exists = stepRepo.existsByRouteAndStepOrder(step.getRoute(), request.getStepOrder());
	        if (exists) {
	            throw new RuntimeException(
	                    "Step order " + request.getStepOrder() + " already exists for this route");
	        }
	        step.setStepOrder(request.getStepOrder());
	    }

	   
	    if (request.getNote() != null) {
	        step.setNote(request.getNote());
	    }

	    
	    if (request.getBinId() != null) {
	        Bin bin = binRepo.findById(request.getBinId())
	                .orElseThrow(() -> new RuntimeException("Bin not found with ID " + request.getBinId()));
	        step.setBin(bin);
	    }

	     
	    stepRepo.save(step);  
	    return RouteStepMapper.toDto(step);
	}


	@Override
	public void deleteStep(Long routeId, Long stepId) {
		 RouteStep step = stepRepo.findById(stepId)
		            .orElseThrow(() -> new RuntimeException("RouteStep with ID " + stepId + " not found"));

		    if (!step.getRoute().getRouteId().equals(routeId)) {
		        throw new RuntimeException("RouteStep " + stepId + " does not belong to route " + routeId);
		    }

		    stepRepo.delete(step);
		
	}
	
	

}
