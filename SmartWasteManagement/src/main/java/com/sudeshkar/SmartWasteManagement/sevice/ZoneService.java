package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.ZoneRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ZoneResponseDto;

public interface ZoneService {
	
	List<ZoneResponseDto> getAllZones();

    ZoneResponseDto getZoneById(Long id);

    void createZone(ZoneRequestDto dto);

    void updateZone(Long id, ZoneRequestDto dto);

    void deleteZone(Long id);

    boolean existsById(Long id);
}
