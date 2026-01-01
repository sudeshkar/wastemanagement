package com.sudeshkar.SmartWasteManagement.sevice;

public interface IoTSimulatorService {
    void sendRandomReading();

    void generateAndSaveReading();

    void startSimulator();
 
    void stopSimulator();
 
    boolean isRunning();
    

}
