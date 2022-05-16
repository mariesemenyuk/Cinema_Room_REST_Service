package cinema;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    int totalRows;
    int totalColumns;
    List<Seat> availableSeats;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Cinema() {
    }

    public Cinema(int totalRows, int totalColumns, List<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public static List<Seat> createAvailableSeats(int rows, int columns) {
        int price = 0;
        List<Seat> seats = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            price = (i + 1) < 4 ? 10 : 8;
            for(int j = 0; j < columns; j++){
                seats.add(new Seat(i + 1, j + 1, price));
            }
        }
        return seats;
    }
}
