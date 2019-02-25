/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warehouse;

import java.util.*;
import java.io.*;
/**
 *
 * @author 21cha
 */
public class Warehouse implements Serializable{
  private static final long serialVersionUID = 1L;
  public static final int MANUFACTURER_NOT_FOUND = 2;
  public static final int PRODUCT_NOT_FOUND = 3;
  public static final int CLIENT_NOT_FOUND = 4;
  public static final int NO_SUCH_PRODUCT = 5;
  public static final int NO_SUCH_MANUFACTURER = 6;
  public static final int NO_SUCH_CLIENT = 7;
    
  private ProductList productlist;
  private ManufacturerList manufacturerlist;
  private ClientList clientlist;
  private static Warehouse warehouse;
  private Warehouse() {
    productlist = ProductList.instance();
    manufacturerlist = ManufacturerList.instance();
    clientlist = ClientList.instance();
  }
  
  public static Warehouse instance() {
    if (warehouse == null) {
      ClientIDServer.instance();
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  public Product addProduct(String name,int quantity ) {
    Product product = new Product(name, quantity);
    if (productlist.insertProduct(product)) {
      return (product);
    }
    return null;
  }
  
  public Manufacturer addManufacturer(String name, String phone, String address){
    Manufacturer manufacturer = new Manufacturer(name, phone, address);
    if (manufacturerlist.insertManufacturer(manufacturer)){
      return (manufacturer);
    }else{
      return null;
    }
  }

  public Client addClient(String name, String address, String phone) {
    Client client = new Client(name, address, phone);
    if (clientlist.insertClient(client)) {
      return (client);
    }
    return null;
  }
  
  public Product assignProdToManufacturer(String productID, String manufacturerID, Float price)
  {
    Product product = productlist.search(productID);
    if (product == null)
    {
      return null;
    }

    Manufacturer manufacturer = manufacturerlist.search(manufacturerID);
    if (manufacturer == null)
    {
      return null;
    }

    int location = 0;
    location = product.SearchSupplylist(manufacturer);
    if (location != -1)
    {
      return null;
    }

    boolean success = product.link(manufacturer);
    success = product.addPrice(price);
    success = manufacturer.assignProduct(product);
    if (success){
      return product;
    } else{
      return null;
    }
  }

  public Product unassignProdFromManufacturer(String productID, String manufacturerID)
  {
    Product product = productlist.search(productID);
    if (product == null)
    {
      return null;
    }

    Manufacturer manufacturer = manufacturerlist.search(manufacturerID);
    if (manufacturer == null)
    {
      return null;
    }

    int location = 0;
    location = product.SearchSupplylist(manufacturer);
    if (location == -1)
    {
      System.out.println("Product already isn't assigned to this manufacturer.");
      return null;
    }

    boolean success = product.unlink(manufacturer);
    success = product.removePrice(location);
    success = manufacturer.removeProduct(product);
    if (success){
      return product;
    } else{
      System.out.println("Error 4");
      return null;
    }
  }
  
  public Product searchProduct(String productID)
  {
    return productlist.search(productID);
  }

  public Manufacturer searchManufacturer(String manufacturerID)
  {
    return manufacturerlist.search(manufacturerID);
  }


  public Iterator getProduct() {
      return productlist.getProducts();
  }
  
  public Iterator getManufacturer() {
      return manufacturerlist.getManufacturers();
  }

  public Iterator getClient() {
      return clientlist.getClient();
  }
  
  public Iterator<Manufacturer> getSuppliersOfProduct(Product p)
  {
    return p.getManufacturer();
  }

  public Iterator<Product> getProductsByManufacturer(Manufacturer m)
  {
    return m.getProducts();
  }

  public Iterator<Float> getProductPrices(Product p)
  {
    return p.getPrices();
  }
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIDServer.retrieve(input);
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
      output.writeObject(ClientIDServer.instance());
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
    return productlist + "\n" + manufacturerlist + "\n" + clientlist;
  }
}
    

