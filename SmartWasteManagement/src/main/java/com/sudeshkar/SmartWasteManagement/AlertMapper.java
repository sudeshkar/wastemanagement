package com.sudeshkar.SmartWasteManagement;

import com.sudeshkar.SmartWasteManagement.dto.AlertDto;
import com.sudeshkar.SmartWasteManagement.model.Alert;

public class AlertMapper {
	public static AlertDto toDto(Alert alert) {
        AlertDto dto = new AlertDto();
        dto.setId(alert.getId());
        dto.setType(alert.getType());
        dto.setMessage(alert.getMessage());
        dto.setAcknowledged(alert.isAcknowledged());
        dto.setCreatedAt(alert.getCreatedAt());
        dto.setAcknowledgedAt(alert.getAcknowledgedAt());
        dto.setBinId(alert.getBin().getId());
        return dto;
    }
}
