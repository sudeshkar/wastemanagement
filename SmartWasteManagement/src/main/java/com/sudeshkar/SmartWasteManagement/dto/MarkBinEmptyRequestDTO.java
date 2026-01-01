package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class MarkBinEmptyRequestDTO {
	private Long binId;
	private Long vehicleId;
	private Double wasteWeight;
	private String notes;

}
