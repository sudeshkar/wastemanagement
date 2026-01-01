package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class DeviceRequestDTO {
	private String deviceId;
    private String firmwareVersion;
    private Boolean active;
}
