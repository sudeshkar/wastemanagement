package com.sudeshkar.SmartWasteManagement.model;

import java.util.ArrayList;
import java.util.List;

import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    private String vehicleNumber;
    
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @OneToOne(mappedBy = "assignedVehicle")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle")
    private List<WasteCollectionLog> logs = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle")
    private List<RouteAssignment> assignments = new ArrayList<>();
}
