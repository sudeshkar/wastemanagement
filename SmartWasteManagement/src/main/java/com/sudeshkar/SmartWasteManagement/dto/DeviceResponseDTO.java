package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class DeviceResponseDTO {
	private Long Id;
	private String deviceId;      
    private String firmwareVersion;
    private Boolean active;
}
