package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.AlertMapper;
import com.sudeshkar.SmartWasteManagement.Enum.AlertType;
import com.sudeshkar.SmartWasteManagement.Repository.AlertRepository;
import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.dto.AlertDto;
import com.sudeshkar.SmartWasteManagement.model.Alert;
import com.sudeshkar.SmartWasteManagement.model.Bin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AlertServiceImpl implements AlertService{
	private final AlertRepository alertRepository;
    private final BinRepository binRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<AlertDto> getAllAlerts() {
        return alertRepository.findAll()
                .stream()
                .map(AlertMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertDto> getActiveAlerts() {
        return alertRepository.findByAcknowledgedFalse()
                .stream()
                .map(AlertMapper::toDto)
                .toList();
    }

    @Override
    public AlertDto acknowledgeAlert(Long alertId) {

        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        alert.setAcknowledged(true);
        alert.setAcknowledgedAt(LocalDateTime.now());

        return AlertMapper.toDto(alert);
    }

    // Used by IoT Simulator or Sensor Service
    @Override
    public void createAlert(Long binId, AlertType type, String message) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        Alert alert = Alert.builder()
                .bin(bin)
                .type(type)
                .message(message)
                .build();

        alertRepository.save(alert);
    }

}
