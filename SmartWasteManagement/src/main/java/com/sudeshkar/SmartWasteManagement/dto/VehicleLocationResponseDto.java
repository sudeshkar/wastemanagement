package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleLocationResponseDto {
	
	private Long vehicleId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
