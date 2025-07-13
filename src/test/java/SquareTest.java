import org.junit.Test;
import static org.junit.Assert.*;

public class SquareTest {
    
    @Test
    public void testSquareCreation() {
        Square square = new Square(0);
        assertEquals(0, square.getSide());
        assertTrue(square.isEmpty());
    }
    
    @Test
    public void testVehicleQueue() {
        Square square = new Square(0);
        Vehicle car = new Vehicle(1, Vehicle.VehicleType.CAR);
        Vehicle minibus = new Vehicle(2, Vehicle.VehicleType.MINIBUS);
        
        // Test adding vehicles
        square.addVehicle(car);
        assertFalse(square.isEmpty());
        
        square.addVehicle(minibus);
        assertFalse(square.isEmpty());
        
        // Test getting vehicles in order
        Vehicle firstVehicle = square.getNextVehicle();
        assertEquals(car.getId(), firstVehicle.getId());
        
        Vehicle secondVehicle = square.getNextVehicle();
        assertEquals(minibus.getId(), secondVehicle.getId());
        
        assertTrue(square.isEmpty());
    }
    
    @Test
    public void testEmptySquare() {
        Square square = new Square(1);
        assertTrue(square.isEmpty());
        
        // Test getting vehicle from empty square
        Vehicle vehicle = square.getNextVehicle();
        assertNull(vehicle);
    }
} 