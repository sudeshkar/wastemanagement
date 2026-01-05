package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Zone;

@Repository
public interface BinRepository extends JpaRepository<Bin,Long>{
	 List<Bin> findByCurrentFillLevelGreaterThan(Double threshold);

	 List<Bin> findByZone(Zone zone);

}
