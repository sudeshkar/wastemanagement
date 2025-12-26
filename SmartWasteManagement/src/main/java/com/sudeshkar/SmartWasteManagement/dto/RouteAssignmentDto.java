package com.sudeshkar.SmartWasteManagement.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RouteAssignmentDto {
	private Long assignmentId;
    private LocalDate assignedDate;
    private String status;
    private Long vehicleId;
}
