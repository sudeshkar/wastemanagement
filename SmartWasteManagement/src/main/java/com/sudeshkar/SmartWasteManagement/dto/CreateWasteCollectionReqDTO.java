package com.sudeshkar.SmartWasteManagement.dto;

import lombok.Data;

@Data
public class CreateWasteCollectionReqDTO {
	private Double wasteWeight;
	private String notes;
	private Long binId;
	private Long vehicleId;

}
