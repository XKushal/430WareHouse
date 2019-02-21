
import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int MANUFACTURER_NOT_FOUND = 2;
  private ManufacturerList manufacturerList;
  private static Warehouse warehouse;

  private Warehouse() {
    manufacturerList = ManufacturerList.instance();
  }
  public static Warehouse instance() {
    if (warehouse == null) {
      ManufacturerIdServer.instance(); // instantiate all singletons
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }

  public Manufacturer addManufacturer(String name, String phone, String address){
    Manufacturer manufacturer = new Manufacturer(name, phone, address);
    if (manufacturerList.insertManufacturer(manufacturer)){
      return (manufacturer);
    }else{
      return null;
    }
  }

  public Iterator <Manufacturer> getManufacturers() {
      return manufacturerList.getManufacturers();
  }

  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ManufacturerIdServer.retrieve(input);
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
      output.writeObject(ManufacturerIdServer.instance());
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
    return manufacturerList.toString();
  }
}

