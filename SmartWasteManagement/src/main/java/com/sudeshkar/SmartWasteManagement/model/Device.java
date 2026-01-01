
package com.sudeshkar.SmartWasteManagement.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;     // ESP32 MAC
    private String firmwareVersion;
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "bin_id", unique = true)
    private Bin bin;
    
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<IoTSensorData> sensorData;


}

