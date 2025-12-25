package com.sudeshkar.SmartWasteManagement.Enum;


public enum VehicleStatus {
	AVAILABLE,          // Ready for assignment
    ASSIGNED,           // Assigned to a route
    EN_ROUTE,           // Traveling to collection points
    COLLECTING_WASTE,   // Actively collecting waste
    FULL,               // Waste capacity reached
    HEADING_TO_DUMP,    // Going to disposal site
    UNLOADING,          // Unloading waste
    RETURNING,          // Returning to depot
    IDLE,               // Stationary but active
    MAINTENANCE,        // Scheduled maintenance
    BREAKDOWN,          // Unexpected failure
    OUT_OF_SERVICE      // Not operational
}
