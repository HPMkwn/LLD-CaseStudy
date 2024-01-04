package parkingLot.stratagies;

import parkingLot.models.Gate;
import parkingLot.models.ParkingSpot;
import parkingLot.models.Vehicle;
import parkingLot.models.VehicleType;

public interface SlotAllotmentStrategy {

    public ParkingSpot getParkingSpot(Gate gate, VehicleType vehicleType);
}
