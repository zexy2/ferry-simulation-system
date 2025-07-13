# Operating Systems Project - Ferry Simulation


## Project Description
This project simulates a ferry system operating between two sides of a city. The system includes vehicles (cars, minibuses, and trucks) that need to cross between the two sides using a ferry with limited capacity.

## System Requirements
- Java 8 or higher
- Maven (for building and testing)

## How to Run
1. Compile and run the simulation:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```

2. Run tests:
```bash
mvn test
```

## Project Structure
```
src/
├── main/java/
│   ├── Main.java
│   ├── Vehicle.java
│   ├── Ferry.java
│   ├── Toll.java
│   ├── Square.java
│   ├── Statistics.java
│   └── Report.java
└── test/java/
    ├── VehicleTest.java
    ├── FerryTest.java
    ├── TollTest.java
    └── SquareTest.java
```

## Test Coverage
The project includes unit tests for:
- Vehicle creation and management
- Ferry capacity and operations
- Toll booth functionality
- Square (waiting area) management

## Output Files
- `FerrySimulationReport.txt`: Detailed analysis report
- Console output: Real-time simulation statistics

## Notes
- The simulation runs until all vehicles return to their starting sides
- Statistics are collected throughout the simulation
- A detailed report is generated at the end
- All components are thoroughly tested 
