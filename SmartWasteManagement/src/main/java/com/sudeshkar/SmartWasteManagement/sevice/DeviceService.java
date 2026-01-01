package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.AssignDeviceToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceResponseDTO;

public interface DeviceService {
	
	 	List<DeviceResponseDTO> getAllDevices();

	 	DeviceResponseDTO  getById(Long id);

	    boolean existById(Long id);

	    void addDevice(DeviceRequestDTO dto);
	    
	    String updateDevice(Long deviceId,DeviceRequestDTO dto);
	    
	   DeviceResponseDTO assignDeviceToBin(AssignDeviceToBinDTO dto);
}
