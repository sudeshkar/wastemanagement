package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;

public interface RouteService {
	CollectionRoute createRoute(CollectionRoute route);
    List<CollectionRoute> getAllRoutes();
    CollectionRoute getRouteById(Long id);
    CollectionRoute updateRoute(Long id, CollectionRoute route);
    void deleteRoute(Long id);

    CollectionRoute generateSmartRoute();
}
