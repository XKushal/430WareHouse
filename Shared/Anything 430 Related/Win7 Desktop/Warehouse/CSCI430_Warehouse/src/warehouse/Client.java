package warehouse;
import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private List pendingOrders = new LinkedList();
  private List filledOrders = new LinkedList();
  private List backOrders = new LinkedList();
  private static final String MEMBER_STRING = "C";
  
  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = MEMBER_STRING + (ClientIdServer.instance()).getId();
  }
  public boolean placeOrder(ClientOrder order)
  {
      if(pendingOrders.add(order))
          return true;
      else return false;
  }
  public ClientOrder findPendingOrder(String orderId) {
    for (ListIterator iterator = pendingOrders.listIterator(); iterator.hasNext(); ) {
      ClientOrder order = (ClientOrder) iterator.next();
      if(order.getId().equals(orderId))
          return order;
    }
    return null;
  }
  public Boolean removePendingOrder(ClientOrder clientOrder) {
    for (ListIterator iterator = pendingOrders.listIterator(); iterator.hasNext(); ) {
      ClientOrder order = (ClientOrder) iterator.next();
      if(order.equals(clientOrder))
      {
          iterator.remove();
          return true;
      }
    }
    return false;
  }
  public Boolean removeBackOrder(ClientOrder clientOrder) {
    for (ListIterator iterator = backOrders.listIterator(); iterator.hasNext(); ) {
      ClientOrder order = (ClientOrder) iterator.next();
      if(order.equals(clientOrder))
      {
          iterator.remove();
          return true;
      }
    }
    return false;
  }
  public boolean addFilledOrder(ClientOrder clientOrder) {
      filledOrders.add(clientOrder);
      return true;
  }
  public boolean addBackOrder(ClientOrder clientOrder) {
      backOrders.add(clientOrder);
      return true;
  }
  public String getName() {
      return name;
  }
  public String getPhone() {
      return phone;
  }
  public String getAddress() {
      return address;
  }
  public String getId() {
      return id;
  }
  public List getOrders() {
      return pendingOrders;
  }
  public List getBackorders() {
      return backOrders;
  }
  public void setName(String newName) {
      name = newName;
  }
  public void setAddress(String newAddress) {
      address = newAddress;
  }
  public void setPhone(String newPhone) {
      phone = newPhone;
  }
  public boolean equals(String id) {
      return this.id.equals(id);
  }
  public String toString() {
      String string = "\nName:" + name + "\tAddress: " + address + "\tID: " + id + "\tPhone: " + phone
              + "\nPending Orders: " + pendingOrders.toString() + "\nBackOrders: " + backOrders.toString() 
              + "\nFilled Orders: " + filledOrders.toString();
      return string;
  }
}