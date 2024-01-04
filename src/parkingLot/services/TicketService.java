package parkingLot.services;

import parkingLot.exceptions.GateNotFoundException;
import parkingLot.models.*;
import parkingLot.repositories.GateRepository;
import parkingLot.repositories.ParkingLotRepository;
import parkingLot.repositories.TicketRepository;
import parkingLot.repositories.VehicleRepository;
import parkingLot.stratagies.SlotAllotmentStrategy;
import parkingLot.stratagies.SlotAllotmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(VehicleType vehicleType,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              Long gateId){


        //Create Ticket Object
        //Assign spot
        //Assign time
        //save to DB
        //return the created object

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // Get Gate modal from gateId
        Optional<Gate> gateOp = gateRepository.findeGateById(gateId);

        if(gateOp.isEmpty()){
            return new GateNotFoundException();
        }

        Gate gate = gateOp.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Vehicle savedVehicle;
        Optional<Vehicle> vehicleOptional = vehicleRepository.getVehicleByNumber(vehicleNumber);

        if(!vehicleOptional.isPresent()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setNumber(vehicleNumber);
            vehicle.setOwnerName(vehicleOwnerName);

            savedVehicle = vehicleRepository.setVehicle(vehicle);
        }else{
            savedVehicle = vehicleOptional.get();
        }

        ticket.setVehicle(savedVehicle);

        SlotAllocationStrategyType slotAllocationStrategyType = parkingLotRepository
                 .getParkingLotForGate(gate).get().getSlotAllocationStrategyType();

        SlotAllotmentStrategy slotAllotmentStrategy =
                SlotAllotmentStrategyFactory.getSlotAllotmentStrategy(
                        slotAllocationStrategyType
                );

        ticket.setParkingSpot(slotAllotmentStrategy.getParkingSpot(gate,vehicleType));

        ticket.setNumber("Ticket-"+ticket.getId());    // Generate UUID

        return ticketRepository.saveTicket(ticket);
    }
}
