/*---------------------------------------------------------
/ CSCI 430                        Class Author: Andy Palmer
/ St. Cloud State University
/ Dr. Sarnath
/
/ Project 1 - Warehouse Class
/--------------------------------------------------------*/

import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
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
  private ProductList productList;
  private ClientList clientList;
  private ManufacturerList manufacturerList;
  private InvoiceList invoiceList;
  private static Warehouse warehouse;
  private Warehouse() {
    productList = ProductList.instance();
    clientList = ClientList.instance();
    manufacturerList = ManufacturerList.instance();
	invoiceList = InvoiceList.instance();
  }
  public static Warehouse instance() {
    if (warehouse == null) {
      ClientIdServer.instance(); // instantiate all singletons
      ManufacturerIdServer.instance(); // instantiate all singletons
	  InvoiceIdServer.instance();
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  public Product addProduct(String name, String id, int quantity,int price) {
    Product product = new Product(name, id, quantity, price);
    if (productList.insertProduct(product)) { 
      return (product);
    }
    return null;
  }
  public Client addClient(String name, String address, String phone) {
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
  public String createInvoice(){
	Invoice invoice = new Invoice();
	if(invoiceList.insertInvoice(invoice)) {
		return (invoice.getId());
	}
	return null;
  }
  public Iterator getManufacturers() {
      return manufacturerList.getManufacturers();
  }  
  public Iterator getProducts() {
      return productList.getProducts();
  }

  public Iterator getClients() {
      return clientList.getClients();
  }
  public Iterator getInvoices() {
      return invoiceList.getInvoices();
  }
  public Client searchClient(String clientId) {
        return clientList.search(clientId);
  }
  public Product searchProduct(String productId) {
        return productList.search(productId);
  }
  public Invoice searchInvoice(String invoiceId) {
        return invoiceList.search(invoiceId);
  }
  public Invoice processCustomerOrder(String invoiceId) {
	Invoice invoice = invoiceList.search(invoiceId);
	if (invoice == null) {
		return null;
	}
	for(int k = 1; k <= invoice.getI(); k++) {
		String productId = invoice.getPid(k);
		Product product = productList.search(productId);
		if (product.getQuantity() >= invoice.getQuantity(k)) {
			int newQuantity = product.getQuantity() - invoice.getQuantity(k);
			product.setQuantity(newQuantity);
			invoice.setShipped(k);
			return(invoice);
		}
		else{
			int remainingQuantity = invoice.getQuantity(k) - product.getQuantity();
			product.setQuantity(0);
			Waitlist waitlist = new Waitlist(product, remainingQuantity);
			product.waitlistItem(waitlist);
			return(invoice);
		}
	}
  return(invoice);
  }
  public Invoice acceptCustomerOrder(String clientId, String invoiceId, String productId, int quantity, int i) {
	Invoice invoice = invoiceList.search(invoiceId);
	if (invoice == null) {
		return null;  
	}
	Product product = productList.search(productId);
	if (product == null) {
		return null;
	}
	Client client = clientList.search(clientId);
	int price = product.getPrice();
	invoice.acceptCustomerOrder(productId, quantity, price, i);
	client.setBalance(price*quantity);
	return(invoice);
  }
  public int getBalance(String clientId){
	Client client = clientList.search(clientId);
	if (client == null){
	  return 0;
	}
	return(client.getBalance());
  }
  public Client acceptCustomerPayment(String clientId, int payment){
	Client client = clientList.search(clientId);
	if (client == null) {
	  return(null);
	}
	client.acceptPayment(payment);
	return client;
  }
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
	  InvoiceIdServer.retrieve(input);
      return warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }

  public static  boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIdServer.instance());
	  output.writeObject(InvoiceIdServer.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (warehouse == null) {
        warehouse = (Warehouse) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public String toString() {
    return productList + "\n" + clientList + "\n" + manufacturerList;
  }
}
