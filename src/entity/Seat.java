package entity;

import enums.SeatType;

public class Seat {

    private String seatId;
    private SeatType type;

    public Seat(String seatId, SeatType type) {
        this.seatId = seatId;
        this.type = type;
    }

    public String getSeatId() {
        return seatId;
    }

    public SeatType getType() {
        return type;
    }
}
