package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;
import com.sudeshkar.SmartWasteManagement.sevice.WasteCollectionLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class WasteCollectionLogController {
    private final WasteCollectionLogService logService;
    
    @PostMapping
    public ResponseEntity<WasteCollectionLog> createLog(
            @RequestParam Long binId,
            @RequestParam Long vehicleId,
            @RequestParam double weight) {

        return ResponseEntity.ok(
                logService.createLog(binId, vehicleId, weight)
        );
    }

    @GetMapping
    public ResponseEntity<List<WasteCollectionLog>> getAll() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCollectionLog> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(logService.getLogById(id));
    }
}
