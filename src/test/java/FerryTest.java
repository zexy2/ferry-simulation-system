import org.junit.Test;
import static org.junit.Assert.*;

public class FerryTest {
    
    @Test
    public void testFerryCreation() {
        Ferry ferry = new Ferry();
        assertEquals(0, ferry.getCurrentCapacity());
        assertTrue(ferry.getCurrentSide() >= 0 && ferry.getCurrentSide() <= 1);
        assertTrue(ferry.getVehiclesOnBoard().isEmpty());
    }
    
    @Test
    public void testFerryCapacity() {
        Ferry ferry = new Ferry();
        Vehicle car = new Vehicle(1, Vehicle.VehicleType.CAR);
        Vehicle minibus = new Vehicle(2, Vehicle.VehicleType.MINIBUS);
        Vehicle truck = new Vehicle(3, Vehicle.VehicleType.TRUCK);
        
        // Test loading vehicles
        assertTrue(ferry.canLoadVehicle(car));
        ferry.loadVehicle(car);
        assertEquals(1, ferry.getCurrentCapacity());
        
        assertTrue(ferry.canLoadVehicle(minibus));
        ferry.loadVehicle(minibus);
        assertEquals(3, ferry.getCurrentCapacity());
        
        assertTrue(ferry.canLoadVehicle(truck));
        ferry.loadVehicle(truck);
        assertEquals(6, ferry.getCurrentCapacity());
    }
    
    @Test
    public void testFerryUnloading() {
        Ferry ferry = new Ferry();
        Vehicle car = new Vehicle(1, Vehicle.VehicleType.CAR);
        Vehicle minibus = new Vehicle(2, Vehicle.VehicleType.MINIBUS);
        
        ferry.loadVehicle(car);
        ferry.loadVehicle(minibus);
        assertEquals(3, ferry.getCurrentCapacity());
        assertEquals(2, ferry.getVehiclesOnBoard().size());
        
        ferry.unloadVehicles();
        assertEquals(0, ferry.getCurrentCapacity());
        assertTrue(ferry.getVehiclesOnBoard().isEmpty());
    }
    
    @Test
    public void testFerrySideChange() {
        Ferry ferry = new Ferry();
        int initialSide = ferry.getCurrentSide();
        
        ferry.changeSide();
        assertEquals(1 - initialSide, ferry.getCurrentSide());
        
        ferry.changeSide();
        assertEquals(initialSide, ferry.getCurrentSide());
    }
} 