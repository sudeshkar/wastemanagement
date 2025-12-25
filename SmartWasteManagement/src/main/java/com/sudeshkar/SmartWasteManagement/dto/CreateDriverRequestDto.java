package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class CreateDriverRequestDto {
	private Long userId;
    private String licenseNumber;
    private String phoneNumber;
    private Long assignedVehicleId; 
}
