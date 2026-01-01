package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sudeshkar.SmartWasteManagement.model.VehicleLocation;

public interface VehicleLocationRepository extends JpaRepository<VehicleLocation, Long>{

	VehicleLocation findTopByVehicleVehicleIdOrderByTimestampDesc(Long vehicleId);
	@Query("""
	        SELECT vl
	        FROM VehicleLocation vl
	        WHERE vl.timestamp = (
	            SELECT MAX(vl2.timestamp)
	            FROM VehicleLocation vl2
	            WHERE vl2.vehicle.vehicleId = vl.vehicle.vehicleId
	        )
	    """)
	    List<VehicleLocation> findLatestLocationForAllVehicles();

}
