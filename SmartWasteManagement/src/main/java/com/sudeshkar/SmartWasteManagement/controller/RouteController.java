package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.sevice.RouteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
	private final RouteService routeService;

    @PostMapping
    public ResponseEntity<CollectionRoute> createRoute(@RequestBody CollectionRoute route) {
        return ResponseEntity.ok(routeService.createRoute(route));
    }

    @GetMapping
    public ResponseEntity<List<CollectionRoute>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionRoute> getRoute(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionRoute> updateRoute(
            @PathVariable Long id,
            @RequestBody CollectionRoute route) {
        return ResponseEntity.ok(routeService.updateRoute(id, route));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * SMART ROUTE GENERATION
     */
    @PostMapping("/generate")
    public ResponseEntity<CollectionRoute> generateRoute() {
        return ResponseEntity.ok(routeService.generateSmartRoute());
    }
}
