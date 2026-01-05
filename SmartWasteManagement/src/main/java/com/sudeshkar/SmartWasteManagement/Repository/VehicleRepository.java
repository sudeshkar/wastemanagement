package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	boolean existsByVehicleNumber(String number);

	Optional<Vehicle> findByVehicleNumber(String vehicleNo);
	

}
