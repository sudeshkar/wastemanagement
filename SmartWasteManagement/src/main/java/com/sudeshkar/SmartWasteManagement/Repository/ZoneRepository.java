package com.sudeshkar.SmartWasteManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

}
