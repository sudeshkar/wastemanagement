package com.sudeshkar.SmartWasteManagement.sevice;

import java.util.List;

import com.sudeshkar.SmartWasteManagement.model.RouteStep;

public interface RouteStepService {
	RouteStep createStep(Long routeId, Long binId, int order, String note);
    List<RouteStep> getStepsByRoute(Long routeId);
}
