package com.sudeshkar.SmartWasteManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.sevice.IoTSimulatorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/simulator")
@RequiredArgsConstructor
public class IoTSimulatorController {
	
	private final IoTSimulatorService simulatorService;
	
	
    @PostMapping("/random-reading")
    public ResponseEntity<String> generateOne() {
        simulatorService.sendRandomReading();
        return ResponseEntity.ok("✅ One simulated sensor reading generated");
    }

    @PostMapping("/start")
    public ResponseEntity<String> start() {
        simulatorService.startSimulator();
        return ResponseEntity.ok("▶ IoT Simulator started");
    }

    
    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
        simulatorService.stopSimulator();
        return ResponseEntity.ok("⏹ IoT Simulator stopped");
    }
    
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(
                simulatorService.isRunning() ? "RUNNING" : "STOPPED"
        );
    }
}
