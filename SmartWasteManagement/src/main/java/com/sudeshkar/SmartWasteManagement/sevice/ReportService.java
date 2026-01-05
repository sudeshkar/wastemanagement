package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.Map;

public interface ReportService {
	Map<String, Object> dailyReport();
    Map<String, Object> zoneReport(Long zoneId);
    Map<String,Object> binUsage(Long binId);
    Map<String, Long> alertStats();
    Map<String, Object> getDashboardStats();
	
}
