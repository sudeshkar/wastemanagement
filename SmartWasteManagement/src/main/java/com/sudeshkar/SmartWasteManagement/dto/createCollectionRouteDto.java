package com.sudeshkar.SmartWasteManagement.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class createCollectionRouteDto {
	private String routeName;
    private String description;
    private Long zoneId;
}
