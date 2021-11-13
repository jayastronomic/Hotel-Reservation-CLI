package src.models;

import java.util.ArrayList;

public class Room {

  private int id;
  private int roomNumber;
  private String bedType;
  private double price;
  private static ArrayList<Room> all = new ArrayList<>();
  private static int idGenerator = 0;

  public Room(int roomNumber, String bedType, int price) {
    this.id = idGenerator += 1;
    this.roomNumber = roomNumber;
    this.bedType = bedType;
    this.price = price;
    all.add(this);
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  public String getBedType() {
    return bedType;
  }

  public void setBedType(String bedType) {
    this.bedType = bedType;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public static ArrayList<Room> all() {
    return all;
  }

  // ppAll === "pretty print" all room instances
  public static void ppAll() {
    for (int i = 0; i < Room.all().size(); i++) {
      System.out.println(all().get(i).toString());
    }
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return (
      "Room Number: " +
      roomNumber +
      " " +
      bedType +
      " bed Room Price: $" +
      price
    );
  }
}
