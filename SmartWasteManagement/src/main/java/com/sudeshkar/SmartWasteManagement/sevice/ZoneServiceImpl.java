package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.dto.ZoneRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ZoneResponseDto;
import com.sudeshkar.SmartWasteManagement.mapper.ZoneMapper;
import com.sudeshkar.SmartWasteManagement.model.Zone;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService{
	
	private final ZoneRepository zoneRepository;

	@Override
	public List<ZoneResponseDto> getAllZones() {
		 List<Zone> zones = zoneRepository.findAll();
		 List<ZoneResponseDto> zoneResponseDtos = new ArrayList<ZoneResponseDto>();
		 for (Zone zone : zones) {
			 ZoneResponseDto res = ZoneMapper.toDtoResponse(zone);
			 zoneResponseDtos.add(res);
		}
		 
		 return zoneResponseDtos;
	}

	@Override
	public ZoneResponseDto getZoneById(Long id) {
		 Zone zone = zoneRepository.findById(id)
				 .orElseThrow(()-> new RuntimeException("Zone Not found with the ID "+id ));
		 
		 return ZoneMapper.toDtoResponse(zone);
	}

	@Override
	public void createZone(ZoneRequestDto dto) {
		Zone zone = ZoneMapper.toEntity(dto);
		zoneRepository.save(zone);
	}

	@Override
	public void updateZone(Long id, ZoneRequestDto dto) {
		Zone zone = zoneRepository.findById(id)
				 .orElseThrow(()-> new RuntimeException("Zone Not found with the ID "+id ));
		
		
		
		if (dto.getZoneName()!=null) {
			zone.setZoneName(dto.getZoneName());
		}
		
		if (dto.getDescription()!=null) {
			zone.setDescription(dto.getDescription());
		}
		
		zoneRepository.save(zone);
	}

	@Override
	public void deleteZone(Long id) {
		 zoneRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		 return zoneRepository.existsById(id);
	}
	
	

}
