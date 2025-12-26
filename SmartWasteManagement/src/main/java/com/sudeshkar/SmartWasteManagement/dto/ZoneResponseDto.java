package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZoneResponseDto {
	private Long zoneId;
    private String zoneName;
    private String description;
}
