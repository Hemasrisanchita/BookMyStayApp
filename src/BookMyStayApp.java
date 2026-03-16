import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * This program demonstrates centralized room inventory management
 * for the Book My Stay application using a HashMap data structure.
 *
 * Room availability is stored and managed through a dedicated
 * RoomInventory component which acts as the single source of truth.
 *
 * @author Developer
 * @version 3.1
 */

/* Inventory Management Class */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    /* Constructor initializes inventory */
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    /* Get availability of a specific room type */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /* Update availability */
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    /* Display entire inventory */
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

/* Main Application Class */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Hotel Booking");
        System.out.println(" Version 3.1");
        System.out.println("=================================");

        /* Initialize Inventory */
        RoomInventory inventory = new RoomInventory();

        /* Display Current Inventory */
        inventory.displayInventory();

        /* Example availability check */
        System.out.println("\nChecking availability for Single Room...");
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        /* Example update */
        System.out.println("\nUpdating availability for Single Room...");
        inventory.updateAvailability("Single Room", 4);

        /* Display updated inventory */
        inventory.displayInventory();

        System.out.println("\nApplication terminated.");
    }
}