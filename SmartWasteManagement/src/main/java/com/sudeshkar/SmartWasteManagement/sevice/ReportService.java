package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;
import java.util.Map;

import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

public interface ReportService {
	Map<String, Object> dailyReport();
    Map<String, Object> zoneReport(Long zoneId);
    Map<String,Object> binUsage(Long binId);
    Map<String, Long> alertStats();
	
}
