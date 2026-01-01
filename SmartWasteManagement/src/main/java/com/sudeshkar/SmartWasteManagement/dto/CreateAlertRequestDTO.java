package com.sudeshkar.SmartWasteManagement.dto;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;

import lombok.Data;
@Data
public class CreateAlertRequestDTO {
	private Long binId;
	private String message;
	private AlertType alertType;
}
