import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Statistics {
    private static Statistics instance;
    private ConcurrentHashMap<Integer, Long> vehicleWaitTimes;
    private ConcurrentHashMap<Integer, Integer> vehicleTripCounts;
    private AtomicLong totalFerryTrips;
    private List<Long> ferryTripDurations;
    private long simulationStartTime;

    private Statistics() {
        vehicleWaitTimes = new ConcurrentHashMap<>();
        vehicleTripCounts = new ConcurrentHashMap<>();
        totalFerryTrips = new AtomicLong(0);
        ferryTripDurations = new ArrayList<>();
        simulationStartTime = System.currentTimeMillis();
    }

    public static Statistics getInstance() {
        if (instance == null) {
            instance = new Statistics();
        }
        return instance;
    }

    public void recordVehicleWaitTime(int vehicleId, long waitTime) {
        vehicleWaitTimes.put(vehicleId, waitTime);
    }

    public void recordVehicleTrip(int vehicleId) {
        vehicleTripCounts.merge(vehicleId, 1, Integer::sum);
    }

    public void recordFerryTrip(long duration) {
        totalFerryTrips.incrementAndGet();
        ferryTripDurations.add(duration);
    }

    // Getter methods for Report.java
    public long getSimulationStartTime() {
        return simulationStartTime;
    }

    public double getAverageFerryTripDuration() {
        return ferryTripDurations.stream().mapToLong(Long::longValue).average().orElse(0) / 1000.0;
    }

    public double getAverageVehicleWaitTime() {
        return vehicleWaitTimes.values().stream().mapToLong(Long::longValue).average().orElse(0) / 1000.0;
    }

    public long getTotalFerryTrips() {
        return totalFerryTrips.get();
    }

    public double getAverageTripsPerVehicle() {
        return vehicleTripCounts.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public int getTotalVehicles() {
        return 30; // 12 cars + 10 minibuses + 8 trucks
    }

    public int getTotalVehicleTrips() {
        return vehicleTripCounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void generateReport() {
        System.out.println("\n=== Simulation Statistics Report ===");
        System.out.println("Total Simulation Time: " + 
                         (System.currentTimeMillis() - simulationStartTime) / 1000.0 + " seconds");
        
        System.out.println("\nFerry Statistics:");
        System.out.println("Total Trips: " + totalFerryTrips.get());
        System.out.println("Average Trip Duration: " + getAverageFerryTripDuration() + " seconds");

        System.out.println("\nVehicle Statistics:");
        System.out.println("Average Wait Time: " + getAverageVehicleWaitTime() + " seconds");
        
        System.out.println("\nTrip Statistics:");
        System.out.println("Average Trips per Vehicle: " + getAverageTripsPerVehicle());
    }
} 