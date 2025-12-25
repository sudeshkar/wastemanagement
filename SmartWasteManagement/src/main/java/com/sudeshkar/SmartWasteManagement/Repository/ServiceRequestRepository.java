package com.sudeshkar.SmartWasteManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long>{

}
