package com.sudeshkar.SmartWasteManagement.sevice;

public interface IoTSimulatorService {
	void sendRandomReading();
	
	void startSimulator();

    void stopSimulator();

    boolean isRunning();
}
