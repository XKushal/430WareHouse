package warehouse;
import java.util.*;
import java.lang.*;
import java.io.*;
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String id;
  private String name;
  private String description;
  private float sellPrice;
  private int quantity;
  private List supplierTerms = new LinkedList();
  private List clientOrders = new LinkedList();
  private List pendingManOrders = new LinkedList();
  private List filledManOrders = new LinkedList();
  private static final String PRODUCT_STRING = "P";

  public Product(String name, String description, float sellPrice) {
    this.name = name;
    this.description = description;
    this.sellPrice = sellPrice;
    this.quantity = 0;
    id = PRODUCT_STRING + (ProductIdServer.instance()).getId();
  }
  public boolean addTerms(SupplierTerms terms)
  {
      if(supplierTerms.add(terms))
          return true;
      else return false;
  }
  public boolean removeTerms(String manufacturerId) {
    for (ListIterator iterator = supplierTerms.listIterator(); iterator.hasNext(); ) {
      SupplierTerms terms = (SupplierTerms) iterator.next();
      String manId = terms.getManufacturer().getId();
      if (manId.equals(manufacturerId)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }
  public Boolean removePendingManOrder(ManufacturerOrder manufacturerOrder) {
    for (ListIterator iterator = pendingManOrders.listIterator(); iterator.hasNext(); ) {
      ManufacturerOrder order = (ManufacturerOrder) iterator.next();
      if(order.equals(manufacturerOrder))
      {
          iterator.remove();
          return true;
      }
    }
    return false;
  }
  
  public ManufacturerOrder findPendingManOrder(String orderId) {
    for (ListIterator iterator = pendingManOrders.listIterator(); iterator.hasNext(); ) {
      ManufacturerOrder order = (ManufacturerOrder) iterator.next();
      if(order.getId().equals(orderId))
          return order;
    }
    return null;
  }
  
  public boolean addFilledManOrder(ManufacturerOrder manufacturerOrder) {
      filledManOrders.add(manufacturerOrder);
      return true;
  }
          
  public boolean placeOrder(ClientOrder order)
  {
      if(clientOrders.add(order))
          return true;
      else return false;
  }
  public void addInventory(int quantity) {
      this.quantity += quantity;
  }
  public List getBackorders() {
      List backOrders = new LinkedList();
      for (ListIterator iterator = clientOrders.listIterator(); iterator.hasNext(); ) {
          ClientOrder order = (ClientOrder) iterator.next();
          if(order.getBackQuantity() > 0)
              backOrders.add(order);
      }
      return backOrders;
  }
  public boolean placeManufacturerOrder(ManufacturerOrder manufacturerOrder) {
    pendingManOrders.add(manufacturerOrder);
    return true;
  }
  public boolean isProducedByMan(Manufacturer manufacturer) {
      for (ListIterator iterator = supplierTerms.listIterator(); iterator.hasNext(); ) {
          SupplierTerms terms = (SupplierTerms) iterator.next();
          if(terms.getManufacturer() == manufacturer)
              return true;
      }
      return false;
  }
  public String getId(){return id;}
  
  public String getName(){return name;}
  
  public String getDescription()    {
    return description;
  }
  public List getPendingOrders() {
      return pendingManOrders;
  }
  public float getSellPrice()   {
      return sellPrice;
  }
  public int getQuantity() {
      return quantity;
  }
  public void setQuantity(int newQuantity) {
      this.quantity = newQuantity;
  }
  public List getTerms() {
      return supplierTerms;
  }
  
  public String toString() {
    return "\nID: " + id + "\tName: " + name + "\tDescription: " + description + 
            "\tSell Price: " + sellPrice + "\tQuantity: " + quantity + "\nTerms: " + supplierTerms.toString()
            + "\nClent Orders: " + clientOrders.toString() + "\nPending Manufacturer Orders: " + pendingManOrders.toString()
            + "\nFilled Manufacturer Orders: " + filledManOrders.toString();
  }
}