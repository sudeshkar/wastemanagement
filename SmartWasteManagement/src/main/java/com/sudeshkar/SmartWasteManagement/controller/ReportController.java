package com.sudeshkar.SmartWasteManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.sevice.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
	
	 private final ReportService reportService;
	 
	 	@GetMapping("/daily")
	    public ResponseEntity<?> daily() {
	        return ResponseEntity.ok(reportService.dailyReport());
	    }

	    @GetMapping("/zone/{zoneId}")
	    public ResponseEntity<?> zone(@PathVariable Long zoneId) {
	        return ResponseEntity.ok(reportService.zoneReport(zoneId));
	    }

	    @GetMapping("/bin-usage/{binId}")
	    public ResponseEntity<?> bin(@PathVariable Long binId) {
	        return ResponseEntity.ok(reportService.binUsage(binId));
	    }

	    @GetMapping("/alerts")
	    public ResponseEntity<?> alerts() {
	        return ResponseEntity.ok(reportService.alertStats());
	    }

}
