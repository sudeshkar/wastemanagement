package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.DeviceRepository;
import com.sudeshkar.SmartWasteManagement.model.Device;

@Service
public class DeviceService {
	@Autowired	
	private DeviceRepository deviceRepository;
	
	public List<Device> getAllDevices(){
		return deviceRepository.findAll();
	}

	public Device getById(Long id) {
		 return deviceRepository.findById(id).orElseThrow(()-> new RuntimeException("Device Not Found"));
	}

	public boolean existById(Long id) {
		 return deviceRepository.existsById(id);
	}

	public void addDevice(Device device) {
		deviceRepository.save(device);
		
	}

	
}
