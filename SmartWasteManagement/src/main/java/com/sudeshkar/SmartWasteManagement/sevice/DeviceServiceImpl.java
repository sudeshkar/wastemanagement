package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.DeviceRepository;
import com.sudeshkar.SmartWasteManagement.dto.AssignDeviceToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.DeviceResponseDTO;
import com.sudeshkar.SmartWasteManagement.mapper.DeviceMapper;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{
	 
	private final DeviceRepository deviceRepository;
	private final BinRepository binRepository;
	
	@Override
    public List<DeviceResponseDTO> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();

        return devices.stream()
                .map(DeviceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

	@Override
	public DeviceResponseDTO getById(Long id) {
		 Device device = deviceRepository.findById(id).orElseThrow(()-> new RuntimeException("Device not Found With the ID "+id));
		 
		 return DeviceMapper.toResponseDTO(device);
	}

	@Override
	public boolean existById(Long id) {
		return deviceRepository.existsById(id);
	}

	@Override
	public void addDevice(DeviceRequestDTO dto) {
		 Device device = DeviceMapper.toEntity(dto);
		 deviceRepository.save(device);
		 
		
	}

	@Override
	public String updateDevice(Long deviceId, DeviceRequestDTO dto) {
		 Device exDevice = deviceRepository.findById(deviceId).orElseThrow(()-> new RuntimeException("Device not Found With the ID "+deviceId));
		 
		 if (dto.getDeviceId()!=null) {
			 exDevice.setDeviceId(dto.getDeviceId());

		}
		 if (dto.getFirmwareVersion()!=null) {
			 exDevice.setFirmwareVersion(dto.getFirmwareVersion());

		}
		 if (dto.getActive()!=null) {
			 exDevice.setActive(dto.getActive());
		}
		
		 deviceRepository.save(exDevice);
		 return "Update Successfully";
	}

	@Override
	public DeviceResponseDTO assignDeviceToBin(AssignDeviceToBinDTO dto) {

		if (dto.getBinId() == null || dto.getDeviceId() == null) {
	        throw new IllegalArgumentException("Bin ID or Device ID cannot be null");
	    }

	    Bin bin = binRepository.findById(dto.getBinId())
	            .orElseThrow(() -> new RuntimeException("Bin Not Found with ID " + dto.getBinId()));

	    Device device = deviceRepository.findById(dto.getDeviceId())
	            .orElseThrow(() -> new RuntimeException("Device not Found With the ID " + dto.getDeviceId()));

	    
	    if (device.getId() == null || bin.getId() == null) {
	        throw new RuntimeException("Device or Bin ID is null");
	    }

	   
	    bin.setDevice(device);
	    device.setBin(bin);

	    
	    deviceRepository.save(device);  
	     

	    return DeviceMapper.toResponseDTO(device);
	}


	
	
}
