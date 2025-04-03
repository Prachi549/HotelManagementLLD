import java.util.*;

public class HotelMgmtSystem {
    //DOUBT why private final instead of private only? - we can add guests, rooms and reservations anytime right
    private HashMap<String,Room> roomList;
    private HashMap<String,User> guestList;
    private HashMap<String,Reservation> reservationList;
    private static HotelMgmtSystem hotelMgmtSystemInstance;

    public HotelMgmtSystem() {
        this.roomList = new HashMap<>();
        this.reservationList = new HashMap<>();
        this.guestList = new HashMap<>();
    }

    public static HotelMgmtSystem getHotelMgmtSystemInstance() {
        if(hotelMgmtSystemInstance==null){
            synchronized (HotelMgmtSystem.class){
                if(hotelMgmtSystemInstance==null){
                    hotelMgmtSystemInstance = new HotelMgmtSystem();
                }
            }
        }
        return hotelMgmtSystemInstance;
    }

    public void addRoom(Room room){
        String roomId = room.getRoomId();
        roomList.put(roomId,room);
    }

    public void addGuest(User guest){
        String guestId = guest.getUserId();
        guestList.put(guestId,guest);
    }

    public List<Room> getAvailableRooms(){
        List<Room> filterList = new ArrayList<>();
        for(Map.Entry<String,Room> room:roomList.entrySet()){
            if(room.getValue().getRoomStatus().equals(RoomStatus.AVAILABLE)){
                filterList.add(room.getValue());
            }
        }
        return filterList;
    }

    public List<Room> getAvailableRooms(RoomType roomType){
        List<Room> filterList = new ArrayList<>();
        for(Map.Entry<String,Room> room:roomList.entrySet()){
            if((room.getValue().getRoomStatus().equals(RoomStatus.AVAILABLE))&& (room.getValue().getRoomType().equals(roomType))){
                filterList.add(room.getValue());
            }
        }
        return filterList;
    }

    public synchronized void createReservation(User guest, Room room, Date startDate, Date endDate){
        String reservationId = generateReservationId();
        Reservation reservation = new Reservation(reservationId, guest, room, startDate, endDate);
        room.setRoomStatus(RoomStatus.BOOKED);
    }

    public synchronized void CheckIn(Reservation reservation){
        reservation.setReservationStatus(ReservationStatus.OCCUPIED);
        reservation.getRoom().setRoomStatus(RoomStatus.OCCUPIED);
    }

    //DOUBT - How did it throw exception without throws in method signature?
    //DOUBT - is exception handling + exact exception handling necessary?
    public synchronized void CheckOut(Reservation reservation, Payment payment) throws Exception {
        if((reservation!=null) && (reservation.getReservationStatus().equals(ReservationStatus.OCCUPIED))){
            //DOUBT - NOTE - SHOULD NOT DO IT THIS WAY - THERE SHOULD BE A METHOD TO MODIFY STATUS INSTEAD OF HERE
            reservation.setReservationStatus(ReservationStatus.COMPLETED);
            //DOUBT - NOTE - SHOULD NOT DO IT THIS WAY - THERE SHOULD BE A METHOD TO MODIFY STATUS INSTEAD OF HERE
            reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
            Bill bill = new Bill(reservation);
            double price = bill.calculatePrice();
            if(payment.processPayment(price)){
                System.out.println("Payment processed");
                System.out.println("Check-out complete");
                reservationList.remove(reservation.getReservationId());
            }
            else {
                throw new Exception("Payment Failed");
            }
        }
        else{
            throw new Exception("Reservation not found");
        }
    }

    public synchronized void CancelReservation(Reservation reservation){
        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
        reservationList.remove(reservation.getReservationId());
    }

    public String generateReservationId(){
        return "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }



}
