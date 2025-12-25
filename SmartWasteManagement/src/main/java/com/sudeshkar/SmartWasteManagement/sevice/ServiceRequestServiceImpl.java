package com.sudeshkar.SmartWasteManagement.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sudeshkar.SmartWasteManagement.ServiceRequestMapper;
import com.sudeshkar.SmartWasteManagement.Enum.RequestStatus;
import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.ServiceRequestRepository;
import com.sudeshkar.SmartWasteManagement.Repository.UserRepository;
import com.sudeshkar.SmartWasteManagement.dto.CreateServiceRequestDto;
import com.sudeshkar.SmartWasteManagement.dto.ServiceRequestDto;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.ServiceRequest;
import com.sudeshkar.SmartWasteManagement.model.User;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final UserRepository userRepository;
    private final BinRepository binRepository;

    // 🔹 CREATE complaint (Citizen or System)
    @Override
    public ServiceRequestDto createRequest(CreateServiceRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bin bin = binRepository.findById(dto.getBinId())
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        ServiceRequest request = new ServiceRequest();
        request.setUser(user);
        request.setBin(bin);
        request.setStatus(RequestStatus.PENDING);
        request.setCreatedAt(LocalDateTime.now());

        ServiceRequest saved = serviceRequestRepository.save(request);

        return ServiceRequestMapper.toDto(saved);
    }

    // 🔹 GET all requests
    @Override
    @Transactional(readOnly = true)
    public List<ServiceRequestDto> getAllRequests() {

        return serviceRequestRepository.findAll()
                .stream()
                .map(ServiceRequestMapper::toDto)
                .toList();
    }

    // 🔹 GET request by ID
    @Override
    @Transactional(readOnly = true)
    public ServiceRequestDto getRequestById(Long id) {

        ServiceRequest request = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service request not found"));

        return ServiceRequestMapper.toDto(request);
    }

    // 🔹 UPDATE status
    @Override
    public ServiceRequestDto updateStatus(Long id, RequestStatus status) {

        ServiceRequest request = serviceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service request not found"));

        request.setStatus(status);

        if (status == RequestStatus.RESOLVED) {
            request.setResolvedAt(LocalDateTime.now());
        }

        return ServiceRequestMapper.toDto(request);
    }

    // 🔹 DELETE request
    @Override
    public void deleteRequest(Long id) {

        if (!serviceRequestRepository.existsById(id)) {
            throw new RuntimeException("Service request not found");
        }

        serviceRequestRepository.deleteById(id);
    }
}

