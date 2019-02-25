import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int PRODUCT_NOT_FOUND  = 1;
  public static final int MANUFACTURER_NOT_FOUND  = 2;
  public static final int TERMS_ADDED  = 3;
  public static final int UNABLE_TO_ADD_TERMS  = 4;
  public static final int HOLD_PLACED  = 5;
  public static final int NO_HOLD_FOUND  = 6;
  public static final int OPERATION_COMPLETED= 7;
  public static final int OPERATION_FAILED= 8;
  public static final int NO_SUCH_MEMBER = 9;
  private ProductList productList;
  private ClientList clientList;
  private ManufacturerList manufacturerList;
  private static Warehouse warehouse;
  private Warehouse() {
    productList = ProductList.instance();
    clientList = ClientList.instance();
    manufacturerList = ManufacturerList.instance();
  }
  public static Warehouse instance() {
    if (warehouse == null) {
      ClientIdServer.instance(); // instantiate all singletons
      ClientOrderIdServer.instance(); // instantiate all singletons
      ProductIdServer.instance(); // instantiate all singletons
      ManufacturerIdServer.instance(); // instantiate all singletons
      ManufacturerOrderIdServer.instance(); // instantiate all singletons
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  public Product addProduct(String name, String description, float sellPrice) {
    Product product = new Product(name, description, sellPrice);
    if (productList.addProduct(product)) {
      return (product);
    }
    return null;
  }
  public Client addMember(String name, String address, String phone) {
    Client client = new Client(name, address, phone);
    if (clientList.insertClient(client)) {
      return (client);
    }
    return null;
  }
  public Manufacturer addManufacturer(String name) {
      Manufacturer manufacturer = new Manufacturer(name);
      if (manufacturerList.insertManufacturer(manufacturer)) {
          return (manufacturer);
      }
      return null;
  }
  public SupplierTerms addTerms(String manId, String productId, float supplyCost) {
      Manufacturer manufacturer = manufacturerList.search(manId);
      if(manufacturer == null)
          return null;
          //return(MANUFACTURER_NOT_FOUND);
      
      Product product = productList.search(productId);
      if(product == null)
          return null;
          //return(PRODUCT_NOT_FOUND);
      
      SupplierTerms terms = new SupplierTerms(manufacturer, product, supplyCost);
      if(manufacturer.addTerms(terms) && product.addTerms(terms)) {
          return terms;
          //return(TERMS_ADDED);
      }
      return null;
      //return(UNABLE_TO_ADD_TERMS);
  }
  public boolean removeTerms(String manId, String productId) {
      Manufacturer manufacturer = manufacturerList.search(manId);
      if(manufacturer == null)
          return false;
          //return(MANUFACTURER_NOT_FOUND);
          
      Product product = productList.search(productId);
      if(product == null)
          return false;
          //return(PRODUCT_NOT_FOUND);
      
      if(manufacturer.removeTerms(productId) && product.removeTerms(manId))
          return true;
      return false;
  }
  public ClientOrder newClientOrder(String clientId, String productId, int quantity, Calendar date) {
      Client client = clientList.search(clientId);
      if(client == null)
          return null;
      Product product = productList.search(productId);
      if(product == null)
          return null;
      
      ClientOrder order = new ClientOrder(client, product, quantity, date);
      if(client.placeOrder(order) && product.placeOrder(order)) {
          return order;
      }
      return null;
  }
  public ManufacturerOrder newManufacturerOrder(String manId, String productId, int quantity) {
      Manufacturer manufacturer = manufacturerList.search(manId);
      if(manufacturer == null)
          return null;
      
      Product product = productList.search(productId);
      if(product == null)
          return null;
      
      ManufacturerOrder order = new ManufacturerOrder(manufacturer, product, quantity);
      if(manufacturer.placeManufacturerOrder(order)) {
          return order;
      }
      return null;
  }
  public Boolean showAllClientOrders(String clientId) {
      Client client = clientList.search(clientId);
      if(client == null)
          return false;
      
      System.out.println(client.getOrders().toString());
      return true;
  }
  public Boolean showAllManufacturerOrders(String manId) {
      Manufacturer manufacturer = manufacturerList.search(manId);
      if(manufacturer == null)
          return null;
      
      System.out.println(manufacturer.getOrders().toString());
      return true;
  }
  public Boolean receiveShipment(String manId, String orderId) {
      Manufacturer manufacturer = manufacturerList.search(manId);
      if(manufacturer == null)
          return null;
      
      ManufacturerOrder manOrder = manufacturer.findPendingOrder(orderId);
      if(manOrder == null)
          return null;
      
      Product product = manOrder.getProduct();
      if(product == null)
          return null;
      
      if(!manufacturer.removePendingOrder(manOrder))
          return null;
      if(!manufacturer.addFilledOrder(manOrder))
          return null;
      
      int quantity = manOrder.getQuantity();
      product.addInventory(quantity);
      
      List backOrders = product.getBackorders();
      for (ListIterator iterator = backOrders.listIterator(); iterator.hasNext(); ) {
          ClientOrder order = (ClientOrder) iterator.next();
          if(product.getQuantity() >= order.getBackQuantity())  {
              product.setQuantity(product.getQuantity() - order.getBackQuantity());
              order.setBackQuantity(0);
            if(!order.getClient().removeBackOrder(order))
                return null;
            if(!order.getClient().addFilledOrder(order))
                return null;
          } else {
              order.setBackQuantity(order.getBackQuantity() - product.getQuantity());
              product.setQuantity(0);
          }
      }
      return true;
  }
  public Boolean processOrder(String clientId, String orderId) {
      Client client = clientList.search(clientId);
      if(client == null)
          return false;
      
      ClientOrder order = client.findPendingOrder(orderId);
      if(order == null)
          return null;
      
      Product product = order.getProduct();
      if(product == null)
          return null;
      
      //If we have enough product
      if(product.getQuantity() >= order.getQuantity())  {
          product.setQuantity(product.getQuantity() - order.getQuantity());
          //Move the order to the "fulfilled" list/delete from pending list
          if(!client.removePendingOrder(order))
              return null;
          if(!client.addFilledOrder(order))
              return null;
      } else {  //If we don't
          //Set backorder quanitity to ordersize - inventory in stock
          order.setBackQuantity(order.getQuantity() - product.getQuantity());
          
          //Add order to backorder list
          if(!client.addBackOrder(order))
              return null;
          
          //Set inventory to 0
          product.setQuantity(0);
          
          //Remove order from pending list
          if(!client.removePendingOrder(order))
              return null;
      }
      return true;
  }
  public void showAllClients() {
      System.out.println(clientList.toString());
  }
  public void showAllProducts() {
      System.out.println(productList.toString());
  }
  public void showAllManufacturers() {
      System.out.println(manufacturerList.toString());
  }
//  public int removeHold(String memberId, String bookId) {
//    Client client = clientList.search(memberId);
//    if (client == null) {
//      return (NO_SUCH_MEMBER);
//    }
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return(PRODUCT_NOT_FOUND);
//    }
//    return client.removeHold(bookId) && product.removeHold(memberId)? OPERATION_COMPLETED: NO_HOLD_FOUND;
//  }
//  public void removeInvalidHolds() {
//    for (Iterator catalogIterator = productList.getBooks(); catalogIterator.hasNext(); ) {
//      for (Iterator iterator = ((Product) catalogIterator.next()).getHolds(); iterator.hasNext(); ) {
//        Manufacturer hold = (Manufacturer) iterator.next();
//        if (!hold.isValid()) {
//          hold.getBook().removeHold(hold.getMember().getId());
//          hold.getMember().removeHold(hold.getBook().getId());
//        }
//      }
//    }
//  }
//  public int placeHold(String memberId, String bookId, int duration) {
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return(PRODUCT_NOT_FOUND);
//    }
//    if (product.getBorrower() == null) {
//      return(MANUFACTURER_NOT_FOUND);
//    }
//    Client client = clientList.search(memberId);
//    if (client == null) {
//      return(NO_SUCH_MEMBER);
//    }
//    Manufacturer hold = new Manufacturer(client, product, duration);
//    product.placeHold(hold);
//    client.placeHold(hold);
//    return(HOLD_PLACED);
//  }
//  public Client searchMembership(String memberId) {
//    return clientList.search(memberId);
//  }
//  public Client processHold(String bookId) {
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return (null);
//    }
//    Manufacturer hold = product.getNextHold();
//    if (hold == null) {
//      return (null);
//    }
//    hold.getMember().removeHold(bookId);
//    hold.getBook().removeHold(hold.getMember().getId());
//    return (hold.getMember());
//  }
//  public Product issueBook(String memberId, String bookId) {
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return(null);
//    }
//    if (product.getBorrower() != null) {
//      return(null);
//    }
//    Client client = clientList.search(memberId);
//    if (client == null) {
//      return(null);
//    }
//    if (!(product.issue(client) && client.issue(product))) {
//      return null;
//    }
//    return(product);
//  }
//  public Product renewBook(String bookId, String memberId) {
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return(null);
//    }
//    Client client = clientList.search(memberId);
//    if (client == null) {
//      return(null);
//    }
//    if ((product.renew(client) && client.renew(product))) {
//      return(product);
//    }
//    return(null);
//  }
//  public Iterator getBooks(String memberId) {
//    Client client = clientList.search(memberId);
//    if (client == null) {
//      return(null);
//    } else {
//      return (client.getBooksIssued());
//    }
//  }
//  public int removeBook(String bookId) {
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return(PRODUCT_NOT_FOUND);
//    }
//    if (product.hasHold()) {
//      return(TERMS_ADDED);
//    }
//    if ( product.getBorrower() != null) {
//      return(UNABLE_TO_ADD_TERMS);
//    }
//    if (productList.removeBook(bookId)) {
//      return (OPERATION_COMPLETED);
//    }
//    return (OPERATION_FAILED);
//  }
//  public int returnBook(String bookId) {
//    Product product = productList.search(bookId);
//    if (product == null) {
//      return(PRODUCT_NOT_FOUND);
//    }
//    Client client = product.returnBook();
//    if (client == null) {
//      return(MANUFACTURER_NOT_FOUND);
//    }
//    if (!(client.returnBook(product))) {
//      return(OPERATION_FAILED);
//    }
//    if (product.hasHold()) {
//      return(TERMS_ADDED);
//    }
//    return(OPERATION_COMPLETED);
//  }
//  public Iterator getTransactions(String memberId, Calendar date) {
//    Client client = clientList.search(memberId);
//    if (client == null) {
//      return(null);
//    }
//    return client.getTransactions(date);
//  }
//  public static Warehouse retrieve() {
//    try {
//      FileInputStream file = new FileInputStream("LibraryData");
//      ObjectInputStream input = new ObjectInputStream(file);
//      input.readObject();
//      ClientIdServer.retrieve(input);
//      return warehouse;
//    } catch(IOException ioe) {
//      ioe.printStackTrace();
//      return null;
//    } catch(ClassNotFoundException cnfe) {
//      cnfe.printStackTrace();
//      return null;
//    }
//  }
//  public static  boolean save() {
//    try {
//      FileOutputStream file = new FileOutputStream("LibraryData");
//      ObjectOutputStream output = new ObjectOutputStream(file);
//      output.writeObject(warehouse);
//      output.writeObject(ClientIdServer.instance());
//      return true;
//    } catch(IOException ioe) {
//      ioe.printStackTrace();
//      return false;
//    }
//  }
//  private void writeObject(java.io.ObjectOutputStream output) {
//    try {
//      output.defaultWriteObject();
//      output.writeObject(warehouse);
//    } catch(IOException ioe) {
//      System.out.println(ioe);
//    }
//  }
//  private void readObject(java.io.ObjectInputStream input) {
//    try {
//      input.defaultReadObject();
//      if (warehouse == null) {
//        warehouse = (Warehouse) input.readObject();
//      } else {
//        input.readObject();
//      }
//    } catch(IOException ioe) {
//      ioe.printStackTrace();
//    } catch(Exception e) {
//      e.printStackTrace();
//    }
//  }
//  public String toString() {
//    return productList + "\n" + clientList;
//  }
}