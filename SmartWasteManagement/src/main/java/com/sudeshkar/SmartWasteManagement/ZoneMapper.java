package com.sudeshkar.SmartWasteManagement;

import java.util.stream.Collectors;

import com.sudeshkar.SmartWasteManagement.dto.ZoneDto;
import com.sudeshkar.SmartWasteManagement.dto.ZoneResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Zone;

public class ZoneMapper {
	public static ZoneDto toDto(Zone zone) {
        if (zone == null) return null;

        ZoneDto dto = ZoneDto.builder()
                .zoneId(zone.getZoneId())
                .zoneName(zone.getZoneName())
                .description(zone.getDescription())
                .bins(zone.getBins() != null ? zone.getBins() : null)  
                .routes(zone.getRoutes() != null 
                        ? zone.getRoutes().stream().collect(Collectors.toList()) 
                        : null)
                .build();

        return dto;
    }
	public static ZoneResponseDto toDtoResponse(Zone zone) {
        if (zone == null) return null;

        ZoneResponseDto dto = ZoneResponseDto.builder()
                .zoneId(zone.getZoneId())
                .zoneName(zone.getZoneName())
                .description(zone.getDescription())                
                .build();

        return dto;
    }

}
