import java.util.concurrent.Semaphore;

public class Toll {
    private int id;
    private int side; // 0 or 1 for the two sides
    private Semaphore semaphore;

    public Toll(int id, int side) {
        this.id = id;
        this.side = side;
        this.semaphore = new Semaphore(1); // Only one vehicle at a time
    }

    public void passThrough(Vehicle vehicle) {
        try {
            semaphore.acquire();
            System.out.println("Vehicle " + vehicle.getId() + " passing through toll " + id + " on side " + side);
            Thread.sleep(1000); // Simulate toll processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public int getId() {
        return id;
    }

    public int getSide() {
        return side;
    }
} 