package parkingLot.dtos;

import parkingLot.models.Ticket;
import parkingLot.models.Vehicle;
import parkingLot.models.VehicleType;

public class IssueTicketResponse {

    private ResposneStatus responseStatus;
    private VehicleType vehicleType;
    private Ticket ticket;
    private String errorMessage;

    public ResposneStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResposneStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
