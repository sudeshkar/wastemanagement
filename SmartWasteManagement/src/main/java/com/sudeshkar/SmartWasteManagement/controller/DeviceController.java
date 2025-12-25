package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.DeviceRequestDTO;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device;
import com.sudeshkar.SmartWasteManagement.sevice.BinService;
import com.sudeshkar.SmartWasteManagement.sevice.DeviceService;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {
	
	@Autowired
	private  DeviceService deviceService;
	
	@Autowired
	private BinService binService;
	
	@GetMapping
	public ResponseEntity<List<Device>> getDevices(){
		List<Device> devices = deviceService.getAllDevices();
		
		return ResponseEntity.ok(devices);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDeviceById(@PathVariable Long id){
		try {
			Device device=deviceService.getById(id);
			return new ResponseEntity<Device>(device,HttpStatus.OK);
			
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device Not Found");
		}
		
	}
	@PostMapping
	public ResponseEntity<String> createDevice(@RequestBody DeviceRequestDTO dto) {

	    if (dto == null || dto.getDeviceId() == null || dto.getBinId() == null) {
	        return ResponseEntity.badRequest().body("Invalid device data");
	    }
	    try {
	    	Bin bin = binService.getBinById(dto.getBinId());
	    	Device device = new Device();
		    device.setDeviceId(dto.getDeviceId());
		    device.setFirmwareVersion(dto.getFirmwareVersion());
		    device.setActive(dto.getActive());
		    device.setBin(bin);
		    deviceService.addDevice(device);
		    
		    return ResponseEntity.status(HttpStatus.CREATED).body("Device Created Successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid Bin ID");
		}


	    

	    
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateDeviceDetails(@PathVariable Long id,@RequestBody DeviceRequestDTO dto){
		if (!deviceService.existById(id)) {
			return new ResponseEntity<String>("Device Not Exist Please Add the Device",HttpStatus.NOT_FOUND);
		}
		Device existingDevice = deviceService.getById(id);
		if (dto.getDeviceId() != null)
		    existingDevice.setDeviceId(dto.getDeviceId());

		if (dto.getFirmwareVersion() != null)
		    existingDevice.setFirmwareVersion(dto.getFirmwareVersion());

		if (dto.getActive() != null)
		    existingDevice.setActive(dto.getActive());

		if (dto.getBinId() != null) {
		    Bin bin = binService.getBinById(dto.getBinId());
		    if (bin == null)
		        return ResponseEntity.badRequest().body("Invalid Bin ID");
		    existingDevice.setBin(bin);
		}

		deviceService.addDevice(existingDevice);
		return ResponseEntity.status(HttpStatus.CREATED).body("Device Updated Successfully!");
	}

}
