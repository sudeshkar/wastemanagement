package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class BinResponseDTO {
	private Long id;
    private Double locationLat;
    private Double locationLng;
    private Double capacity;
    private Double currentFillLevel;
    private String status;
    private String deviceId;
    private String zoneName;
    private Long zoneId;
}
