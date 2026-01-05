package com.sudeshkar.SmartWasteManagement.mapper;

import com.sudeshkar.SmartWasteManagement.dto.ZoneRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ZoneResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Zone;

public class ZoneMapper {
	 
	public static ZoneResponseDto toDtoResponse(Zone zone) {
        if (zone == null) return null;

        ZoneResponseDto dto = ZoneResponseDto.builder()
                .zoneId(zone.getZoneId())
                .zoneName(zone.getZoneName())
                .description(zone.getDescription())                
                .build();

        return dto;
    }
	
	public static Zone toEntity(ZoneRequestDto dto) {
		if (dto==null) return null;
		Zone zone =  new Zone();
		zone.setZoneName(dto.getZoneName());
		zone.setDescription(dto.getDescription());
		return zone;
	}

}
