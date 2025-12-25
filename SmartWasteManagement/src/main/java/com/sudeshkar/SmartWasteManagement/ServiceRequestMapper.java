package com.sudeshkar.SmartWasteManagement;

import com.sudeshkar.SmartWasteManagement.dto.ServiceRequestDto;
import com.sudeshkar.SmartWasteManagement.model.ServiceRequest;

public class ServiceRequestMapper {

	public static ServiceRequestDto toDto(ServiceRequest entity) {
        ServiceRequestDto dto = new ServiceRequestDto();
        dto.setId(entity.getRequestId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setResolvedAt(entity.getResolvedAt());
        dto.setUserId(entity.getUser().getId());
        dto.setBinId(entity.getBin().getId());
        return dto;
    }
}
