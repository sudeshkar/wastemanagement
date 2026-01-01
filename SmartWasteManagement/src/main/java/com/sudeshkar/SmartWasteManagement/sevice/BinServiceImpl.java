package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.DeviceRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.Repository.WasteCollectionLogRepository;
import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.dto.AssignDeviceToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.AssignZoneToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinResponseDTO;
import com.sudeshkar.SmartWasteManagement.dto.UpdateBinRequestDTO;
import com.sudeshkar.SmartWasteManagement.mapper.BinMapper;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;
import com.sudeshkar.SmartWasteManagement.model.Zone;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BinServiceImpl implements BinService{

	private final BinRepository binRepository;
	private final ZoneRepository zoneRepository;
	private final DeviceRepository deviceRepository;
	private final VehicleRepository vehicleRepository;
	private final WasteCollectionLogRepository wasteCollectionLogRepository;
	@Override
	public List<BinResponseDTO> getAllBins() {
		List<Bin> bins = binRepository.findAll();
		List<BinResponseDTO> dtos = new ArrayList<>();
		 for (Bin bin : bins) {
			 BinResponseDTO dto = BinMapper.toDTO(bin);
			dtos.add(dto);
		}
		return dtos; 
	}

	@Override
	public BinResponseDTO getBinById(Long id) {
		 Bin bin = binRepository.findById(id).orElseThrow(()-> new RuntimeException("Bin Not Found With ID "+id));
		return  BinMapper.toDTO(bin);
		   
	}

	@Override
	public String createBin(BinRequestDTO binDTO) {
		 Bin bin = BinMapper.toEntity(binDTO);
		 bin.setCurrentFillLevel(0.0);
		 bin.setStatus("ACTIVE");

		 binRepository.save(bin);
		  return "Created Successfully";
	}

	@Override
	public BinResponseDTO updateBin(Long id, UpdateBinRequestDTO binDTO) {

	    Bin existingBin = binRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Bin Not Found With ID " + id));

	    if (binDTO.getLocationLat() != null) {
	        existingBin.setLocation_lat(binDTO.getLocationLat());  
	         
	    }

	    if (binDTO.getLocationLng() != null) {
	        existingBin.setLocation_lng(binDTO.getLocationLng());
	    }

	    if (binDTO.getCapacity() != null) {
	        existingBin.setCapacity(binDTO.getCapacity());
	    }
	    
	    if (binDTO.getCurrentFillLevel() != null) {
	        existingBin.setCurrentFillLevel(binDTO.getCurrentFillLevel());
	    }
	    
	    if (binDTO.getStatus() != null) {
	        existingBin.setStatus(binDTO.getStatus());
	    }

	    

	    if (binDTO.getZoneId() != null) {
	        Zone zone = zoneRepository.findById(binDTO.getZoneId())
	                .orElseThrow(() -> new RuntimeException("Zone Not Found With ID " + binDTO.getZoneId()));
	        existingBin.setZone(zone);
	    }

	    

	    Bin updatedBin = binRepository.save(existingBin);

	    return BinMapper.toDTO(updatedBin);
	}


	@Override
	public void deleteBin(Long id) {
		binRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Bin Not Found With ID " + id)); 
		binRepository.deleteById(id);
		
	}

	@Override
	public BinResponseDTO assignDevice(AssignDeviceToBinDTO dto) {
		Bin existingBin = binRepository.findById(dto.getBinId())
	            .orElseThrow(() -> new RuntimeException("Bin Not Found With ID " + dto.getBinId()));
		
		Device exDevice = deviceRepository.findById(dto.getDeviceId())
				.orElseThrow(() -> new RuntimeException("Device Not Found With ID " + dto.getDeviceId()));
		exDevice.setBin(existingBin);
		existingBin.setDevice(exDevice);
		deviceRepository.save(exDevice);
		BinResponseDTO resDto = new BinResponseDTO();
		resDto.setId(existingBin.getId());
		resDto.setLocationLat(existingBin.getLocation_lat());
		resDto.setLocationLng(existingBin.getLocation_lng());
		resDto.setCapacity(existingBin.getCapacity());
		resDto.setCurrentFillLevel(existingBin.getCurrentFillLevel());
		resDto.setStatus(existingBin.getStatus());
		
		resDto.setDeviceId(existingBin.getDevice().getDeviceId());
		
		return resDto;
		
		
		
	}

	@Override
	public BinResponseDTO markEmpty(Long binId, Long vehicleId, double wasteWeight, String notes) {
		Bin bin = binRepository.findById(binId)
	            .orElseThrow(() -> new RuntimeException("Bin not found with the ID "+binId));
		
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(()-> new RuntimeException("Vehicle Not Found With the ID "+vehicleId));

	    WasteCollectionLog log = new WasteCollectionLog();
	    log.setCollectedAt(LocalDateTime.now());
	    log.setWasteWeight(wasteWeight);
	    log.setBin(bin);  
	    log.setVehicle(vehicle); 
	    log.setNotes(notes);
	    
	    bin.getCollectionLogs().add(log);

	    wasteCollectionLogRepository.save(log);
	    
	    bin.setCurrentFillLevel(0.0);
	    bin.setStatus("EMPTY");
	    binRepository.save(bin);
	    
	    BinResponseDTO dto = new BinResponseDTO();
		dto.setId(bin.getId());
		dto.setLocationLat(bin.getLocation_lat());
		dto.setLocationLng(bin.getLocation_lng());
		dto.setCapacity(bin.getCapacity());
		dto.setCurrentFillLevel(bin.getCurrentFillLevel());
		dto.setStatus(bin.getStatus());
		dto.setDeviceId(bin.getDevice().getDeviceId());
		
		return dto;
	    
	    
	}

	@Override
	public boolean existsById(Long id) {
		 return binRepository.existsById(id);
	}

	@Override
	public BinResponseDTO assignZoneToBin(AssignZoneToBinDTO dto) {

	    if (dto.getBinId() == null || dto.getZoneId() == null) {
	        throw new RuntimeException("Bin ID or Zone ID must not be null");
	    }

	    Zone zone = zoneRepository.findById(dto.getZoneId())
	            .orElseThrow(() ->
	                    new RuntimeException("Zone Not Found With the ID " + dto.getZoneId()));

	    Bin bin = binRepository.findById(dto.getBinId())
	            .orElseThrow(() ->
	                    new RuntimeException("Bin Not Found with the ID " + dto.getBinId()));

	     
	    if (bin.getZone() != null && bin.getZone().getZoneId().equals(dto.getZoneId())) {
	        throw new RuntimeException("Bin is already assigned to this zone");
	    }

	     
	    bin.setZone(zone);
	    zone.getBins().add(bin);

	    binRepository.save(bin);

	    return BinMapper.toDTO(bin);
	}


}
