package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.IoTSensorDataRepository;
import com.sudeshkar.SmartWasteManagement.dto.SensorReadingRequestDTO;
import com.sudeshkar.SmartWasteManagement.model.Device;
import com.sudeshkar.SmartWasteManagement.model.IoTSensorData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SensorService {
	private final DeviceService deviceService;
    private final IoTSensorDataRepository sensorRepository;
    
    private static final double BIN_FULL_THRESHOLD = 80.0;
    private static final double GAS_DANGER_THRESHOLD = 20.0;

    
    public void saveSensorReading(SensorReadingRequestDTO dto) {

        Device device = deviceService.getById(Long.valueOf(dto.getDeviceId()));
        if (device == null || !device.getActive()) {
            throw new RuntimeException("Invalid or inactive device");
        }

        IoTSensorData data = new IoTSensorData();
        data.setDevice(device);
        data.setFillLevel(dto.getFillLevel());
        data.setGasLevel(dto.getGasLevel());
        data.setTemperature(dto.getTemperature());
        data.setTimestamp(LocalDateTime.now());
        
        if (dto.getFillLevel() != null &&
        	    dto.getFillLevel() >= BIN_FULL_THRESHOLD) {

        	    
        	    System.out.println("⚠️ Bin almost full: " +
        	            device.getBin().getId());
        	}

        	if (dto.getGasLevel() != null &&
        	    dto.getGasLevel() >= GAS_DANGER_THRESHOLD) {

        	    
        	    System.out.println("🚨 Gas level danger at bin: " +
        	            device.getBin().getId());
        	}


        sensorRepository.save(data);
    }
    
    public List<IoTSensorData> getRecentReadings() {
        return sensorRepository.findTop10ByOrderByTimestampDesc();
    }
    
    public List<IoTSensorData> getSensorDataByBin(Long binId) {
        return sensorRepository.findByDevice_Bin_IdOrderByTimestampDesc(binId);
    }
    
    public Page<IoTSensorData> getSensorDataByBinAndDateRange(
            Long binId,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable) {

        return sensorRepository
                .findByDevice_Bin_IdAndTimestampBetween(
                        binId, start, end, pageable);
    }


}
