import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Add some rooms
        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Deluxe"));
        rooms.add(new Room(103, "Suite"));

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom(sc);
                    break;

                case 3:
                    cancelBooking(sc);
                    break;

                case 4:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void viewRooms() {
        for (Room r : rooms) {
            System.out.println("Room " + r.getRoomNumber() +
                    " | Category: " + r.getCategory() +
                    " | Booked: " + r.isBooked());
        }
    }

    private static void bookRoom(Scanner sc) {
        System.out.print("Enter room number to book: ");
        int number = sc.nextInt();
        sc.nextLine();

        for (Room r : rooms) {
            if (r.getRoomNumber() == number && !r.isBooked()) {

                System.out.print("Enter customer name: ");
                String name = sc.nextLine();

                r.bookRoom();
                bookings.add(new Booking(name, r));

                System.out.println("Payment successful! Room booked.");
                return;
            }
        }

        System.out.println("Room not available.");
    }

    private static void cancelBooking(Scanner sc) {
        System.out.print("Enter room number to cancel: ");
        int number = sc.nextInt();

        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == number) {
                b.getRoom().cancelBooking();
                bookings.remove(b);
                System.out.println("Booking cancelled successfully.");
                return;
            }
        }

        System.out.println("Booking not found.");
    }
}
