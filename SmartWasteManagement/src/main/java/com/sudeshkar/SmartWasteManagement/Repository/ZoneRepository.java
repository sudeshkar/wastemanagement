package com.sudeshkar.SmartWasteManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

	boolean existsByZoneName(String name);

	Optional<User> findByZoneName(String string);

}
