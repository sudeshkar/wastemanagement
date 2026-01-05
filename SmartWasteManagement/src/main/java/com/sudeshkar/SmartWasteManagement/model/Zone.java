package com.sudeshkar.SmartWasteManagement.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zone {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;

    private String zoneName;
    private String description;

    // One zone contains many bins
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<Bin> bins = new ArrayList<>();

    // One zone may have many routes
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<CollectionRoute> routes = new ArrayList<>();
}
