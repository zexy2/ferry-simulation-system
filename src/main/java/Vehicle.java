public class Vehicle {
    private int id;
    private VehicleType type;
    private int capacity; // How many car units this vehicle takes
    private int currentSide; // 0 or 1 for the two sides
    private int tollNumber; // Which toll the vehicle is using
    private boolean hasReturned; // Yeni eklenen: Araç başlangıç noktasına döndü mü?
    private int startingSide; // Yeni eklenen: Başlangıç yakası
    private int tripsCompleted; // Yeni eklenen: Tamamlanan sefer sayısı

    public enum VehicleType {
        CAR(1),
        MINIBUS(2),
        TRUCK(3);

        private final int capacity;

        VehicleType(int capacity) {
            this.capacity = capacity;
        }

        public int getCapacity() {
            return capacity;
        }
    }

    public Vehicle(int id, VehicleType type) {
        this.id = id;
        this.type = type;
        this.capacity = type.getCapacity();
        this.startingSide = (int) (Math.random() * 2);
        this.currentSide = this.startingSide;
        this.hasReturned = false;
        this.tripsCompleted = 0;
    }

    public int getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(int side) {
        this.currentSide = side;
        // Eğer araç başlangıç noktasına döndüyse
        if (this.currentSide == this.startingSide) {
            this.tripsCompleted++;
            // Eğer araç karşı yakaya gidip geri döndüyse
            if (this.tripsCompleted > 0) {
                this.hasReturned = true;
            }
        }
    }

    public int getTollNumber() {
        return tollNumber;
    }

    public void setTollNumber(int tollNumber) {
        this.tollNumber = tollNumber;
    }

    public boolean hasReturned() {
        return hasReturned;
    }

    public int getStartingSide() {
        return startingSide;
    }

    public int getTripsCompleted() {
        return tripsCompleted;
    }
} 