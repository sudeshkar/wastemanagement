package com.sudeshkar.SmartWasteManagement.mapper;

import com.sudeshkar.SmartWasteManagement.dto.BinResponseDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinRequestDTO;
import com.sudeshkar.SmartWasteManagement.model.Bin;

public class BinMapper {
	public static BinResponseDTO toDTO(Bin bin) {
        if (bin == null) return null;

        BinResponseDTO dto = new BinResponseDTO();
        dto.setId(bin.getId());
        dto.setLocationLat(bin.getLocation_lat());
        dto.setLocationLng(bin.getLocation_lng());
        dto.setCapacity(bin.getCapacity());
        dto.setCurrentFillLevel(bin.getCurrentFillLevel());
        dto.setStatus(bin.getStatus());

        

        if (bin.getDevice() != null) {
            dto.setDeviceId(bin.getDevice().getDeviceId());
        }
        
        return dto;
    }
	
	public static Bin toEntity(BinRequestDTO dto) {
	    Bin bin = new Bin();
	    bin.setLocation_lat(dto.getLocationLat());
	    bin.setLocation_lng(dto.getLocationLng());
	    bin.setCapacity(dto.getCapacity());
	     
	    return bin;
	}
}
