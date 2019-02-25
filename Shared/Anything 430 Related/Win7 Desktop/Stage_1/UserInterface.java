/*---------------------------------------------------------
/ CSCI 430                        Class Author: Jeff Okeefe
/ St. Cloud State University
/ Dr. Sarnath
/
/ Project 1 - UserInterface Class
/--------------------------------------------------------*/

import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int ADD_MANUFACTURER = 3;
  private static final int ADD_MANUFACTURER_PRODUCT = 4;
  private static final int DELETE_MANUFACTURER_PRODUCT = 5;
  private static final int ACCEPT_CUSTOMER_ORDER= 6;
  private static final int PROCESS_CUSTOMER_ORDER = 7;
  private static final int PLACE_ORDER_WITH_MANUFACTURER = 8;
  private static final int GENERATE_INVOICE = 9;
  private static final int ACCEPT_CUSTOMER_PAYMENT = 10;
  private static final int ACCEPT_SUPPLIER_SHIPMENT = 11;
  private static final int SAVE = 12;
  private static final int RETRIEVE = 13;
  private static final int SHOW_CLIENTS = 14;
  private static final int SHOW_PRODUCTS = 15;
  private static final int HELP = 16;
  
  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
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
    System.out.println("Enter a number between 0 and 16 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client.");
    System.out.println(ADD_PRODUCT + " to add a product.");
    System.out.println(ADD_MANUFACTURER + " to add a manufacturer.");
    System.out.println(ADD_MANUFACTURER_PRODUCT + " to add a manufacturer to the supplier list for a product.");
    System.out.println(DELETE_MANUFACTURER_PRODUCT + " to delete a manufacturer to the supplier list for a product.");
    System.out.println(ACCEPT_CUSTOMER_ORDER + " to accept customer order.");
    System.out.println(PROCESS_CUSTOMER_ORDER + " to process customer order.");
    System.out.println(PLACE_ORDER_WITH_MANUFACTURER + " to place an order with a manufacturer.");
    System.out.println(GENERATE_INVOICE + " to generate an invoice.");
    System.out.println(ACCEPT_CUSTOMER_PAYMENT + " to accept a customers payment.");
    System.out.println(ACCEPT_SUPPLIER_SHIPMENT + " to accept a supplier shipment.");
    System.out.println(SAVE + " to save the warehouse.");
    System.out.println(RETRIEVE + " to retrieve the warehouse.");
    System.out.println(SHOW_CLIENTS + " to show the clients.");
    System.out.println(SHOW_PRODUCTS + " to show the products.");
    System.out.println(HELP + " for help");
  }

  public void addClient() {
    String name = getToken("Enter member name");
    String address = getToken("Enter address");
    String phone = getToken("Enter phone");
    Client result;
    result = warehouse.addClient(name, address, phone);
    if (result == null) {
      System.out.println("Could not add member");
    }
    System.out.println(result);
  }

  public void addProduct() {
    Product result;
    do {
      String productName = getToken("Enter product name");
      String productId = getToken("Enter product id");
      String productQuantity = getToken("Enter product quantity");
      result = warehouse.addProduct(productName, productId, productQuantity);
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
  
  public void addManufacturer() {
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

  public void showProducts() {
      Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
	  Product product = (Product)(allProducts.next());
          System.out.println(product.toString());
      }
  }

  public void showClients() {
      Iterator allClients = warehouse.getClients();
      while (allClients.hasNext()){
	  Client client = (Client)(allClients.next());
          System.out.println(client.toString());
      }
  }

  public void addManufacturerProduct() {
      System.out.println("Adding manufacturer Product...");
  }
  public void deleteManufacturerProduct() {
      System.out.println("Deleting manufacturer Product...");   
  }
  public void acceptCustomerOrder() {
      System.out.println("Accepting a customer order...");   
  }
  public void processCustomerOrder() {
      System.out.println("Processing a customer order...");   
  }
  public void placeOrderWithManufacturer() {
      System.out.println("Placing an order with a manufacturer...");   
  }
  public void generateInvoice() {
      System.out.println("Generating an invoice...");   
  }
  public void acceptCustomerPayment() {
      System.out.println("Accepting a customers payment...");   
  }
  public void acceptSupplierShipment() {
      System.out.println("Accepting a suppliers shipment...");   
  }
  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData.\n" );
    } else {
      System.out.println(" There has been an error in saving the warehouse.\n" );
    }
  }
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:        			 addClient();
                                			 break;
        case ADD_PRODUCT:       			 addProduct();
                                			 break;
        case ADD_MANUFACTURER:  			 addManufacturer();
                                			 break;
        case ADD_MANUFACTURER_PRODUCT:			 addManufacturerProduct();
                                            		 break;
        case DELETE_MANUFACTURER_PRODUCT:   		 deleteManufacturerProduct();
                                			 break;
        case ACCEPT_CUSTOMER_ORDER:		       	 acceptCustomerOrder();
                                			 break;
        case PROCESS_CUSTOMER_ORDER:    	         processCustomerOrder();
                                			 break;
        case PLACE_ORDER_WITH_MANUFACTURER:  		 placeOrderWithManufacturer();
                                			 break;
        case GENERATE_INVOICE:      		 	 generateInvoice();
                                			 break;
        case ACCEPT_CUSTOMER_PAYMENT:	     		 acceptCustomerPayment();
                                			 break;
        case ACCEPT_SUPPLIER_SHIPMENT:	     	 	 acceptSupplierShipment();
		 				 	 break;
        case SAVE:              			 save();
                                			 break;
        case RETRIEVE:          			 retrieve();
                                			 break;
        case SHOW_CLIENTS:				 showClients();
                                			 break; 		
        case SHOW_PRODUCTS:				 showProducts();
                            				 break; 		
        case HELP:              			 help();
                                			 break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
