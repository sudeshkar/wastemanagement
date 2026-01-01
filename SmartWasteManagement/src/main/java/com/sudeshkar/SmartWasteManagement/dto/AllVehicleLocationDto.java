package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDateTime;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AllVehicleLocationDto {
	
	private Long vehicleId;
	private String vehicleNumber;
	private VehicleStatus status;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
