package src.models;


import java.util.ArrayList;

public class Reservation {

  private int id;
  private String checkInDate;
  private String checkOutDate;
  private Customer customer;
  private Room room;
  private static ArrayList<Reservation> all = new ArrayList<>();
  private static int idGenerator = 0;

  public Reservation(
    String checkInDate,
    String checkOutDate,
    Customer customer,
    Room room
  ) {
    this.id = idGenerator += 1;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.customer = customer;
    this.room = room;
    all.add(this);
  }

  public String getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(String checkInDate) {
    this.checkInDate = checkInDate;
  }

  public String getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(String checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public int getId() {
    return id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Room getRoom() {
    return room;
  }

  public static ArrayList<Reservation> all() {
    return all;
  }

  public static void ppAll() {
    for (int i = 0; i < Reservation.all().size(); i++) {
      Reservation reservation = Reservation.all().get(i);
      System.out.println(reservation.getId() + ". Checkin Date: " +  reservation.getCheckInDate() + " Checkout Date: " + reservation.getCheckOutDate() + " Customer: " + reservation.getCustomer().getFirstName() + " " + reservation.getCustomer().getLastName() + " Room: #" + reservation.getRoom().getRoomNumber());
    }
  }

  @Override
  public String toString() {
    return (
      "<Reservation @id=" +
      id +
      " @checkInDate=" +
      checkInDate +
      " @checkOutDate=" +
      checkOutDate +
      " @user=" +
      customer.getFirstName() +
      " @roomNumber=" +
      room.getRoomNumber() +
      ">"
    );
  }
}
