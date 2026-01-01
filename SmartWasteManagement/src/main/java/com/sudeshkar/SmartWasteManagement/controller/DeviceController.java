package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.dto.AssignDeviceToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceResponseDTO;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device; 
import com.sudeshkar.SmartWasteManagement.sevice.DeviceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;   

    
    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> getDevices() {
        List<DeviceResponseDTO> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

     
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        try {
            
            return new ResponseEntity<>(deviceService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device Not Found");
        }
    }

    // Create a new device
    @PostMapping
    public ResponseEntity<String> createDevice(@RequestBody DeviceRequestDTO dto) {
        if (dto == null || dto.getDeviceId() == null) {
            return ResponseEntity.badRequest().body("Invalid device data");
        }
     
           try {
        	   deviceService.addDevice(dto); 
               
               return ResponseEntity.status(HttpStatus.CREATED).body("Device Created Successfully");
			
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to create device "+e.getMessage());
		}
            
       
    }

     
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDeviceDetails(@PathVariable Long id, @RequestBody DeviceRequestDTO dto) {
        if (!deviceService.existById(id)) {
            return new ResponseEntity<>("Device Not Exist. Please Add the Device", HttpStatus.NOT_FOUND);
        }
        
        try {
        	deviceService.updateDevice(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Device Updated Successfully!");
			
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to Update device "+e.getMessage());

		}
        
    }
    
    @PostMapping("/assign-device-tobin")
    public ResponseEntity<?> assignDevice(@RequestBody AssignDeviceToBinDTO dto){
    	try {
			
			return new ResponseEntity<DeviceResponseDTO>(deviceService.assignDeviceToBin(dto),HttpStatus.OK);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to Update device "+e.getMessage());

		}
    }
}
