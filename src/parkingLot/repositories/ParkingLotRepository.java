package parkingLot.repositories;

import parkingLot.models.Gate;
import parkingLot.models.ParkingLot;

import javax.swing.*;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotRepository {

    private Map<Long,ParkingLot> parkingLotMap = new TreeMap<>();

    public Optional<ParkingLot> getParkingLotForGate(Gate gate){


        for(ParkingLot parkingLot : parkingLotMap.values()){
            if(parkingLot.getGates().contains(gate)){
                return Optional.of(parkingLot);
            }
        }


        return Optional.empty();
    }
}
