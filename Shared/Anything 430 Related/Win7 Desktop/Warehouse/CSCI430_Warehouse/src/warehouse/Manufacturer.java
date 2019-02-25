package warehouse;
import java.util.*;
import java.io.*;
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private List supplierTerms = new LinkedList();
  private List pendingOrders = new LinkedList();
  private List filledOrders = new LinkedList();
  private static final String MANUFACTURER_STRING = "M";
  
  public Manufacturer (String name) {
    this.name = name;
    id = MANUFACTURER_STRING + (ManufacturerIdServer.instance()).getId();
  }
  public boolean addTerms(SupplierTerms terms)
  {
      if(supplierTerms.add(terms))
          return true;
      else return false;
  }
  
  public boolean placeManufacturerOrder(ManufacturerOrder manufacturerOrder) {
    if(pendingOrders.add(manufacturerOrder))
        return true;
    else return false;
  }
  
  public boolean removeTerms(String productId) {
    for (ListIterator iterator = supplierTerms.listIterator(); iterator.hasNext(); ) {
      SupplierTerms terms = (SupplierTerms) iterator.next();
      String prodId = terms.getProduct().getId();
      if (prodId.equals(productId)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }
  
  public Boolean removePendingOrder(ManufacturerOrder manufacturerOrder) {
    for (ListIterator iterator = pendingOrders.listIterator(); iterator.hasNext(); ) {
      ManufacturerOrder order = (ManufacturerOrder) iterator.next();
      if(order.equals(manufacturerOrder))
      {
          iterator.remove();
          return true;
      }
    }
    return false;
  }
  
  public boolean addFilledOrder(ManufacturerOrder manufacturerOrder) {
      filledOrders.add(manufacturerOrder);
      return true;
  }
  
  public ManufacturerOrder findPendingOrder(String orderId) {
    for (ListIterator iterator = pendingOrders.listIterator(); iterator.hasNext(); ) {
      ManufacturerOrder order = (ManufacturerOrder) iterator.next();
      if(order.getId().equals(orderId))
          return order;
    }
    return null;
  }
  public boolean producesProduct(Product product) {
      for (ListIterator iterator = supplierTerms.listIterator(); iterator.hasNext(); ) {
          SupplierTerms terms = (SupplierTerms) iterator.next();
          if(terms.getProduct() == product)
              return true;
      }
      return false;
  }
  
  public String getName() {
    return name;
  }
  public String getId() {
    return id;
  }
  public List getPendingOrders() {
      return pendingOrders;
  }
  public void setName(String newName) {
    name = newName;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    return "\nName " + name + "\tID " + id + "\nTerms: " + 
            supplierTerms.toString() + "\nPending Orders: " + 
            pendingOrders.toString()+ "\nFilled Orders: " + 
            filledOrders.toString();
  }
}

