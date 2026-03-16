

abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Size (sqft): " + size);
        System.out.println("Price     : $" + price);
    }
}

/* Single Room Class */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 100.0);
    }
}

/* Double Room Class */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 180.0);
    }
}

/* Suite Room Class */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 350.0);
    }
}

/* Main Application */
public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Hotel Booking");
        System.out.println(" Version 2.1");
        System.out.println("=================================");

        /* Static availability variables */
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        /* Room objects */
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("\n--- Room Details ---");

        single.displayRoomDetails();
        System.out.println("Available : " + singleRoomAvailability);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available : " + doubleRoomAvailability);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteRoomAvailability);

        System.out.println("\nApplication terminated.");
    }
}