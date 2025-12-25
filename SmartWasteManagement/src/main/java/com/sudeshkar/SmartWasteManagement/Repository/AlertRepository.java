package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudeshkar.SmartWasteManagement.model.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	List<Alert> findByAcknowledgedFalse();
}
