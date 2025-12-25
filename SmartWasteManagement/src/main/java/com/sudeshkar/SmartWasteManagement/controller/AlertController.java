package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.AlertDto;
import com.sudeshkar.SmartWasteManagement.sevice.AlertService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
	private final AlertService alertService;
	
	@GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

     
    @PostMapping("/ack/{id}")
    public ResponseEntity<AlertDto> acknowledgeAlert(
            @PathVariable Long id) {

        return ResponseEntity.ok(alertService.acknowledgeAlert(id));
    }
}
