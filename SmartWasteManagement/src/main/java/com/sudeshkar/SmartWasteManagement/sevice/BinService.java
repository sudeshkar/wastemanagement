package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.DeviceRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.Repository.WasteCollectionLogRepository;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

import lombok.RequiredArgsConstructor;

@Service
public class BinService {
	@Autowired
	private  BinRepository binRepository;
	@Autowired
	private WasteCollectionLogRepository wasteCollectionLog;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<Bin> getAllBins(){
		return binRepository.findAll();
	}

	public Bin getBinById(Long id) {
		return binRepository.findById(id).orElseThrow(()->new RuntimeException("Unable to find Bin With ID "+id));
	}

	public void createbin(Bin bin) {
		binRepository.save(bin);
		
	}

	public boolean existsById(Long id) {
		return binRepository.existsById(id);
	}

	public void deleteBin(Long id) {
		binRepository.deleteById(id);
		
	}

	public Bin assignDevice(Long id, String deviceId) {
		Bin bin = binRepository.findById(id).orElseThrow(()->new RuntimeException("Bin not found"));
		
		Device device = deviceRepository.findById(Long.parseLong(deviceId)).orElse(new Device());
		
		if (device.getBin() !=null && !device.getBin().getId().equals(id)) {
			throw new RuntimeException("Device Already Assigned to another Bin");
		}	
		device.setDeviceId(deviceId);
		device.setActive(true);
		device.setBin(bin);
		deviceRepository.save(device);
		
		return bin;
		
	}

	public Bin markEmpty(Long binId, Long vehicleId, double wasteWeight, String notes) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        bin.setCurrentFillLevel(0.0);
        bin.setStatus("EMPTY");

         
        WasteCollectionLog log = new WasteCollectionLog();
        log.setCollectedAt(LocalDateTime.now());
        log.setWasteWeight(wasteWeight);
        log.setNotes(notes);

         
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        log.setVehicle(vehicle);

         
        log.setBin(bin);

         
        wasteCollectionLog.save(log);

        
        bin.getCollectionLogs().add(log);

        return binRepository.save(bin);
    }

}
