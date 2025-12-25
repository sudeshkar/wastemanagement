package com.sudeshkar.SmartWasteManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;

@Repository
public interface WasteCollectionLogRepository extends JpaRepository<WasteCollectionLog, Long> {

}
