public class Room {
    private final String roomId;
    private RoomStatus roomStatus;
    private final RoomType roomType;

    public Room(String roomId, RoomType roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomStatus = RoomStatus.AVAILABLE;
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public synchronized void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
}
