package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data	
public class DriverResponseDto {
	private Long driverId;
    private Long userId;
    private String email;
    private String licenseNumber;
    private String phoneNumber;
    private Long assignedVehicleId;
    private String assignedVehicleNumber;
}
