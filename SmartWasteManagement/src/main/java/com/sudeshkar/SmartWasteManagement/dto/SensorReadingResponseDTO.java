package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SensorReadingResponseDTO {
	
	 private Long id;
	    private Double fillLevel;
	    private Double gasLevel;
	    private Double temperature;
	    private LocalDateTime timestamp;
	    private Long deviceId;
}
