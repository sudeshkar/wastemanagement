package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.Zone;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class SmartRouteEngineImpl implements SmartRouteEngine {
	private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;
	@Override
	public CollectionRoute generate() {
		 // 1️⃣ Get bins that need urgent collection
        List<Bin> criticalBins = binRepository.findByCurrentFillLevelGreaterThan(80.0);

        if (criticalBins.isEmpty()) {
            throw new IllegalStateException("No bins require urgent collection");
        }

        // 2️⃣ Determine zone (simple rule)
        Zone zone = criticalBins.get(0).getZone();

        // 3️⃣ Create route
        CollectionRoute route = new CollectionRoute();
        route.setRouteName("Smart Route - " + zone.getZoneName());
        route.setDescription("Auto-generated based on bin fill levels");
        route.setZone(zone);

        return route;
	}

}
