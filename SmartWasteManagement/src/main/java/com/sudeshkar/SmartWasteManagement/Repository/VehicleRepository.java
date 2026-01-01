package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.VehicleLocation;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	

}
