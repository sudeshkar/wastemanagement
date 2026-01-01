package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class UpdateRouteStepRequestDto {
	private Integer stepOrder;
	private String note;
	private Long binId;   
}
