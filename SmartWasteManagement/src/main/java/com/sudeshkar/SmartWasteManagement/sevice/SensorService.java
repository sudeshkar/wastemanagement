package com.sudeshkar.SmartWasteManagement.sevice;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

 

import com.sudeshkar.SmartWasteManagement.dto.SensorReadingRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.SensorReadingResponseDTO;

public interface SensorService {
	
	void saveSensorReading(SensorReadingRequestDTO dto);

    List<SensorReadingResponseDTO> getRecentReadings();

    List<SensorReadingResponseDTO> getSensorDataByBin(Long binId);

    Page<SensorReadingResponseDTO> getSensorDataByBinAndDateRange(
            Long binId,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable);
}
