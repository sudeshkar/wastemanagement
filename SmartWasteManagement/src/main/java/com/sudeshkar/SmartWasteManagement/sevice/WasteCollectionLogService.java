package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.dto.CreateWasteCollectionReqDTO;
import com.sudeshkar.SmartWasteManagement.dto.ResponseWasteCollectionDTO;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

public interface  WasteCollectionLogService {
	void createLog(CreateWasteCollectionReqDTO dto);
    List<ResponseWasteCollectionDTO> getAllLogs();
    ResponseWasteCollectionDTO getLogById(Long id);
}
