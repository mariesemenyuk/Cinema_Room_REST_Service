package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PurchaseResult {

    UUID token;

    @JsonProperty("ticket")
    private Seat seat;

    public PurchaseResult() {
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
