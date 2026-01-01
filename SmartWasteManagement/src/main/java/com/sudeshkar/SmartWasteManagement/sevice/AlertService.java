package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;
import com.sudeshkar.SmartWasteManagement.dto.AlertDto;
import com.sudeshkar.SmartWasteManagement.dto.CreateAlertRequestDTO;


public interface AlertService {
	List<AlertDto> getAllAlerts();

    List<AlertDto> getActiveAlerts();

    AlertDto acknowledgeAlert(Long alertId);

    void createAlert(Long binId, AlertType type, String message);
    
    AlertDto getById(Long id);
    
    void deleteById(Long id);
    
    void updateAlert(Long alertId,CreateAlertRequestDTO dto);
}
