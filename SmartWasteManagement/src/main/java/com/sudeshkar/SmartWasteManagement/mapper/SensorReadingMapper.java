package com.sudeshkar.SmartWasteManagement.mapper;

import com.sudeshkar.SmartWasteManagement.dto.SensorReadingResponseDTO;
import com.sudeshkar.SmartWasteManagement.model.IoTSensorData;

public class SensorReadingMapper {
	
	 public static SensorReadingResponseDTO toDTO(IoTSensorData entity) {
	        if (entity == null) {
	            return null;
	        }

	        SensorReadingResponseDTO dto = new SensorReadingResponseDTO();
	        dto.setId(entity.getId());
	        dto.setFillLevel(entity.getFillLevel());
	        dto.setGasLevel(entity.getGasLevel());
	        dto.setTemperature(entity.getTemperature());
	        dto.setTimestamp(entity.getTimestamp());
	        dto.setDeviceId(entity.getDevice().getId());

	        return dto;
	    }
}
