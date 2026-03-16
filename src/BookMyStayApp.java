import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates handling booking requests using a Queue
 * to ensure First-Come-First-Served processing.
 *
 * Booking requests are collected and stored in arrival order.
 * No room allocation or inventory updates occur in this stage.
 *
 * @author Developer
 * @version 5.0
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

    public void displayReservation() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type  : " + roomType);
    }
}

/* Booking Request Queue */
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /* Add booking request to queue */
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("\nBooking request added for: " + reservation.getGuestName());
    }

    /* Display all queued requests */
    public void displayRequests() {

        System.out.println("\n--- Booking Request Queue ---");

        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }

        for (Reservation r : requestQueue) {
            r.displayReservation();
            System.out.println();
        }
    }
}

/* Main Application */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Book My Stay - Booking Requests");
        System.out.println(" Version 5.0");
        System.out.println("=================================");

        /* Initialize Booking Queue */
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        /* Guests submit booking requests */
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Suite Room");
        Reservation r3 = new Reservation("Charlie", "Double Room");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        /* Display queued requests */
        bookingQueue.displayRequests();

        System.out.println("Requests stored in FIFO order. Awaiting allocation...");
    }
}