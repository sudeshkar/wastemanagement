package com.sudeshkar.SmartWasteManagement.sevice;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;
import com.sudeshkar.SmartWasteManagement.Repository.DeviceRepository;
import com.sudeshkar.SmartWasteManagement.Repository.IoTSensorDataRepository;
import com.sudeshkar.SmartWasteManagement.model.Device;
import com.sudeshkar.SmartWasteManagement.model.IoTSensorData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IoTSimulatorServiceImpl implements IoTSimulatorService {

    private final DeviceRepository deviceRepository;
    private final IoTSensorDataRepository sensorDataRepository;
    private final AlertService alertService;

    private volatile boolean running = false;

    @Override
    @Scheduled(fixedRate = 5000)
    public void sendRandomReading() {
        if (!running) return;
        generateAndSaveReading();
    }

    @Transactional
    public void generateAndSaveReading() {

        Device device = deviceRepository.findFirstByActiveTrue()
                .orElseThrow(() -> new RuntimeException("No active IoT device found"));

        if (device.getBin() == null) {
            System.out.println("❌ Active device has no bin assigned: " + device.getId());
            return;
        }

        double fillLevel = Math.random() * 100;
        double gasLevel = Math.random() * 100;
        double temperature = 20 + Math.random() * 60;

        IoTSensorData data = new IoTSensorData();
        data.setDevice(device);
        data.setFillLevel(fillLevel);
        data.setGasLevel(gasLevel);
        data.setTemperature(temperature);

        sensorDataRepository.save(data);

        if (fillLevel > 80) {
            alertService.createAlert(
                    device.getBin().getId(),
                    AlertType.OVERFLOW,
                    "Simulated bin overflow detected"
            );
        }

        if (temperature > 60) {
            alertService.createAlert(
                    device.getBin().getId(),
                    AlertType.FIRE,
                    "Simulated high temperature detected"
            );
        }
    }

    @Override
    public void startSimulator() {
        running = true;
    }

    @Override
    public void stopSimulator() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}

