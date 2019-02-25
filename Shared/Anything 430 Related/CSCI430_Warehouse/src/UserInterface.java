import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCTS = 2;
  private static final int ADD_MANUFACTURERS = 3;
  private static final int SHOW_ALL_CLIENTS = 4;
  private static final int SHOW_ALL_PRODUCTS = 5;
  private static final int SHOW_ALL_MANUFACTURERS = 6;
  private static final int ADD_MAN_PRODUCT_RELATION = 7;
  private static final int REMOVE_MAN_PRODUCT_RELATION = 8;
  private static final int NEW_CLIENT_ORDER = 9;
  private static final int LIST_TRANSACTIONS_FOR_CLIENT = 10;
  private static final int NEW_MAN_ORDER = 11;
  private static final int SHOW_MAN_ORDERS = 12;
  private static final int RECEIVE_SHIPMENT = 13;
  private static final int FILL_CLIENT_ORDER = 14;
  private static final int HELP = 15;
  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      //retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(ADD_PRODUCTS + " to add products");
    System.out.println(ADD_MANUFACTURERS + " to add manufacturers");
    System.out.println(SHOW_ALL_CLIENTS + " to show a list of all Clients");
    System.out.println(SHOW_ALL_PRODUCTS + " to show a list of all Products");
    System.out.println(SHOW_ALL_MANUFACTURERS + " to show a list of all Manufacturers");
    System.out.println(ADD_MAN_PRODUCT_RELATION + " to assosciate a product and manufacturer");
    System.out.println(REMOVE_MAN_PRODUCT_RELATION + " to disassosciate a product and manufacturer");
    System.out.println(NEW_CLIENT_ORDER + " to enter a new client order");
    System.out.println(LIST_TRANSACTIONS_FOR_CLIENT + " to list transactions for a given client");
    System.out.println(NEW_MAN_ORDER + " to place a new manufacturer order");
    System.out.println(SHOW_MAN_ORDERS + " to list orders for a given manufacturer");
    System.out.println(RECEIVE_SHIPMENT + " to receive a shipment from a given manufacturer");
    System.out.println(FILL_CLIENT_ORDER + " to process an order from a client");
    System.out.println(HELP + " for help");
  }

  public void addClient() {
    String name = getToken("Enter client name");
    String address = getToken("Enter address");
    String phone = getToken("Enter phone");
    Client result;
    result = warehouse.addMember(name, address, phone);
    if (result == null) {
      System.out.println("Could not add client");
    }
    System.out.println(result);
  }

  public void addProducts() {
    Product result;
    do {
      String name = getToken("Enter Product Name");
      String description = getToken("Enter description");
      String Price = getToken("Enter Price");
      float sellPrice = new Float(Price);
      result = warehouse.addProduct(name, description, sellPrice);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Product could not be added");
      }
      if (!yesOrNo("Add more products?")) {
        break;
      }
    } while (true);
  }
  
  public void addManufacturers() {
    Manufacturer result;
    do {
      String name = getToken("Enter Manufacturer Name");
      result = warehouse.addManufacturer(name);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Manufacturer could not be added");
      }
      if (!yesOrNo("Add more manufacturers?")) {
        break;
      }
    } while (true);
  }
  
  public void addManProductRelation() {
      SupplierTerms result;
      do {
          String manId = getToken("Enter Manufacturer ID");
          String productId = getToken("Enter Product ID");
          String price = getToken("Enter Supply Cost");
          float supplyCost = new Float(price);
          result = warehouse.addTerms(manId, productId, supplyCost);
          if (result != null) {
              System.out.println(result);
          } else {
              System.out.println("Terms could not be added");
          }
          if (!yesOrNo("Add more terms?")) {
              break;
          }
      } while (true);
  }
  
  public void removeManProductRelation() {
      boolean result;
      do {
          String manId = getToken("Enter Manufacturer ID");
          String productId = getToken("Enter Product ID");
          result = warehouse.removeTerms(manId, productId);
          if (result) {
              System.out.println("Terms removed");
          } else {
              System.out.println("Terms could not be removed");
          }
          if (!yesOrNo("Remove more terms?")) {
              break;
          }
      } while (true);
  }
  public void newClientOrder()
  {
      ClientOrder result;
      String clientId = getToken("Enter Client ID");
      do {
          String productId = getToken("Enter Product ID");
          int quantity = Integer.parseInt(getToken("Enter Product Quantity"));
          Calendar date = Calendar.getInstance();
          result = warehouse.newClientOrder(clientId, productId, quantity, date);
          if (result != null) {
              System.out.println(result);
          } else {
              System.out.println("Order could not be placed");
          }
          if (!yesOrNo("Order more items for this user?")) {
              break;
          }
      } while (true);
  }
  public void showAllClientOrders() {
      String clientId = getToken("Enter Client ID");
      if(!warehouse.showAllClientOrders(clientId))
          System.out.println("Unable to find client");
  }
  public void newManufacturerOrder() {
      ManufacturerOrder result;
      String manId = getToken("Enter Manufacturer ID");
      do{
        String productId = getToken("Enter Product ID");
        int quantity = Integer.parseInt(getToken("Enter Product Quantity"));
        result = warehouse.newManufacturerOrder(manId, productId, quantity);
        if(result != null) {
            System.out.println(result);
        } else {
            System.out.println("Order could not be placed");
        }
        if(!yesOrNo("Place more orders with this manufacturer?")) {
            break;
        }
      } while(true);
  }
  public void showManOrders() {
      String manId = getToken("Enter Manufacturer ID");
      if(!warehouse.showAllManufacturerOrders(manId))
          System.out.println("Unable to find manufacturer");
  }
  public void receiveShipment() {
      String manId = getToken("Enter Manufacturer ID");
      if(!warehouse.showAllManufacturerOrders(manId))
          System.out.println("Unable to find manufacturer");
      
      String orderId = getToken("Enter the order ID of the shipment received");
    
      if(!warehouse.receiveShipment(manId, orderId))
          System.out.println("Unable to receive shipment");
  }
  public void processClientOrder() {
      String clientId = getToken("Enter Client ID");
      if(!warehouse.showAllClientOrders(clientId))
          System.out.println("Unable to find client");
      
      String orderId = getToken("Enter the order ID to be processes");
      
      if(!warehouse.processOrder(clientId, orderId))
          System.out.println("Unable to process order");
  }
  public void showAllClients() {
      warehouse.showAllClients();
  }
  
  public void showAllProducts() {
      warehouse.showAllProducts();
  }
  
  public void showAllManufacturers() {
      warehouse.showAllManufacturers();
  }
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:                    addClient();
                                            break;
        case ADD_PRODUCTS:                  addProducts();
                                            break;
        case ADD_MANUFACTURERS:             addManufacturers();
                                            break;
        case SHOW_ALL_CLIENTS:              showAllClients();
                                            break;
        case SHOW_ALL_PRODUCTS:             showAllProducts();
                                            break;
        case SHOW_ALL_MANUFACTURERS:        showAllManufacturers();
                                            break;
        case ADD_MAN_PRODUCT_RELATION:      addManProductRelation();
                                            break;
        case REMOVE_MAN_PRODUCT_RELATION:   removeManProductRelation();
                                            break;
        case NEW_CLIENT_ORDER:              newClientOrder();
                                            break;
        case LIST_TRANSACTIONS_FOR_CLIENT:  showAllClientOrders();
                                            break;
        case NEW_MAN_ORDER:                 newManufacturerOrder();
                                            break;
        case SHOW_MAN_ORDERS:               showManOrders();
                                            break;
        case RECEIVE_SHIPMENT:              receiveShipment();
                                            break;
        case FILL_CLIENT_ORDER:             processClientOrder();
                                            break;
        case HELP:                          help();
                                            break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}