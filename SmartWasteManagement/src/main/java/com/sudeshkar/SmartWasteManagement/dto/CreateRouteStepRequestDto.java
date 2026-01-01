package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class CreateRouteStepRequestDto {
	private Long binId;
    private Integer stepOrder;
    private String note;
}
