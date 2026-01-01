package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class SensorReadingRequestDTO {

	private Long id;      // ESP32 MAC or unique ID
    private Double fillLevel;
    private Double gasLevel;
    private Double temperature;
}
