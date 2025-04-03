import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private final String reservationId;
    private User guest;
    private Room room;
    private Date reservationStartDt;
    private Date reservationEndDt;
    private ReservationStatus reservationStatus;
    private double price;

    public Reservation(String reservationId, User guest, Room room, Date reservationStartDt, Date reservationEndDt) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.reservationStartDt = reservationStartDt;
        this.reservationEndDt = reservationEndDt;
        this.reservationStatus = ReservationStatus.RESERVED;
    }

    public String getReservationId() {
        return reservationId;
    }

    public User getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public Date getReservationStartDt() {
        return reservationStartDt;
    }

    public Date getReservationEndDt() {
        return reservationEndDt;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public synchronized void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
