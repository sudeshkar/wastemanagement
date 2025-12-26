package com.sudeshkar.SmartWasteManagement;

import org.springframework.stereotype.Component;

import com.sudeshkar.SmartWasteManagement.dto.RouteStepResponseDto;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;
import com.sudeshkar.SmartWasteManagement.model.RouteStep;

@Component
public class RouteStepMapper {
	
	public static RouteStepResponseDto toDto(RouteStep step) {
        if (step == null) {
            return null;
        }

        RouteStepResponseDto dto = new RouteStepResponseDto();
        dto.setId(step.getId());
        dto.setStepOrder(step.getStepOrder());
        dto.setNote(step.getNote());

        Bin bin = step.getBin();
        if (bin != null) {
            dto.setBinId(bin.getId());
        }

        return dto;
    }

    
    public static RouteStep toEntity(
            CollectionRoute route,
            Bin bin,
            int stepOrder,
            String note) {

        RouteStep step = new RouteStep();
        step.setRoute(route);
        step.setBin(bin);
        step.setStepOrder(stepOrder);
        step.setNote(note);

        return step;
    }
}
