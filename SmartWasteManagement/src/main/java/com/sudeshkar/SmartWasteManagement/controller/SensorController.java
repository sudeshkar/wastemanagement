package com.sudeshkar.SmartWasteManagement.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.SensorReadingRequestDTO;
import com.sudeshkar.SmartWasteManagement.model.IoTSensorData;
import com.sudeshkar.SmartWasteManagement.sevice.SensorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sensors")
@RequiredArgsConstructor
public class SensorController {
	private final SensorService sensorService;
	
	@PostMapping("/reading")
    public ResponseEntity<String> receiveSensorReading(@RequestBody SensorReadingRequestDTO dto) {

        if (dto == null || dto.getDeviceId() == null) {
            return ResponseEntity.badRequest()
                    .body("Invalid sensor payload");
        }

        try {
            sensorService.saveSensorReading(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Sensor data saved");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
	
	@GetMapping("/recent")
    public ResponseEntity<List<IoTSensorData>> getRecentReadings() {
        return ResponseEntity.ok(sensorService.getRecentReadings());
    }
	
	@GetMapping("/bin/{binId}")
	public ResponseEntity<List<IoTSensorData>> getSensorDataByBin(@PathVariable Long binId) {

	    List<IoTSensorData> data = sensorService.getSensorDataByBin(binId);

	    if (data.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
	    }

	    return ResponseEntity.ok(data);
	}
	
	@GetMapping("/bin/{binId}/range")
	public ResponseEntity<Page<IoTSensorData>> getSensorDataByBinWithRange(
	        @PathVariable Long binId,
	        @RequestParam String start,
	        @RequestParam String end,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

	    LocalDateTime startDate = LocalDateTime.parse(start);
	    LocalDateTime endDate = LocalDateTime.parse(end);

	    Pageable pageable = PageRequest.of(page, size);

	    Page<IoTSensorData> result =
	            sensorService.getSensorDataByBinAndDateRange(
	                    binId, startDate, endDate, pageable);

	    return ResponseEntity.ok(result);
	}


}
