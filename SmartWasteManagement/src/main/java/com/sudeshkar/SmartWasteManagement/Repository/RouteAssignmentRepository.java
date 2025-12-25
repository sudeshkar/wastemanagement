package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;

public interface RouteAssignmentRepository extends JpaRepository<RouteAssignment, Long>{
	List<RouteAssignment> findByRouteRouteId(Long routeId);

}
