package src.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {

  private int id;
  private String firstName;
  private String lastName;
  private static ArrayList<Customer> all = new ArrayList<>();
  private static Map<String, Customer> mapOfCustomers = new HashMap<String, Customer>();
  private static int idGenerator = 0;

  public Customer(String firstName, String lastName) {
    this.id = idGenerator += 1;
    this.firstName = firstName;
    this.lastName = lastName;
    all.add(this);
    mapOfCustomers.put(this.firstName, this);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getId() {
    return id;
  }

  public static ArrayList<Customer> all() {
    return all;
  }

  public static Customer findByFirstName(String firstName) {
    Customer customer = mapOfCustomers.get(firstName);
    return customer;
  }

  public static void ppAll() {
    for (int i = 0; i < Customer.all().size(); i++) {
      Customer customer = Customer.all().get(i);
      System.out.println(
        customer.getId() +
        ". " +
        customer.getFirstName() +
        " " +
        customer.getLastName()
      );
    }
  }

  @Override
  public String toString() {
    return (
      "#<Customer: @id=" +
      id +
      " @firstName=" +
      "\"" +
      firstName +
      "\"" +
      " @lastName=" +
      "\"" +
      lastName +
      "\"" +
      ">"
    );
  }
}
