package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RouteAssignmentResponseDto {
	private Long assignmentId;
    private LocalDate assignedDate;
    private String status;

    private Long vehicleId;
    private String vehicleNumber;
}
