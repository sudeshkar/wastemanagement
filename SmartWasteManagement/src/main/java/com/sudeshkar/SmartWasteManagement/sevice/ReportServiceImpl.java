package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.Repository.AlertRepository;
import com.sudeshkar.SmartWasteManagement.Repository.WasteCollectionLogRepository;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	
	private final WasteCollectionLogRepository logRepo;
    private final AlertRepository alertRepo;

    @Override
    public Map<String, Object> dailyReport() {
        Map<String, Object> map = new HashMap<>();

        long totalCollections = logRepo.count();
        long totalAlerts = alertRepo.count();

        map.put("date", java.time.LocalDate.now());
        map.put("totalCollections", totalCollections);
        map.put("totalAlerts", totalAlerts);

        return map;
    }

    @Override
    public Map<String, Object> zoneReport(Long zoneId) {
        Map<String, Object> map = new HashMap<>();

        long collectionsInZone = logRepo.findAll()
                .stream()
                .filter(log -> log.getBin().getZone().getZoneId().equals(zoneId))
                .count();

        map.put("zoneId", zoneId);
        map.put("collections", collectionsInZone);

        return map;
    }

    @Override
    public Map<String,Object> binUsage(Long binId) {
        List<WasteCollectionLog> logs = logRepo.findAll()
                .stream()
                .filter(l -> l.getBin().getId().equals(binId))
                .toList();

        Map<String, Object> map = new HashMap<>();
        map.put("binId", binId);
        map.put("totalCollections", logs.size());

        if (!logs.isEmpty()) {
            map.put("lastCollectedAt",
                    logs.get(logs.size() - 1).getCollectedAt());
        }

        return map;
    }

    @Override
    public Map<String, Long> alertStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalAlerts", alertRepo.count());

        stats.put("overflowAlerts",
                alertRepo.findAll().stream()
                        .filter(a -> a.getType().name().equals("OVERFLOW"))
                        .count());

        stats.put("fireAlerts",
                alertRepo.findAll().stream()
                        .filter(a -> a.getType().name().equals("FIRE"))
                        .count());

        return stats;
    }
}
