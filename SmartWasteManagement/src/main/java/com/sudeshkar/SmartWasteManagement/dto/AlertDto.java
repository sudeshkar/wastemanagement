package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDateTime;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;

import lombok.Data;

@Data
public class AlertDto {
	private Long id;
    private AlertType type;
    private String message;
    private boolean acknowledged;
    private LocalDateTime createdAt;
    private LocalDateTime acknowledgedAt;
    private Long binId;
}
