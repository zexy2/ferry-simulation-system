import java.util.ArrayList;
import java.util.List;

public class Ferry {
    public static final int MAX_CAPACITY = 20; // Maximum capacity in car units
    private int currentCapacity;
    private int currentSide; // 0 or 1 for the two sides
    private List<Vehicle> vehiclesOnBoard;
    private int tripCount; // Yeni eklenen: Sefer sayısı

    public Ferry() {
        this.currentCapacity = 0;
        this.currentSide = (int) (Math.random() * 2);
        this.vehiclesOnBoard = new ArrayList<>();
        this.tripCount = 0;
    }

    public synchronized boolean canLoadVehicle(Vehicle vehicle) {
        return (currentCapacity + vehicle.getCapacity()) <= MAX_CAPACITY;
    }

    public synchronized void loadVehicle(Vehicle vehicle) {
        if (canLoadVehicle(vehicle)) {
            vehiclesOnBoard.add(vehicle);
            currentCapacity += vehicle.getCapacity();
            System.out.println("Ferry Status: " + currentCapacity + "/" + MAX_CAPACITY + 
                             " capacity used. Vehicle " + vehicle.getId() + " (" + 
                             vehicle.getType() + ") loaded.");
        } else {
            System.out.println("Cannot load vehicle " + vehicle.getId() + 
                             ": Ferry capacity would be exceeded");
        }
    }

    public synchronized void unloadVehicles() {
        System.out.println("Unloading " + vehiclesOnBoard.size() + " vehicles from ferry");
        for (Vehicle vehicle : vehiclesOnBoard) {
            System.out.println("Vehicle " + vehicle.getId() + " (" + vehicle.getType() + 
                             ") unloaded at side " + currentSide);
        }
        vehiclesOnBoard.clear();
        currentCapacity = 0;
        tripCount++;
        System.out.println("Ferry has completed " + tripCount + " trips");
    }

    public synchronized void changeSide() {
        currentSide = 1 - currentSide; // Switch between 0 and 1
        System.out.println("Ferry now at side " + currentSide);
    }

    public int getCurrentSide() {
        return currentSide;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public List<Vehicle> getVehiclesOnBoard() {
        return vehiclesOnBoard;
    }

    public int getTripCount() {
        return tripCount;
    }
} 