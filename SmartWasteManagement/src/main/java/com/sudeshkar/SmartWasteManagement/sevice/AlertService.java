package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;
import com.sudeshkar.SmartWasteManagement.dto.AlertDto;


public interface AlertService {
	List<AlertDto> getAllAlerts();

    List<AlertDto> getActiveAlerts();

    AlertDto acknowledgeAlert(Long alertId);

    void createAlert(Long binId, AlertType type, String message);
}
