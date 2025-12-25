package com.sudeshkar.SmartWasteManagement.Repository;

 


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.IoTSensorData;

public interface IoTSensorDataRepository extends JpaRepository<IoTSensorData, Long>{
	List<IoTSensorData> findTop10ByOrderByTimestampDesc();
	List<IoTSensorData>findByDevice_Bin_IdOrderByTimestampDesc(Long binId);
	Page<IoTSensorData> findByDevice_Bin_IdAndTimestampBetween(
	        Long binId,
	        LocalDateTime start,
	        LocalDateTime end,
	        Pageable pageable
	);

}
