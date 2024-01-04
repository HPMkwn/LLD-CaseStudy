package parkingLot.repositories;

import parkingLot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long,Ticket> ticketsBucket = new HashMap<>();
    private int previousId = 0;
    public Ticket saveTicket(Ticket ticket){
        previousId += 1;
        ticket.setId(previousId);

        ticketsBucket.put((long) previousId, ticket);

        return ticket;
    }
}
