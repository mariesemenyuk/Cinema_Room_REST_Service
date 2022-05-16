package cinema;

import cinema.exceptions.PasswordWrongException;
import cinema.exceptions.TicketIsBoughtException;
import cinema.exceptions.WrongRowColumnException;
import cinema.exceptions.WrongTokenException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    private final static int rows = 9;
    private final static int columns = 9;

    List<Seat> availableSeats = Cinema.createAvailableSeats(rows, columns);
    List<PurchaseResult> boughtTickets = new ArrayList<>();
    Cinema cinema = new Cinema(rows, columns, availableSeats);
    long sum;

    @GetMapping("/seats")
    public Cinema getAllSeats(){
        return cinema;
    }

    @PostMapping("/purchase")
    public PurchaseResult buyTicket(@RequestBody Seat seat) {
        if(seat.getRow() > rows || seat.getColumn() > columns || seat.getRow() < 1 || seat.getColumn() < 1) {
            throw new WrongRowColumnException();
        }
        Seat seatForBooking = availableSeats.stream()
                .filter(s -> s.getRow() == seat.getRow() && s.getColumn() == seat.getColumn())
                .findAny().orElseThrow();
        if(seatForBooking.isBooked()) {
            throw new TicketIsBoughtException();
        }

        seatForBooking.setBooked(true);
        PurchaseResult result = new PurchaseResult();
        result.setToken(UUID.randomUUID());
        result.setSeat(seatForBooking);
        sum += seatForBooking.getPrice();
        boughtTickets.add(result);
        return result;
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody PurchaseResult token) {
        PurchaseResult purchaseResult = boughtTickets.stream()
                .filter((ticket -> ticket.getToken().toString().equals(token.getToken().toString())))
                .findAny()
                .orElseThrow(() -> new WrongTokenException());

        Seat returnedTicket = purchaseResult.getSeat();
        boughtTickets.remove(purchaseResult);
        ReturnedTicket returnedSeat = new ReturnedTicket();
        for (Seat seat: availableSeats) {
            if(seat.getRow() == returnedTicket.getRow() && seat.getColumn() == returnedTicket.getColumn()) {
                seat.setBooked(false);
                returnedSeat.setSeat(seat);
                break;
            }
        }
        sum = sum - returnedSeat.getSeat().getPrice();
        return returnedSeat;
    }

    @PostMapping("/stats")
    public Statistics getStatistics(@RequestParam(required = false) String password) {
        if(password == null || !password.equals("super_secret")) {
            throw new PasswordWrongException();
        }
        return new Statistics(sum, availableSeats.size() - boughtTickets.size(), boughtTickets.size());
    }
}
