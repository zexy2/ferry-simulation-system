import org.junit.Test;
import static org.junit.Assert.*;

public class TollTest {
    
    @Test
    public void testTollCreation() {
        Toll toll = new Toll(1, 0);
        assertEquals(1, toll.getId());
        assertEquals(0, toll.getSide());
    }
    
    @Test
    public void testTollAccess() {
        Toll toll = new Toll(1, 0);
        Vehicle vehicle = new Vehicle(1, Vehicle.VehicleType.CAR);
        
        // Test that toll can be accessed
        toll.passThrough(vehicle);
        // If we reach here without exception, the test passes
        assertTrue(true);
    }
    
    @Test
    public void testMultipleTolls() {
        Toll toll1 = new Toll(1, 0);
        Toll toll2 = new Toll(2, 0);
        Vehicle vehicle1 = new Vehicle(1, Vehicle.VehicleType.CAR);
        Vehicle vehicle2 = new Vehicle(2, Vehicle.VehicleType.MINIBUS);
        
        // Test that different tolls can be accessed independently
        toll1.passThrough(vehicle1);
        toll2.passThrough(vehicle2);
        // If we reach here without exception, the test passes
        assertTrue(true);
    }
} 