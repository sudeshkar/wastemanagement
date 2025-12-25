package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.RouteStepRepository;
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
	public RouteStep createStep(Long routeId, Long binId, int order, String note) {
		CollectionRoute route = routeRepo.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Bin bin = binRepo.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        RouteStep step = new RouteStep();
        step.setRoute(route);
        step.setBin(bin);
        step.setStepOrder(order);
        step.setNote(note);

        return stepRepo.save(step);
	}

	@Override
	public List<RouteStep> getStepsByRoute(Long routeId) {
		return stepRepo.findByRouteRouteIdOrderByStepOrder(routeId);
	}

}
