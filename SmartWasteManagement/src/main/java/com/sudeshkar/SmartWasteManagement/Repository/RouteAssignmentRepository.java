package com.sudeshkar.SmartWasteManagement.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteAssignment;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;

public interface RouteAssignmentRepository extends JpaRepository<RouteAssignment, Long>{
	List<RouteAssignment> findByRouteRouteId(Long routeId);

	boolean existsByRouteAndVehicleAndAssignedDate(CollectionRoute route, Vehicle vehicle, LocalDate now);

	List<RouteAssignment> findByRouteOrderByAssignedDateDesc(CollectionRoute route);

}
