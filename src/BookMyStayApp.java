import java.util.HashMap;
import java.util.Map;

/**
 * UseCase4RoomSearch
 *
 * Demonstrates read-only room search functionality for the
 * Book My Stay application. The program retrieves room
 * availability from centralized inventory and displays
 * only available room types along with their details.
 *
 * Inventory state is not modified during search operations.
 *
 * @author Developer
 * @version 4.0
 */

/* Abstract Room Class */
abstract class Room {

    protected String type;
    protected int beds;
    protected double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : $" + price);
    }
}

/* Concrete Room Classes */

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 100.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 180.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 350.0);
    }
}

/* Centralized Inventory */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable example
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }
}

/* Search Service */
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {

        System.out.println("\n--- Available Rooms ---");

        for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {

            String roomType = entry.getKey();
            int available = entry.getValue();

            /* Defensive Programming: show only available rooms */
            if (available > 0) {

                Room room = null;

                if (roomType.equals("Single Room"))
                    room = new SingleRoom();
                else if (roomType.equals("Double Room"))
                    room = new DoubleRoom();
                else if (roomType.equals("Suite Room"))
                    room = new SuiteRoom();

                if (room != null) {
                    room.displayDetails();
                    System.out.println("Available : " + available);
                    System.out.println();
                }
            }
        }
    }
}

/* Main Application */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Room Search");
        System.out.println(" Version 4.0");
        System.out.println("=================================");

        /* Initialize Inventory */
        RoomInventory inventory = new RoomInventory();

        /* Initialize Search Service */
        RoomSearchService searchService = new RoomSearchService(inventory);

        /* Guest performs search */
        searchService.searchAvailableRooms();

        System.out.println("Search completed. System state unchanged.");
    }
}