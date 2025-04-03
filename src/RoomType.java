public enum RoomType {
    SINGLE(100),
    DOUBLE(150),
    DELUXE(250),
    SUITE(500);

    private final Integer roomPrice;

    RoomType(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomPrice(){
        return roomPrice;
    }

    public static Integer getRoomPrice(RoomType roomType){
        return roomType.getRoomPrice();
    }
}
