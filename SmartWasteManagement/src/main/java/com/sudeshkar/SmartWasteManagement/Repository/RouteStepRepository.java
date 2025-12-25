package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.RouteStep;

public interface RouteStepRepository extends JpaRepository<RouteStep, Long> {
	List<RouteStep> findByRouteRouteIdOrderByStepOrder(Long routeId);

}
