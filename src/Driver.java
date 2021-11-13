package src;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import src.models.Customer;
import src.models.Reservation;
import src.models.Room;

public class Driver {

  public static boolean keepRunning = true;
  public static Map<Integer, Customer> currentCustomer = new HashMap<Integer, Customer>();
  public static Scanner scanner = new Scanner(System.in);

  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";

  public static void mianMenu() {
    System.out.println("-----------------------------------------");
    System.out.println(ANSI_YELLOW + "MAIN MENU" + ANSI_RESET);
    System.out.println("-----------------------------------------");
    System.out.println("1. Find and reserve a room");
    System.out.println("2. See my reservations");
    System.out.println("3. Create an Account");
    System.out.println("4. Admin");
    System.out.println("5. Exit");
    System.out.println("-----------------------------------------");
    System.out.println(
      ANSI_YELLOW + "Please select a number from the menu option" + ANSI_RESET
    );
  }

  public static void adminMenu() {
    System.out.println("-----------------------------------------");
    System.out.println(ANSI_YELLOW + "ADMIN MENU" + ANSI_RESET);
    System.out.println("-----------------------------------------");
    System.out.println("1. See all Customers");
    System.out.println("2. See all Rooms");
    System.out.println("3. See all Reservations");
    System.out.println("4. Add a room");
    System.out.println("5. Add Test Data");
    System.out.println("6. Back to Main Menu");
    System.out.println("-----------------------------------------");
    System.out.println(ANSI_YELLOW  + "Please select a number from the menu option" + ANSI_RESET);
  }

  public static Customer createCustomer(String firstName, String lastName){
      Customer newCustomer = new Customer(firstName, lastName);
      return newCustomer;
  }

  public static void showReservationsAdmin() {
    System.out.println(ANSI_YELLOW + "RESERVATIONS" + ANSI_RESET);
    System.out.println(ANSI_YELLOW +"------------"+ ANSI_RESET);
    Reservation.ppAll();
  }

  public static void showRoomsAdmin() {
    System.out.println(ANSI_YELLOW + "ROOMS" + ANSI_RESET);
    System.out.println(ANSI_YELLOW +"-----"+ ANSI_RESET);
    Room.ppAll();
  }

  public static void showCustomersAdmin() {
    System.out.println(ANSI_YELLOW + "CUSTOMERS" + ANSI_RESET);
    System.out.println(ANSI_YELLOW +"---------"+ ANSI_RESET);
    Customer.ppAll();
  }

  public static boolean checkCustomerLog() {
    if (Customer.all().size() > 0) {
      return true;
    } else {
      return false;
    }
  }

public static void createReservation(Customer customer, Room room){
        System.out.println("Choose a Checkin Date: Format(MM/DD/YYYY)");
        String checkInDate = scanner.nextLine();
        System.out.print("Choose a Checkout Date: Format(MM/DD/YYYY)");
        String checkOutDate = scanner.nextLine();
        // Create Reservation
        Reservation newReservation = new Reservation(checkInDate, checkOutDate, customer, room);
        System.out.println(newReservation.toString());     
}

  public static void showRooms() {
    Room.ppAll();
    System.out.println("-----------------------------------------");
    System.out.println(
      "Please enter a room number to choose a room to reserve."
    );
    int selection = Integer.parseInt(scanner.nextLine());
    //Check if room number is valid
    boolean roomExist = false;
    for(int i = 0; i < Room.all().size(); i++){
        Room room = Room.all().get(i);
        if(selection == room.getRoomNumber()){
            roomExist = true;
            break;
        } else {
            roomExist = false;
        }
    }
    if(roomExist == false) {
        System.out.println(ANSI_RED + "Not a valid room number" + ANSI_RESET);
    } else {
    Room room = Seed.chooseRoom(selection);
    System.out.println( ANSI_BLUE + "You chose: " + ANSI_RESET + ANSI_YELLOW + "Room " + room.getRoomNumber() + ANSI_RESET);
    System.out.println("");
    System.out.println( ANSI_BLUE +  "Is that okay? (Enter 1 for Yes/ 0 for No)" + ANSI_RESET );
    selection = Integer.parseInt(scanner.nextLine());
    if (selection == 1) {
      if (checkCustomerLog()) {
        System.out.println("Enter your first name to pull up your account:");
        String selection2 = scanner.nextLine();
        System.out.println(selection2);
        Customer loadedCustomer = Customer.findByFirstName(selection2);
        if ((loadedCustomer.getFirstName() == selection2)) {
          System.out.println("");
          System.out.println(
            "You entered the wrong name or create an account."
          );
          System.out.println("");
          enterMainMenu();
        } else {
          System.out.println("Taking you to reservations " + ANSI_YELLOW + loadedCustomer.getFirstName() + ANSI_RESET);
          System.out.println("");
          createReservation(loadedCustomer, room);
        }
      } else {
        System.out.println("");
        System.out.println(
         ANSI_RED +  "There are no accounts in our system. Create account first." + ANSI_RESET
        );
        System.out.println("");
        enterMainMenu();
      }
    } else if (selection == 0) {
      System.out.println("");
      System.out.println("Cancelling....");
      System.out.println("Going back to main menu......");
      System.out.println("");
      enterMainMenu();
    } else {
      System.out.println("");
      System.out.println("Please Enter 1 or 0.");
      System.out.println("");
      enterMainMenu();
    }
   }
  }

  public static void enterMainMenu() {
    while (keepRunning) {
      mianMenu();
      int selection = Integer.parseInt(scanner.nextLine());

      if (selection == 1) {
        System.out.println("");
        System.out.println(ANSI_BLUE + "Looking for available rooms....." + ANSI_RESET);
        System.out.println("");
        if (Room.all().size() == 0) {
          System.out.println(ANSI_RED + "There are no rooms available!" + ANSI_RESET);
          System.out.println(ANSI_RED + "Admin must create the roooms first!" + ANSI_RESET);
          System.out.println("");
          enterMainMenu();
        }
        showRooms();
      }
      if (selection == 2) {
        System.out.println("2. See my reservations");
      }

      if (selection == 3) {
        String firstName ="";
        String lastName = "";
        boolean keepRunning = true;
        //Get first name from customer
        while(keepRunning){
        System.out.print( ANSI_BLUE + "Enter First Name:" + ANSI_RESET + " ");
       String selection2 = scanner.nextLine();
       System.out.println( ANSI_BLUE + "You entered: " + ANSI_RESET + ANSI_YELLOW +  selection2 + ANSI_RESET);
       System.out.println(ANSI_BLUE +"Is that correct? (Enter 1 for Yes/ 0 for No)" + ANSI_RESET);
       int selection3 = Integer.parseInt(scanner.nextLine());
       if(selection3 == 1){
           firstName = selection2;
           keepRunning = false;
           }
        }
        System.out.println("");
        keepRunning = true;
        //Get last name from customer
        while(keepRunning){
            System.out.print( ANSI_BLUE + "Enter Last Name:" + ANSI_RESET + " ");
            String selection2 = scanner.nextLine();
            System.out.println( ANSI_BLUE + "You entered: " + ANSI_RESET + ANSI_YELLOW +  selection2 + ANSI_RESET);
            System.out.println(ANSI_BLUE +"Is that correct? (Enter 1 for Yes/ 0 for No)" + ANSI_RESET);
            int selection3 = Integer.parseInt(scanner.nextLine());
            if(selection3 == 1){
               lastName = selection2;
                keepRunning = false;
            }
        }
        // //create customer
        Customer newCustomer = createCustomer(firstName, lastName);
        System.out.println("");
        System.out.println("Account for " + ANSI_YELLOW + newCustomer.getFirstName() + " " + newCustomer.getLastName() + ANSI_RESET + " created!");
      }
      if (selection == 4) {
        System.out.println("");
        System.out.print("\033[H\033[2J");
        System.out.println(
          ANSI_BLUE + "Now entering Admin Menu......" + ANSI_RESET
        );
        enterAdminMenu();
      }
      if (selection == 5) {
        System.out.println(ANSI_BLUE + "Exiting........Goodybe!");
        scanner.close();
        keepRunning = false;
      }
      if (selection > 5) {
        System.out.println("");
        System.out.println(
          ANSI_RED + "Please enter a number between 1 and 5." + ANSI_RESET
        );
        System.out.println("");
      }
    }
  }

  public static void enterAdminMenu() {
    while (keepRunning) {
      adminMenu();
      int selection = Integer.parseInt(scanner.nextLine());
      if (selection == 1) {
        if (Customer.all().size() == 0) {
          System.out.println(
            ANSI_BLUE + "There are no customers in the log." + ANSI_RESET
          );
        } else {
          showCustomersAdmin();
        }
      }
      if (selection == 2) {
          if (Room.all().size() == 0){
            System.out.println(ANSI_BLUE + "There are no rooms in the log" + ANSI_RESET);  
          } else {
            showRoomsAdmin();  
          }   
      }
      if (selection == 3) {
        if (Reservation.all().size() == 0) {
          System.out.println(
            ANSI_BLUE + "There are no reservations in the log." + ANSI_RESET
          );
        } else {
          showReservationsAdmin();
        }
      }
      if (selection == 4) {
            Room newRoom = Seed.addRoom();
            System.out.println( ANSI_YELLOW + "Room " + newRoom.getRoomNumber() + " added!" + ANSI_RESET);
          }
      if (selection == 5) {
        System.out.println("");
        System.out.println(ANSI_BLUE + "Creating Rooms........" + ANSI_RESET);
        System.out.println("");
        if (Room.all().size() > 0) {
          System.out.println("");
          System.out.println(ANSI_RED + "Rooms already created!" + ANSI_RESET);
          System.out.println("");
          enterAdminMenu();
        }
        Seed.createRooms();
        System.out.println(ANSI_BLUE +"Rooms Created!" + ANSI_RESET);
        System.out.println("");
        enterAdminMenu();
      }
      if (selection == 6) {
        System.out.print("\033[H\033[2J");
        System.out.println("");
        System.out.print(
          ANSI_BLUE + "Going back to main menu......" + ANSI_RESET
        );
        System.out.println("");
        enterMainMenu();
      }
    }
  }

  public static void main(String[] args) {
   enterMainMenu();
  }
}
