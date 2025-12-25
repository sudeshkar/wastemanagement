package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDateTime;

import com.sudeshkar.SmartWasteManagement.Enum.RequestStatus;

import lombok.Data;

@Data
public class ServiceRequestDto {
	private Long id;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private Long userId;
    private Long binId;
}
