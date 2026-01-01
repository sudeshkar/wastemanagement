package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseWasteCollectionDTO {
	private Long id;
	private LocalDateTime collectedAt;
	private Double wasteWeight;
	private String notes;
	private Long binId;
	private Long vehicleId;
	private String vehicleNumber;
}
