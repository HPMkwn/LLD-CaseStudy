package parkingLot.repositories;

import parkingLot.models.Vehicle;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<String, Vehicle> vehicles = new HashMap<>();
    private int previousId = 0;
    public Optional<Vehicle> getVehicleByNumber(String vehicleNumber){

        if(vehicles.containsKey(vehicleNumber)){
            return Optional.of(vehicles.get(vehicleNumber));
        }
        return Optional.empty();
    }

    public Vehicle setVehicle(Vehicle vehicle) {
        previousId += 1;
        vehicle.setId(previousId);

        vehicles.put(vehicle.getNumber(), vehicle);

        return vehicle;
    }
}
