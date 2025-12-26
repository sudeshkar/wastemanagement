package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class RouteStepResponseDto {
	private Long id;
    private int stepOrder;
    private String note;

    private Long binId;
    private String binCode; 
}
