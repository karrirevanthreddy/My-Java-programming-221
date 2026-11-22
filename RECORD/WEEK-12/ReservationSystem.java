package record;
class Reservation {
    // Set to 10 to match the expected output (change to 100 as mentioned in the prompt if needed)
    private int availableSeats = 10;

    // Synchronized method ensures only one thread can access reserve() at a time
    public synchronized void reserve(int requestedSeats) {
        System.out.println(Thread.currentThread().getName() + " entered.");
        System.out.println("Available seats: " + availableSeats + " Requested seats: " + requestedSeats);

        if (availableSeats >= requestedSeats) {
            System.out.println("Seat Available. Reserve now :-)");
            try {
                // Simulating booking delay
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            availableSeats -= requestedSeats;
            System.out.println(requestedSeats + " seats reserved.");
        } else {
            System.out.println("Requested seats not available :-)");
        }

        System.out.println(Thread.currentThread().getName() + " leaving.");
        System.out.println("----------------------------------------------");
    }
}

class Person extends Thread {
    private Reservation reservation;
    private int requestedSeats;

    public Person(Reservation reservation, String name, int requestedSeats) {
        super(name);
        this.reservation = reservation;
        this.requestedSeats = requestedSeats;
    }

    @Override
    public void run() {
        reservation.reserve(requestedSeats);
    }
}

public class ReservationSystem {
    public static void main(String[] args) {
        Reservation sharedReservation = new Reservation();

        Person p1 = new Person(sharedReservation, "Person-1", 5);
        Person p2 = new Person(sharedReservation, "Person-2", 2);
        Person p3 = new Person(sharedReservation, "Person-3", 4);

        p1.start();
        p2.start();
        p3.start();
    }
}
