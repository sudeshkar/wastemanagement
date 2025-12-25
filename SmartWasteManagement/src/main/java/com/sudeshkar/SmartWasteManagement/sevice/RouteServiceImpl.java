package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteStep;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService{
	private final CollectionRouteRepository routeRepository;
    private final BinRepository binRepository;

    @Override
    public CollectionRoute createRoute(CollectionRoute route) {
        return routeRepository.save(route);
    }

    @Override
    public List<CollectionRoute> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public CollectionRoute getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
    }

    @Override
    public CollectionRoute updateRoute(Long id, CollectionRoute route) {
        CollectionRoute existing = getRouteById(id);
        existing.setRouteName(route.getRouteName());
        existing.setDescription(route.getDescription());
        existing.setZone(route.getZone());
        return routeRepository.save(existing);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    /**
     * SMART ROUTE GENERATION (basic version)
     */
    @Override
    public CollectionRoute generateSmartRoute() {
        List<Bin> fullBins = binRepository.findByCurrentFillLevelGreaterThan(80.0);

        CollectionRoute route = new CollectionRoute();
        route.setRouteName("Auto Generated Route");
        route.setDescription("Generated based on full bins");

        int order = 1;
        for (Bin bin : fullBins) {
            RouteStep step = new RouteStep();
            step.setStepOrder(order++);
            step.setBin(bin);
            step.setRoute(route);
        }

        return routeRepository.save(route);
    }

}
