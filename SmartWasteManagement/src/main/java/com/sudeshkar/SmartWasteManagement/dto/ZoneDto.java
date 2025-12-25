package com.sudeshkar.SmartWasteManagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.CollectionRoute;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZoneDto {
    private Long zoneId;
    private String zoneName;
    private String description;
    private List<Bin> bins = new ArrayList<>();
    private List<CollectionRoute> routes = new ArrayList<>();

}
