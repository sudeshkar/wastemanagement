package com.sudeshkar.SmartWasteManagement.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity 
@Data
public class Bin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double location_lat;
	private Double location_lng;
	private Double capacity;
	
	@Column(name = "current_fill_level")
    private Double currentFillLevel;
	private String status;
	
	
	@OneToMany(mappedBy = "bin")
    private List<WasteCollectionLog> collectionLogs = new ArrayList<>();
	
	@OneToMany(mappedBy = "bin")
    private List<ServiceRequest> serviceRequests = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zone_id")
	private Zone zone;
	
	@OneToOne(mappedBy = "bin", cascade = CascadeType.ALL, orphanRemoval = true)
	private Device device;

	
}
