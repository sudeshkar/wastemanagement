package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class UpdateBinRequestDTO {
	private Double locationLat;
    private Double locationLng;
    private Double capacity;
    private Double currentFillLevel;
    private String status;
    private Long zoneId;
}
