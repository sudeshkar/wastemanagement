package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class CreateRouteStepRequestDto {
	private Long binId;
    private int stepOrder;
    private String note;
}
