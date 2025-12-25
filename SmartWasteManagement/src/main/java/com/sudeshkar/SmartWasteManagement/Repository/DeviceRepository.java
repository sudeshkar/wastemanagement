package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
	 Optional<Device> findFirstByActiveTrue();
}
