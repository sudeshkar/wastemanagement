package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.Repository.WasteCollectionLogRepository;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WasteCollectionLogServiceImpl implements WasteCollectionLogService{
	
	private final WasteCollectionLogRepository logRepo;
    private final BinRepository binRepo;
    private final VehicleRepository vehicleRepo;

	
    @Override
    public WasteCollectionLog createLog(Long binId, Long vehicleId, double weight) {

        Bin bin = binRepo.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        WasteCollectionLog log = new WasteCollectionLog();
        log.setBin(bin);
        log.setVehicle(vehicle);
        log.setWasteWeight(weight);
        log.setCollectedAt(LocalDateTime.now());

        // reset bin
        bin.setCurrentFillLevel(0.0);

        return logRepo.save(log);
    }

    @Override
    public List<WasteCollectionLog> getAllLogs() {
        return logRepo.findAll();
    }

    @Override
    public WasteCollectionLog getLogById(Long id) {
        return logRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));
    }

}
