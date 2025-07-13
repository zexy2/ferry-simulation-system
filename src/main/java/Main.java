import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static AtomicBoolean allVehiclesReturned = new AtomicBoolean(false);

    public static void main(String[] args) {
        Statistics stats = Statistics.getInstance();
        
        // Create ferry
        Ferry ferry = new Ferry();

        // Create squares for both sides
        Square[] squares = new Square[2];
        squares[0] = new Square(0);
        squares[1] = new Square(1);

        // Create tolls (2 for each side)
        Toll[] tolls = new Toll[4];
        tolls[0] = new Toll(0, 0);
        tolls[1] = new Toll(1, 0);
        tolls[2] = new Toll(2, 1);
        tolls[3] = new Toll(3, 1);

        // Create vehicles
        List<Vehicle> vehicles = new ArrayList<>();
        int vehicleId = 0;

        // Create cars
        for (int i = 0; i < 12; i++) {
            vehicles.add(new Vehicle(vehicleId++, Vehicle.VehicleType.CAR));
        }

        // Create minibuses
        for (int i = 0; i < 10; i++) {
            vehicles.add(new Vehicle(vehicleId++, Vehicle.VehicleType.MINIBUS));
        }

        // Create trucks
        for (int i = 0; i < 8; i++) {
            vehicles.add(new Vehicle(vehicleId++, Vehicle.VehicleType.TRUCK));
        }

        // Create and start vehicle threads
        List<Thread> vehicleThreads = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            Thread vehicleThread = new Thread(() -> {
                long waitStartTime = System.currentTimeMillis();
                while (!vehicle.hasReturned()) {
                    // Choose random toll on the current side
                    int tollIndex = vehicle.getCurrentSide() * 2 + (int)(Math.random() * 2);
                    vehicle.setTollNumber(tollIndex);

                    // Pass through toll
                    tolls[tollIndex].passThrough(vehicle);

                    // Add to square
                    squares[vehicle.getCurrentSide()].addVehicle(vehicle);

                    // Record wait time
                    long waitEndTime = System.currentTimeMillis();
                    stats.recordVehicleWaitTime(vehicle.getId(), waitEndTime - waitStartTime);
                    waitStartTime = waitEndTime;

                    // Wait for ferry to transport
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Vehicle " + vehicle.getId() + " has completed its journey and returned to starting side " + 
                                 vehicle.getStartingSide());
            });
            vehicleThreads.add(vehicleThread);
            vehicleThread.start();
        }

        // Create and start ferry thread
        Thread ferryThread = new Thread(() -> {
            long tripStartTime = System.currentTimeMillis();
            while (!allVehiclesReturned.get()) {
                // Check if ferry is full or no more vehicles to load
                if (ferry.getCurrentCapacity() >= Ferry.MAX_CAPACITY || 
                    squares[ferry.getCurrentSide()].isEmpty()) {
                    
                    // Record trip duration
                    long tripEndTime = System.currentTimeMillis();
                    stats.recordFerryTrip(tripEndTime - tripStartTime);
                    tripStartTime = tripEndTime;
                    
                    // Travel to other side
                    System.out.println("Ferry traveling from side " + ferry.getCurrentSide() + 
                                     " to side " + (1 - ferry.getCurrentSide()));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    // Unload vehicles and update their sides
                    for (Vehicle vehicle : ferry.getVehiclesOnBoard()) {
                        vehicle.setCurrentSide(1 - vehicle.getCurrentSide());
                        stats.recordVehicleTrip(vehicle.getId());
                    }
                    ferry.unloadVehicles();
                    
                    // Change side
                    ferry.changeSide();
                } else {
                    // Load next vehicle
                    Vehicle nextVehicle = squares[ferry.getCurrentSide()].getNextVehicle();
                    if (nextVehicle != null && ferry.canLoadVehicle(nextVehicle)) {
                        ferry.loadVehicle(nextVehicle);
                        System.out.println("Vehicle " + nextVehicle.getId() + " loaded onto ferry");
                    }
                }

                // Check if all vehicles have returned
                boolean allReturned = true;
                for (Vehicle vehicle : vehicles) {
                    if (!vehicle.hasReturned()) {
                        allReturned = false;
                        break;
                    }
                }
                allVehiclesReturned.set(allReturned);
            }
            System.out.println("All vehicles have returned to their starting sides. Simulation complete.");
        });
        ferryThread.start();

        // Wait for all vehicle threads to complete
        for (Thread thread : vehicleThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Wait for ferry thread to complete
        try {
            ferryThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Generate final statistics report
        stats.generateReport();
        
        // Generate analysis report
        Report.generateReport();
    }
} 