package com.sudeshkar.SmartWasteManagement;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;
import com.sudeshkar.SmartWasteManagement.Enum.Role;
import com.sudeshkar.SmartWasteManagement.Enum.VehicleStatus;
import com.sudeshkar.SmartWasteManagement.Repository.AlertRepository;
import com.sudeshkar.SmartWasteManagement.Repository.BinRepository;
import com.sudeshkar.SmartWasteManagement.Repository.CollectionRouteRepository;
import com.sudeshkar.SmartWasteManagement.Repository.DeviceRepository;
import com.sudeshkar.SmartWasteManagement.Repository.DriverRepository;
import com.sudeshkar.SmartWasteManagement.Repository.IoTSensorDataRepository;
import com.sudeshkar.SmartWasteManagement.Repository.RouteAssignmentRepository;
import com.sudeshkar.SmartWasteManagement.Repository.UserRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleLocationRepository;
import com.sudeshkar.SmartWasteManagement.Repository.VehicleRepository;
import com.sudeshkar.SmartWasteManagement.Repository.WasteCollectionLogRepository;
import com.sudeshkar.SmartWasteManagement.Repository.ZoneRepository;
import com.sudeshkar.SmartWasteManagement.model.Alert;
import com.sudeshkar.SmartWasteManagement.model.Bin;
import com.sudeshkar.SmartWasteManagement.model.Device;
import com.sudeshkar.SmartWasteManagement.model.Driver;
import com.sudeshkar.SmartWasteManagement.model.IoTSensorData;
import com.sudeshkar.SmartWasteManagement.model.User;
import com.sudeshkar.SmartWasteManagement.model.Vehicle;
import com.sudeshkar.SmartWasteManagement.model.VehicleLocation;
import com.sudeshkar.SmartWasteManagement.model.WasteCollectionLog;
import com.sudeshkar.SmartWasteManagement.model.Zone;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final ZoneRepository zoneRepository;
    private final BinRepository binRepository;
    private final DeviceRepository deviceRepository;
    private final VehicleRepository vehicleRepository;
    private final WasteCollectionLogRepository logRepository;
    private final AlertRepository alertRepository;
    private final CollectionRouteRepository routeRepository;
    private final RouteAssignmentRepository assignmentRepository;
    private final IoTSensorDataRepository sensorRepository;
    private final VehicleLocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) return;

        System.out.println(">>> SEEDING DATABASE...");

        // 1. Create Zones (Foundation)
        Zone kandy = createZone("Kandy Central", "Business District");
        Zone peradeniya = createZone("Peradeniya", "Residential Zone");

        // 2. Create Users & Drivers
        User d1User = createUser("driver1@swm.lk", Role.DRIVER);
        User d2User = createUser("driver2@swm.lk", Role.DRIVER);
        createUser("admin@swm.lk", Role.ADMIN);

        // 3. Create Vehicles
        Vehicle v1 = createVehicle("SWM-001", VehicleStatus.COLLECTING_WASTE);
        Vehicle v2 = createVehicle("SWM-002", VehicleStatus.AVAILABLE);

        // 4. Create Drivers (Linked)
        createDriver(d1User, "LIC-101", "0712345678", v1);
        createDriver(d2User, "LIC-102", "0777654321", v2);

        // 5. Create Bins, Devices, and Sensors (Linked Chain)
        // Bin 1
        Bin bin1 = createBin(7.2906, 80.6337, 100.0, 75.0, "AVAILABLE", kandy);
        Device dev1 = createDevice("MAC-AA-11", bin1);
        seedSensorHistory(dev1);

        // Bin 2
        Bin bin2 = createBin(7.2945, 80.6380, 100.0, 92.0, "FULL", kandy);
        Device dev2 = createDevice("MAC-BB-22", bin2);
        seedSensorHistory(dev2);

        // 6. Create Live Vehicle Locations
        createLocation(v1, 7.2910, 80.6340);
        createLocation(v2, 7.2850, 80.6200);

        // 7. Create Collection Logs (Historical)
        createLog(bin1, v1, 45.2, "Morning standard pickup");
        createLog(bin2, v1, 88.5, "Emergency overflow pickup");

        // 8. Create Alerts
        createAlert("Bin #2 Overflowing!", AlertType.OVERFLOW, bin2);
        createAlert("Bin #1 Battery Low", AlertType.LOW_BATTERY, bin1);

        System.out.println(">>> SEEDING COMPLETE!");
    }

    // --- LOGICALLY ORDERED HELPERS ---

    private User createUser(String email, Role role) {
        User u = new User();
        u.setEmail(email);
        u.setPasswordHash(passwordEncoder.encode("Password123"));
        u.setRole(role);
        return userRepository.save(u);
    }

    private Zone createZone(String name, String desc) {
        Zone z = new Zone();
        z.setZoneName(name);
        z.setDescription(desc);
        return zoneRepository.save(z);
    }

    private Vehicle createVehicle(String no, VehicleStatus status) {
        Vehicle v = new Vehicle();
        v.setVehicleNumber(no);
        v.setStatus(status);
        return vehicleRepository.save(v);
    }

    private void createDriver(User u, String lic, String ph, Vehicle v) {
        Driver d = new Driver();
        d.setUser(u);
        d.setLicenseNumber(lic);
        d.setPhoneNumber(ph);
        d.setAssignedVehicle(v);
        driverRepository.save(d);
    }

    private Bin createBin(double lat, double lng, double cap, double fill, String status, Zone z) {
        Bin b = new Bin();
        b.setLocation_lat(lat);
        b.setLocation_lng(lng);
        b.setCapacity(cap);
        b.setCurrentFillLevel(fill);
        b.setStatus(status);
        b.setZone(z);
        return binRepository.save(b);
    }

    private Device createDevice(String mac, Bin b) {
        Device d = new Device();
        d.setDeviceId(mac);
        d.setActive(true);
        d.setFirmwareVersion("v2.1");
        d.setBin(b);
        return deviceRepository.save(d); // Crucial: return saved object with ID
    }

    private void seedSensorHistory(Device d) {
        for (int i = 5; i >= 0; i--) {
            IoTSensorData s = new IoTSensorData();
            s.setDevice(d);
            s.setFillLevel(20.0 + (random.nextDouble() * 70.0));
            s.setTemperature(25.0 + random.nextDouble() * 5);
            s.setGasLevel(random.nextDouble() * 2);
            // Since timestamp has @PrePersist, it will auto-set to 'now'.
            // To seed real history, you'd need to remove @PrePersist and set manually.
            sensorRepository.save(s);
        }
    }

    private void createLog(Bin b, Vehicle v, double w, String note) {
        WasteCollectionLog log = new WasteCollectionLog();
        log.setBin(b);
        log.setVehicle(v);
        log.setWasteWeight(w);
        log.setNotes(note);
        log.setCollectedAt(LocalDateTime.now().minusHours(random.nextInt(24)));
        logRepository.save(log);
    }

    private void createLocation(Vehicle v, double lat, double lng) {
        VehicleLocation loc = new VehicleLocation();
        loc.setVehicle(v);
        loc.setLatitude(lat);
        loc.setLongitude(lng);
        loc.setTimestamp(LocalDateTime.now());
        locationRepository.save(loc);
    }

    private void createAlert(String msg, AlertType type, Bin b) {
        Alert a = new Alert();
        a.setMessage(msg);
        a.setBin(b);
        a.setType(type);
        alertRepository.save(a);
    }
}