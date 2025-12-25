package com.sudeshkar.SmartWasteManagement.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class RouteAssignment {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long assignmentId;

	    private LocalDate assignedDate;
	    private String status;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "route_id")
	    private CollectionRoute route;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "vehicle_id")
	    private Vehicle vehicle;
	    

}
