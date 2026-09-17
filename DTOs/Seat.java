package Practice.MovieBookingSystem.DTOs;


public class Seat {
    private String seatNo;
    private Character row;
    SeatType seatType;

    public Seat(String seatNo, Character row, SeatType seatType) {
        this.seatNo = seatNo;
        this.row = row;
        this.seatType = seatType;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public Character getRow() {
        return row;
    }
}
