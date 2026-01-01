package com.sudeshkar.SmartWasteManagement.mapper;

import com.sudeshkar.SmartWasteManagement.dto.ResponseWasteCollectionDTO;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

public class WasteCollectionMapper {
	
	public static ResponseWasteCollectionDTO toReponseDTO(WasteCollectionLog log) {
		ResponseWasteCollectionDTO dto = new ResponseWasteCollectionDTO();
		dto.setId(log.getLogId());
		dto.setCollectedAt(log.getCollectedAt());
		dto.setBinId(log.getBin().getId());
		dto.setNotes(log.getNotes());
		dto.setVehicleId(log.getVehicle().getVehicleId());
		dto.setWasteWeight(log.getWasteWeight());
		dto.setVehicleNumber(log.getVehicle().getVehicleNumber());
		return dto;
	}
}
