package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

public interface  WasteCollectionLogService {
	WasteCollectionLog createLog(Long binId, Long vehicleId, double weight);
    List<WasteCollectionLog> getAllLogs();
    WasteCollectionLog getLogById(Long id);
}
