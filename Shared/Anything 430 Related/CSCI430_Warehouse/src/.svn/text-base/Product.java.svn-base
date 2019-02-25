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
  private List orders = new LinkedList();
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
  public boolean placeOrder(ClientOrder order)
  {
      if(orders.add(order))
          return true;
      else return false;
  }
  public void addInventory(int quantity) {
      this.quantity += quantity;
  }
  public List getBackorders() {
      List backOrders = new LinkedList();
      for (ListIterator iterator = orders.listIterator(); iterator.hasNext(); ) {
          ClientOrder order = (ClientOrder) iterator.next();
          if(order.getBackQuantity() > 0)
              backOrders.add(order);
      }
      return backOrders;
  }
  public String getId(){return id;}
  
  public String getName(){return name;}
  
  public String getDescription()    {
    return description;
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
  
    @Override
  public String toString() {
    return "\nID: " + id + "\tName: " + name + "\tDescription: " + description + 
            "\tSell Price: " + sellPrice + "\tQuantity: " + quantity + "\nTerms: " + supplierTerms.toString()
            + "\tOrders: " + orders.toString();
  }
  
  /*
  public boolean issue(Member member) {
    borrowedBy = member;
    dueDate = new GregorianCalendar();
    dueDate.setTimeInMillis(System.currentTimeMillis());
    dueDate.add(Calendar.MONTH, 1);
    return true;
  }
  
  public Member returnBook() {
    if (borrowedBy == null) {
      return null;
    } else {
      Member borrower = borrowedBy;
      borrowedBy = null;
      return borrower;
    }
  }
  public boolean renew(Member member) {
    if (hasHold()) {
      return false;
    }
    if ((member.getId()).equals(borrowedBy.getId())) {
      return (issue(member));
    }
    return false;
  }
  public void placeHold(Hold hold) {
    holds.add(hold);
  }
  public boolean removeHold(String memberId) {
    for (ListIterator iterator = holds.listIterator(); iterator.hasNext(); ) {
      Hold hold = (Hold) iterator.next();
      String id = hold.getMember().getId();
      if (id.equals(memberId)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }
  public Hold getNextHold() {
    for (ListIterator iterator = holds.listIterator(); iterator.hasNext(); ) {
      Hold hold = (Hold) iterator.next();
      iterator.remove();
      if (hold.isValid()) {
        return hold;
      }
    }
    return null;
  }
  public boolean hasHold() {
    ListIterator iterator = holds.listIterator();
    if (iterator.hasNext()) {
      return true;
    }
    return false;
  }
  
  * 
  */
}