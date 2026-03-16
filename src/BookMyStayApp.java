import java.util.*;

/**
 * UseCase6BookingAllocation
 *
 * Demonstrates safe room allocation for booking requests
 * using Queue, HashMap, and Set to prevent double booking
 * and maintain inventory consistency.
 *
 * @author Developer
 * @version 6.0
 */

/* Reservation Class */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/* Booking Request Queue */
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

/* Inventory Service */
class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

/* Booking Service */
class BookingService {

    private RoomInventory inventory;

    /* Map room type → allocated room IDs */
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    /* Global set of used room IDs */
    private Set<String> usedRoomIds = new HashSet<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processReservation(Reservation r) {

        String roomType = r.getRoomType();

        if (inventory.getAvailability(roomType) <= 0) {
            System.out.println("No rooms available for " + roomType +
                    " (Guest: " + r.getGuestName() + ")");
            return;
        }

        /* Generate unique room ID */
        String roomId;
        do {
            roomId = roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 4);
        } while (usedRoomIds.contains(roomId));

        usedRoomIds.add(roomId);

        allocatedRooms.putIfAbsent(roomType, new HashSet<>());
        allocatedRooms.get(roomType).add(roomId);

        /* Update inventory */
        inventory.decreaseAvailability(roomType);

        /* Confirm reservation */
        System.out.println("Reservation Confirmed:");
        System.out.println("Guest: " + r.getGuestName());
        System.out.println("Room Type: " + roomType);
        System.out.println("Assigned Room ID: " + roomId);
        System.out.println();
    }
}

/* Main Application */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Booking Allocation");
        System.out.println(" Version 6.0");
        System.out.println("=================================");

        /* Initialize services */
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        /* Add booking requests */
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Suite Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room")); // should fail

        /* Process queue (FIFO) */
        while (queue.hasRequests()) {

            Reservation r = queue.getNextRequest();
            bookingService.processReservation(r);
        }

        System.out.println("All booking requests processed.");
    }
}