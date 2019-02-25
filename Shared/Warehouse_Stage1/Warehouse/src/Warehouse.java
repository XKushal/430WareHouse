import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  public static final int BOOK_NOT_FOUND  = 1;
  public static final int BOOK_NOT_ISSUED  = 2;
  public static final int BOOK_HAS_HOLD  = 3;
  public static final int BOOK_ISSUED  = 4;
  public static final int HOLD_PLACED  = 5;
  public static final int NO_HOLD_FOUND  = 6;
  public static final int OPERATION_COMPLETED= 7;
  public static final int OPERATION_FAILED= 8;
  public static final int NO_SUCH_MEMBER = 9;
  private ProductList product;
  private ClientList memberList;
  private ManufacturerList manufacturerList;

  private static Warehouse warehouse;
  private Warehouse() {
    product = ProductList.instance();
    memberList = ClientList.instance();
    manufacturerList = ManufacturerList.instance();
  }
   
  public static Warehouse instance() {
    if (warehouse == null) {
      ClientIdServer.instance(); // instantiate all singletons
      ManufacturerIDServer.instance(); // instantiate all singletons
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
   
  public Product addBook(String title, String id) {
    Product book = new Product(title, id);
    if (product.insertBook(book)) {
      return (book);
    }
    return null;
  }
   
  public Client addMember(String name, String address, String phone) {
    Client member = new Client(name, address, phone);
    if (memberList.insertMember(member)) {
      return (member);
    }
    return null;
  }

  public Manufacturer addManufacturer(String name, String address, String phone) {
    Manufacturer manu = new Manufacturer(name, address, phone);
    if (manufacturerList.insertManufacturer(manu)) {
      return (manu);
    }
    return null;
  }

  public Iterator getBooks() {
      return product.getBooks();
  }

  public Iterator getMembers() {
      return memberList.getMembers();
  }
  
    public Iterator getManufacturers() {
      return manufacturerList.getManufacturers();
  }
  
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("LibraryData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
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
      FileOutputStream file = new FileOutputStream("LibraryData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIdServer.instance());
       output.writeObject(ManufacturerIDServer.instance());
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
   
  /*public String toString() {
    return catalog + "\n" + memberList;
  }
  * */
}
