package com.sudeshkar.SmartWasteManagement.dto;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;

import lombok.Data;

@Data
public class VehicleResponseDto {
	private Long vehicleId;
    private String vehicleNumber;
    private VehicleStatus status;
    private Long driverId;
    private String driverEmail;
}
