package com.sudeshkar.SmartWasteManagement.controller;

import org.springframework.http.HttpStatus;
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
	    simulatorService.generateAndSaveReading();
	    return ResponseEntity.ok("✅ One simulated sensor reading generated");
	}


    @PostMapping("/start")
    public ResponseEntity<String> start() {
    	try {
    		simulatorService.startSimulator();
            return ResponseEntity.ok("▶ IoT Simulator started");
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);

		}
        
    }

    
    @PostMapping("/stop")
    public ResponseEntity<String> stop() {
    	try {
    		 simulatorService.stopSimulator();
    	        return ResponseEntity.ok("⏹ IoT Simulator stopped");
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
       
    }
    
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok(
                simulatorService.isRunning() ? "RUNNING" : "STOPPED"
        );
    }
}
