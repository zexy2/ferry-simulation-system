import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleTest {
    
    @Test
    public void testVehicleCreation() {
        Vehicle car = new Vehicle(1, Vehicle.VehicleType.CAR);
        assertEquals(1, car.getId());
        assertEquals(Vehicle.VehicleType.CAR, car.getType());
        assertEquals(1, car.getCapacity());
        
        Vehicle minibus = new Vehicle(2, Vehicle.VehicleType.MINIBUS);
        assertEquals(2, minibus.getId());
        assertEquals(Vehicle.VehicleType.MINIBUS, minibus.getType());
        assertEquals(2, minibus.getCapacity());
        
        Vehicle truck = new Vehicle(3, Vehicle.VehicleType.TRUCK);
        assertEquals(3, truck.getId());
        assertEquals(Vehicle.VehicleType.TRUCK, truck.getType());
        assertEquals(3, truck.getCapacity());
    }
    
    @Test
    public void testVehicleSideManagement() {
        Vehicle vehicle = new Vehicle(1, Vehicle.VehicleType.CAR);
        int startingSide = vehicle.getStartingSide();
        
        // Test side change
        vehicle.setCurrentSide(1 - startingSide);
        assertEquals(1 - startingSide, vehicle.getCurrentSide());
        
        // Test return to starting side
        vehicle.setCurrentSide(startingSide);
        assertEquals(startingSide, vehicle.getCurrentSide());
        assertEquals(1, vehicle.getTripsCompleted());
    }
    
    @Test
    public void testVehicleReturnStatus() {
        Vehicle vehicle = new Vehicle(1, Vehicle.VehicleType.CAR);
        int startingSide = vehicle.getStartingSide();
        
        // First trip
        vehicle.setCurrentSide(1 - startingSide);
        assertFalse(vehicle.hasReturned());
        
        // Return trip
        vehicle.setCurrentSide(startingSide);
        assertTrue(vehicle.hasReturned());
    }
} 