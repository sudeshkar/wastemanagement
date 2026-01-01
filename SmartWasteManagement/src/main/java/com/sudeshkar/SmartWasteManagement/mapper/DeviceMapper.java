package com.sudeshkar.SmartWasteManagement.mapper;

import com.sudeshkar.SmartWasteManagement.dto.DeviceRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceResponseDTO;
import com.sudeshkar.SmartWasteManagement.model.Device;

public class DeviceMapper {
	public static DeviceResponseDTO toResponseDTO(Device device) {
        if (device == null) return null;

        DeviceResponseDTO dto = new DeviceResponseDTO();
        dto.setId(device.getId());
        dto.setDeviceId(device.getDeviceId());
        dto.setFirmwareVersion(device.getFirmwareVersion());
        dto.setActive(device.getActive());

        return dto;
    }
	
	public static Device toEntity(DeviceRequestDTO dto) {
		if(dto == null) return null;
		Device device = new Device();
		device.setActive(dto.getActive());
		device.setDeviceId(dto.getDeviceId());
		device.setFirmwareVersion(dto.getFirmwareVersion());
		return device;
	}
}
