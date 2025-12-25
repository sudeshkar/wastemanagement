package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.Enum.RequestStatus;
import com.sudeshkar.SmartWasteManagement.dto.CreateServiceRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ServiceRequestDto;

public interface ServiceRequestService {
	ServiceRequestDto createRequest(CreateServiceRequestDto dto);

    List<ServiceRequestDto> getAllRequests();

    ServiceRequestDto getRequestById(Long id);

    ServiceRequestDto updateStatus(Long id, RequestStatus status);

    void deleteRequest(Long id);
}
