package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.CreateWasteCollectionReqDTO;
import com.sudeshkar.SmartWasteManagement.dto.ResponseWasteCollectionDTO;
import com.sudeshkar.SmartWasteManagement.sevice.WasteCollectionLogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class WasteCollectionLogController {
    private final WasteCollectionLogService logService;
    
    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody CreateWasteCollectionReqDTO dto) {
    	try {
    		return ResponseEntity.ok(
                  "Created Successfully"
            );
		} catch (Exception e) {
			return new ResponseEntity<String>("Error Unable to create "+e.getMessage(),HttpStatus.CONFLICT);
		}
        
    }

    @GetMapping
    public ResponseEntity<List<ResponseWasteCollectionDTO>> getAll() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWasteCollectionDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(logService.getLogById(id));
    }
}
