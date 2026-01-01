package com.sudeshkar.SmartWasteManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudeshkar.SmartWasteManagement.dto.AssignDeviceToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.AssignZoneToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinResponseDTO;
import com.sudeshkar.SmartWasteManagement.dto.MarkBinEmptyRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.UpdateBinRequestDTO;
import com.sudeshkar.SmartWasteManagement.sevice.BinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bins")
@RequiredArgsConstructor
public class BinController {

   
    private final BinService binService;

    @GetMapping
    public ResponseEntity<List<BinResponseDTO>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBinById(@PathVariable Long id) {
    	try {
            return ResponseEntity.ok(binService.getBinById(id));

		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
    }

    @PostMapping
    public ResponseEntity<String> createBin(@RequestBody BinRequestDTO binDTO) {
    	try {
    		 return new ResponseEntity<String>(binService.createBin(binDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
       
    }
    
    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id,@RequestBody UpdateBinRequestDTO updateBinDTO){
    	try {
    		return ResponseEntity.ok(binService.updateBin(id, updateBinDTO));
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
    	
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBin(@PathVariable Long id) {
    	try {
    		binService.deleteBin(id);
            return ResponseEntity.ok("Deleted successfully");
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
        
    }
    
    @PostMapping("/assign-device")
    public ResponseEntity<?> assignDevice(@RequestBody AssignDeviceToBinDTO dto) {
         
        BinResponseDTO binDTO = binService.assignDevice(dto);

         try {
        	 if (binDTO != null) {
                 return ResponseEntity.ok(binDTO);
             } else {
                 return ResponseEntity.notFound().build();
             }
		} catch (Exception e) {
			 return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
        
    }
    
    @PostMapping("/assign-zone")
    public ResponseEntity<?> assignZone(@RequestBody AssignZoneToBinDTO dto){
    	try {
			return new ResponseEntity<BinResponseDTO>(binService.assignZoneToBin(dto),HttpStatus.OK);
		} catch (Exception e) {
			 return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
    }
    
    @PostMapping("/mark-empty")
    public ResponseEntity<?> markEmpty(@RequestBody MarkBinEmptyRequestDTO dto) {

         try {
        	 BinResponseDTO binDTO = binService.markEmpty(dto.getBinId(), dto.getVehicleId(), dto.getWasteWeight(), dto.getNotes());

             
             if (binDTO != null) {
                 return ResponseEntity.ok(binDTO);
             } else {
                 return ResponseEntity.notFound().build();
             }
		} catch (Exception e) {
			return new ResponseEntity<String>("Error "+e.getMessage(),HttpStatus.CONFLICT);
		}
        
    }
    
    @GetMapping("/{binId}/exists")
    public ResponseEntity<Void> existsById(@PathVariable Long binId) {
         
        if (binService.existsById(binId)) {
            return ResponseEntity.ok().build();  
        } else {
            return ResponseEntity.notFound().build();  
        }
    }
}

