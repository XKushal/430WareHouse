import java.util.*;
import java.io.*;
public class Warehouse implements Serializable{
  private static final long serialVersionUID = 1L;
  public static final int PRODUCT_NOT_FOUND = 1;
  public static final int MANUFACTURER_NOT_FOUND = 2;
  public static final int CLIENT_NOT_FOUND = 3;

  private ManufacturerList manufacturerList;
  private ProductList productList;
  private ClientList clientList;

  private static Warehouse warehouse;

  private Warehouse()
  {
    manufacturerList = ManufacturerList.instance();
    clientList       = ClientList.instance();
    productList      = ProductList.instance();
  }

  public static Warehouse instance()
  {
    if (warehouse == null)
    {
      ManufacturerIDServer.instance();
      ProductIDServer.instance();
      ClientIDServer.instance();
      return (warehouse = new Warehouse());
    }
    else {
      return warehouse;
    }
  }

  public Client addClient(String Name, String Phone, String Address, String Billing)
  {
    Client client = new Client(Name, Phone, Address, Billing);
    if (clientList.insertClient(client))
    {
      return (client);
    }
    else
    {
      return null;
    }
  }

  public Product addProduct(String Name, int Quantity)
  {
    Product product = new Product(Name, Quantity);
    if (productList.insertProduct(product))
    {
      return (product);
    }
    else
    {
      return null;
    }

  }

  public Manufacturer addManufacturer(String Name, String Phone, String Address)
  {
    Manufacturer manufacturer = new Manufacturer(Name, Phone, Address);
    if (manufacturerList.insertManufacturer(manufacturer))
    {
      return (manufacturer);
    }
    else
    {
      return null;
    }
  }

  public Iterator<Client> getClients()
  {
    return clientList.getClients();
  }

  public Iterator<Product> getProducts()
  {
    return productList.getProducts();
  }

  public Iterator<Manufacturer> getManufacturers()
  {
    return manufacturerList.getManufacturers();
  }

  public Product assignProdToManufacturer(String pID, String mID, Double price)
  {
    Product product = productList.search(pID);
    if (product == null)
    {
      return null;
    }

    Manufacturer manufacturer = manufacturerList.search(mID);
    if (manufacturer == null)
    {
      return null;
    }

    int location = 0;
    location = product.SearchSuppList(manufacturer);
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

  public Product unassignProdFromManufacturer(String pID, String mID)
  {
    Product product = productList.search(pID);
    if (product == null)
    {
      return null;
    }

    Manufacturer manufacturer = manufacturerList.search(mID);
    if (manufacturer == null)
    {
      return null;
    }

    int location = 0;
    location = product.SearchSuppList(manufacturer);
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

  public Product searchProduct(String pID)
  {
    return productList.search(pID);
  }

  public Manufacturer searchManufacturer(String mID)
  {
    return manufacturerList.search(mID);
  }

  public Iterator<Manufacturer> getSuppliersOfProduct(Product p)
  {
    return p.getManufacturer();
  }

  public Iterator<Product> getProductsByManufacturer(Manufacturer m)
  {
    return m.getProducts();
  }

  public Iterator<Double> getProductPrices(Product p)
  {
    return p.getPrices();
  }

  public static Warehouse retrieve()
  {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
	  ClientIDServer.retrieve(input);
      ManufacturerIDServer.retrieve(input);
      ProductIDServer.retrieve(input);
      return warehouse;
    } catch (IOException ioe){
      ioe.printStackTrace();
      return null;
    } catch (ClassNotFoundException cnfe){
      cnfe.printStackTrace();
      return null;
    }
  }

  public static boolean save()
  {
    try{
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIDServer.instance());
      output.writeObject(ManufacturerIDServer.instance());
      output.writeObject(ProductIDServer.instance());
      return true;
    } catch (IOException ioe){
      ioe.printStackTrace();
      return false;
    }
  }

  private void writeObject(java.io.ObjectOutputStream output)
  {
    try{
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch (IOException ioe){
      System.out.println(ioe);
    }
  }

  private void readObject(java.io.ObjectInputStream input)
  {
    try{
      input.defaultReadObject();
      if (warehouse == null)
      {
        warehouse = (Warehouse) input.readObject();
      }
      else
      {
        input.readObject();
      }
    } catch(IOException ioe){
      ioe.printStackTrace();
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public String toString()
  {
    return clientList + "\n" + manufacturerList + "\n"; // + productList;
  }

}
