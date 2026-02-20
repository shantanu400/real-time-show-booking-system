
package factory;

import entity.Seat;
import enums.SeatType;

import java.util.ArrayList;
import java.util.List;

public class SeatFactory {

    private SeatFactory() {} // prevent instantiation

    public static List<Seat> createSeats(int rows,
                                         int seatsPerRow) {

        List<Seat> seats = new ArrayList<>();

        char rowName = 'A';

        for (int i = 0; i < rows; i++) {

            SeatType type = getSeatTypeForRow(i);

            for (int j = 1; j <= seatsPerRow; j++) {
                String seatId = rowName + String.valueOf(j);
                seats.add(new Seat(seatId, type));
            }

            rowName++;
        }

        return seats;
    }

    private static SeatType getSeatTypeForRow(int rowIndex) {
        if (rowIndex == 0) return SeatType.SILVER;
        if (rowIndex == 1) return SeatType.GOLD;
        return SeatType.RECLINER;
    }
}
