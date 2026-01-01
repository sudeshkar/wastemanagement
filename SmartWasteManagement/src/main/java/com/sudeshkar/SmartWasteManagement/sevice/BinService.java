package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.AssignDeviceToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.AssignZoneToBinDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinRequestDTO;
import com.sudeshkar.SmartWasteManagement.dto.BinResponseDTO;
import com.sudeshkar.SmartWasteManagement.dto.UpdateBinRequestDTO;

public interface BinService {
	List<BinResponseDTO> getAllBins();

    BinResponseDTO getBinById(Long id);

    String createBin(BinRequestDTO binDTO);

    BinResponseDTO updateBin(Long id, UpdateBinRequestDTO binDTO);

    void deleteBin(Long id);

    BinResponseDTO assignDevice(AssignDeviceToBinDTO dto);

    BinResponseDTO markEmpty(Long binId, Long vehicleId, double wasteWeight, String notes);

    boolean existsById(Long id);
    
    BinResponseDTO assignZoneToBin(AssignZoneToBinDTO dto);
}
