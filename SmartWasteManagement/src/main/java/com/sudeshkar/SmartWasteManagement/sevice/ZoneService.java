package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.model.Zone;

@Service
public class ZoneService {
	@Autowired
	private ZoneRepository zoneRepository;
	
	public List<Zone> getAllZones() {
	  List<Zone> zones = zoneRepository.findAll();
	  return zones;
	}

	public Zone getById(Long id) {
		
		return zoneRepository.findById(id).orElseThrow(()-> new RuntimeException("Zone Not Found "+id));
		
	}

	public void createZone(Zone zone) {
		 zoneRepository.save(zone);
		
	}

	public boolean existsById(Long id) {
		 return zoneRepository.existsById(id);
	}

	public void deleteUser(Long id) {
		 zoneRepository.deleteById(id);
		
	}
	
	

}
