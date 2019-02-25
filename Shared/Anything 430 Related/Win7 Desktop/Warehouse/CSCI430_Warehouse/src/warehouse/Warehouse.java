package warehouse;
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
  public static Warehouse getInstance() {
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
  public static void setInstance(Warehouse house) {
      Warehouse.warehouse = house;
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
  public String newClientOrder(String clientId, String productId, int quantity, Calendar date) {
      Client client = clientList.search(clientId);
      if(client == null)
          return null;
      Product product = productList.search(productId);
      if(product == null)
          return null;
      
      ClientOrder order = new ClientOrder(client, product, quantity, date);
      if(client.placeOrder(order) && product.placeOrder(order)) {
          return order.toString();
      }
      return null;
  }
  public ManufacturerOrder newManufacturerOrder(String manId, String productId, int quantity) {
      Manufacturer manufacturer = manufacturerList.search(manId);   //Find the manufacturer
      if(manufacturer == null)
          return null;
      
      Product product = productList.search(productId);  //Find the product
      if(product == null)
          return null;
      
      //Check if this manufacturer has terms with this product
      if(!product.isProducedByMan(manufacturer))
          return null;
      if(!manufacturer.producesProduct(product))
          return null;
      
      ManufacturerOrder order = new ManufacturerOrder(manufacturer, product, quantity); //Create new order object
      if(manufacturer.placeManufacturerOrder(order) && product.placeManufacturerOrder(order)) {
          return order;
      }
      return null;
  }
  public String showAllClientOrders(String clientId) {
      Client client = clientList.search(clientId);
      if(client == null)
          return null;
      else
        return client.getOrders().toString();
  }
  public String showAllManufacturerOrders(String manId) {
      Manufacturer manufacturer = manufacturerList.search(manId);
      if(manufacturer == null)
          return null;
      else
          return manufacturer.getPendingOrders().toString();
  }
  public List receiveShipment(String manId, String orderId) {
      Manufacturer manufacturer = manufacturerList.search(manId);   //Validate and find manufacturer
      if(manufacturer == null)
          return null;
      
      ManufacturerOrder manOrder1 = manufacturer.findPendingOrder(orderId);
      if(manOrder1 == null)
          return null;
      
      Product product = manOrder1.getProduct();
      if(product == null)
          return null;
      
      ManufacturerOrder manOrder2 = product.findPendingManOrder(orderId);
      if(manOrder2 == null)
          return null;
      
      ManufacturerOrder manOrder;
      
      if(manOrder1.equals(manOrder2))
          manOrder = manOrder1;
      else
          return null;
      
      if(!manufacturer.removePendingOrder(manOrder))
          return null;
      if(!product.removePendingManOrder(manOrder))
          return null;
      if(!manufacturer.addFilledOrder(manOrder))
          return null;
      if(!product.addFilledManOrder(manOrder))
          return null;
      
      int quantity = manOrder.getQuantity();
      product.addInventory(quantity);
      
      return product.getBackorders();
  }
  public Boolean processBackOrder (ClientOrder order) { //Return true if there is no stock remaining
      Product product = order.getProduct();
      if(product.getQuantity() >= order.getBackQuantity())  {
        product.setQuantity(product.getQuantity() - order.getBackQuantity());
        order.setBackQuantity(0);
        order.getClient().removeBackOrder(order);
        order.getClient().addFilledOrder(order);
        return false;
      } 
      else {
        order.setBackQuantity(order.getBackQuantity() - product.getQuantity());
        product.setQuantity(0);
        return true;
      }  
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
  public String showAllClientsWithBackorders() {
      String result = "";
      List clients = clientList.getAllClients();
      for (ListIterator iterator = clients.listIterator(); iterator.hasNext(); ) {
          Client client = (Client) iterator.next();
          if(!client.getBackorders().isEmpty())
              result.concat(client.toString());
      }
      return result;
  }
  public Boolean showOutstandingManOrders(String productId) {
      Product product = productList.search(productId);
      if(product == null)
          return false;
      
      System.out.println(product.getPendingOrders().toString());
      
      return true;
  }
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
      ManufacturerIdServer.retrieve(input);
      ProductIdServer.retrieve(input);
      ClientOrderIdServer.retrieve(input);
      ManufacturerOrderIdServer.retrieve(input);
      return warehouse;
    } catch(Exception e) {
      return null;
    }
  }
  public static boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIdServer.instance());
      output.writeObject(ManufacturerIdServer.instance());
      output.writeObject(ProductIdServer.instance());
      output.writeObject(ClientOrderIdServer.instance());
      output.writeObject(ManufacturerOrderIdServer.instance());
      return true;
    } catch(Exception e) {
      return false;
    }
  }

    public Product searchProduct(String id) {
        Product product = productList.search(id);
        if(product == null)
            return null;
        else
            return product;
        
    }
    public Client searchClient(String id) {
        Client client = clientList.search(id);
        if(client == null)
            return null;
        else
            return client;
    }
    public Manufacturer searchManufacturer(String id) {
        Manufacturer manufacturer = manufacturerList.search(id);
        if(manufacturer == null)
            return null;
        else
            return manufacturer;
    }
    public Iterator getAllProducts() {
        return productList.getProducts();
    }
    public Iterator getAllClients() {
        return clientList.getClients();
    }
    public Iterator getAllManufacturers() {
        return manufacturerList.getManufacturers();
    }
}