package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sudeshkar.SmartWasteManagement.dto.AnalyticsDTO;
import com.sudeshkar.SmartWasteManagement.model.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	List<Alert> findByAcknowledgedFalse();
	
	@Query("SELECT new com.sudeshkar.SmartWasteManagement.dto.AnalyticsDTO(STR(a.type), COUNT(a)) " +
		       "FROM Alert a GROUP BY a.type")
		List<AnalyticsDTO> getAlertCountByType();
	    
	    long countByAcknowledgedFalse();
}
