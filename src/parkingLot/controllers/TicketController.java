package parkingLot.controllers;

import parkingLot.dtos.IssueTicketRequest;
import parkingLot.dtos.IssueTicketResponse;
import parkingLot.dtos.ResposneStatus;
import parkingLot.models.Ticket;
import parkingLot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    private Ticket ticket;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponse issueTicket(IssueTicketRequest issueTicketRequest) {

        IssueTicketResponse issueTicketResponse = new IssueTicketResponse();

        try{
            ticket = ticketService.issueTicket(
                    issueTicketRequest.getVehicleType(),
                    issueTicketRequest.getVehicleNumber(),
                    issueTicketRequest.getVehicleOwner(),
                    issueTicketRequest.getGateId()
            );
        }catch (Exception e){
            issueTicketResponse.setResponseStatus(ResposneStatus.ERROR);
            issueTicketResponse.setErrorMessage(e.getMessage());
            return issueTicketResponse;
        }

        issueTicketResponse.setResponseStatus(ResposneStatus.SUCCESS);
        issueTicketResponse.setTicket(ticket);

        return issueTicketResponse;
    }
}
