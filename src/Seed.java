package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import src.models.Room;

public class Seed {

  // This variablie is the initial room number that starts at Room 100;
  private static int roomNumberInit = 100;

  private static final String[] roomTypes = { "Single", "Double" };
  private static final int[] roomPrice = { 100, 150 };
  private static final int[] array = new int[10];
  private static Map<Integer, Room> mapOfRooms = new HashMap<Integer, Room>();

  public static void createRooms() {
    for (int i = 0; i < array.length; i++) {
      Random rand = new Random();
      int upperBound = 2;
      int int_random = rand.nextInt(upperBound);
      if (int_random == 0) {
        Room room = new Room(roomNumberInit++, roomTypes[0], roomPrice[0]);
        mapOfRooms.put(room.getRoomNumber(), room);
      } else {
        Room room = new Room(roomNumberInit++, roomTypes[1], roomPrice[1]);
        mapOfRooms.put(room.getRoomNumber(), room);
      }
    }
  }

  public static Room chooseRoom(int selection) {
    Room room = mapOfRooms.get(selection);
    return room;
  }

  public static Room addRoom() {
    Random rand = new Random();
    int upperBound = 2;
    int int_random = rand.nextInt(upperBound);
    if (int_random == 0) {
      Room room = new Room(roomNumberInit++, roomTypes[0], roomPrice[0]);
      mapOfRooms.put(room.getRoomNumber(), room);
      return room;
    } else {
      Room room = new Room(roomNumberInit++, roomTypes[1], roomPrice[1]);
      mapOfRooms.put(room.getRoomNumber(), room);
      return room;
    }
  }
}
