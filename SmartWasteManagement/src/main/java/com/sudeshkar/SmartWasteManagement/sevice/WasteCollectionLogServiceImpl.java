package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.Repository.WasteCollectionLogRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateWasteCollectionReqDTO;
import com.sudeshkar.SmartWasteManagement.dto.ResponseWasteCollectionDTO;
import com.sudeshkar.SmartWasteManagement.mapper.WasteCollectionMapper;
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
    public void createLog(CreateWasteCollectionReqDTO dto) {

        Bin bin = binRepo.findById(dto.getBinId())
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        WasteCollectionLog log = new WasteCollectionLog();
        log.setBin(bin);
        log.setVehicle(vehicle);
        log.setWasteWeight(dto.getWasteWeight());
        log.setNotes(dto.getNotes());
        log.setCollectedAt(LocalDateTime.now());

        logRepo.save(log);
        // reset bin
        bin.setCurrentFillLevel(0.0);
        bin.setStatus("EMPTY");
        binRepo.save(bin);
         
    }

    @Override
    public List<ResponseWasteCollectionDTO> getAllLogs() {
        List<WasteCollectionLog> logs = logRepo.findAll();
        List<ResponseWasteCollectionDTO> respondDtoLogs = new ArrayList<ResponseWasteCollectionDTO>();
        
        for (WasteCollectionLog wasteCollectionLog : logs) {
			ResponseWasteCollectionDTO respondDtoLog = WasteCollectionMapper.toReponseDTO(wasteCollectionLog);
			respondDtoLogs.add(respondDtoLog);
		}
        return respondDtoLogs;
    }

    @Override
    public ResponseWasteCollectionDTO getLogById(Long id) {
        WasteCollectionLog log = logRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found With ID "+id));
        
        return WasteCollectionMapper.toReponseDTO(log);
    }

}
