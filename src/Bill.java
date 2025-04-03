import java.time.LocalDate;
import java.util.Date;

public class Bill {
    Reservation reservation;
    double price;

    public Bill(Reservation reservation) {
        this.reservation = reservation;
    }
    public double calculatePrice(){
        RoomType roomType = this.reservation.getRoom().getRoomType();
        Date startDate = this.reservation.getReservationStartDt();
        Date endDate = this.reservation.getReservationEndDt();
        long diff= (endDate.getTime() - startDate.getTime())/1000*60*60*24;
        return diff*roomType.getRoomPrice();
    }
}
