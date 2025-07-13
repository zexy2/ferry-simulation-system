import java.util.concurrent.LinkedBlockingQueue;

public class Square {
    private int side; // 0 or 1 for the two sides
    private LinkedBlockingQueue<Vehicle> waitingVehicles;

    public Square(int side) {
        this.side = side;
        this.waitingVehicles = new LinkedBlockingQueue<>();
    }

    public void addVehicle(Vehicle vehicle) {
        try {
            waitingVehicles.put(vehicle);
            System.out.println("Vehicle " + vehicle.getId() + " added to square on side " + side);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Vehicle getNextVehicle() {
        try {
            return waitingVehicles.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmpty() {
        return waitingVehicles.isEmpty();
    }

    public int getSide() {
        return side;
    }
} 