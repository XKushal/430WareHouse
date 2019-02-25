import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface{
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int ADD_MANUFACTURER = 3;
  private static final int ASSIGN_PRODUCT = 4;
  private static final int UNASSIGN_PRODUCT = 5;
  private static final int LIST_CLIENTS = 6;
  private static final int LIST_PRODUCTS = 7;
  private static final int LIST_MANUFACTURERS = 8;
  private static final int LIST_SUPP_OF_PROD = 9;
  private static final int LIST_PROD_BY_MANU = 10;
  private static final int SAVE = 11;
  private static final int RETRIEVE = 12;
  private static final int HELP = 13;

  private UserInterface(){
    if(yesOrNo("Look for saved data and use it?")){
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }

  public static UserInterface instance()
  {
    if (userInterface == null)
    {
      return userInterface = new UserInterface();
    }
    else
    {
      return userInterface;
    }
  }

  public String getToken(String prompt)
  {
    do{
      try{
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
        if (tokenizer.hasMoreTokens())
        {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe){
        System.exit(0);
      }
    } while (true);
  }

  private boolean yesOrNo(String prompt)
  {
    String more = getToken(prompt + " (Y|y)es or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y')
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  public int getNumber(String prompt)
  {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe){
        System.out.println("Please input a number ");
      }
    } while (true);
  }

  public Calendar getDate(String prompt)
  {
    do {
      try{
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe){
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }

  public int getCommand()
  {
    do {
      try{
        int value = Integer.parseInt(getToken("Enter command:    (" + HELP + "for help)"));
        if (value >= EXIT && value <= HELP)
        {
            return value;
        }
      }catch (NumberFormatException nfe){
        System.out.println("Enter a number");
      }
    }while (true);
  }

  public void help()
  {
    System.out.println("\nEnter a number between 0 and 13 as explained below:");
    System.out.println(EXIT + " to Exit");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(ADD_PRODUCT + " to add a product");
    System.out.println(ADD_MANUFACTURER + " to add a manufacturer");
    System.out.println(ASSIGN_PRODUCT + " to assign a product to a manufacturer");
    System.out.println(UNASSIGN_PRODUCT + " to unassign a product from manufacturer");
    System.out.println(LIST_CLIENTS + " to list all clients");
    System.out.println(LIST_PRODUCTS + " to list all products");
    System.out.println(LIST_MANUFACTURERS + " to list all manufacturers");
    System.out.println(LIST_SUPP_OF_PROD + " to list suppliers of a specified product");
    System.out.println(LIST_PROD_BY_MANU + " to list products supplied by a specified manufacturer");
    System.out.println(SAVE + " to save");
    System.out.println(RETRIEVE + " to retrieve");
    System.out.println(HELP + " for help\n");
  }

  public void addClient()
  {
    String Name = getToken("Enter client name: ");
    String Address = getToken("Enter client address: ");
    String Phone = getToken("Enter client phone: ");
    String Billing = getToken("Enter client billing info: ");
    Client result;
    result = warehouse.addClient(Name, Phone, Address, Billing);
    if (result == null)
    {
      System.out.println("Could not add client.");
    }
    else
    {
      System.out.println(result);
    }
  }

  public void addProduct()
  {
    String Name = getToken("Enter product name: ");
    String Quantity = getToken("Enter quantity: ");
    int q = Integer.parseInt(Quantity);
    Product result;
    result = warehouse.addProduct(Name, q);
    if (result == null)
    {
      System.out.println("Could not add product.");
    }
    else
    {
      System.out.println(result);
    }
  }

  public void addManufacturer()
  {
    String Name = getToken("Enter manufacturer name: ");
    String Address = getToken("Enter manufacturer address: ");
    String Phone = getToken("Enter manufacturer phone: ");
    Manufacturer result;
    result = warehouse.addManufacturer(Name, Phone, Address);
    if (result == null)
    {
      System.out.println("Could not add manufacturer.");
    }
    else
    {
      System.out.println(result);
    }
  }

  public void assignProduct()
  {
    String pID = getToken("Enter product ID: ");
    Product product;
    if ((product=warehouse.searchProduct(pID)) == null)
    {
      System.out.println("Product does not exist.");
      return;
    }

    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer m;
    if ((m=warehouse.searchManufacturer(mID)) == null)
    {
      System.out.println("No such manufacturer.");
      return;
    }

    double p;
    while (true)
    {
      String price = getToken("Enter product unit price: ");
      try {
        p = Double.parseDouble(price);
        break; // will only get to here if input was a double
        } catch (NumberFormatException ignore) {
        System.out.println("Invalid input");
      }
    }


    product = warehouse.assignProdToManufacturer(pID, mID, p);
    if (product != null)
    {
      System.out.println("Product " + product.getProdName() + " assigned to " + m.getName() + " successfully.");
    }
    else
    {
      System.out.println("Product could not be assigned.");
    }
  }

  public void unassignProduct()
  {
    String pID = getToken("Enter product ID: ");
    Product product;
    if ((product=warehouse.searchProduct(pID)) == null)
    {
      System.out.println("Product does not exist.");
      return;
    }

    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer m;
    if ((m=warehouse.searchManufacturer(mID)) == null)
    {
      System.out.println("No such manufacturer.");
      return;
    }

    product = warehouse.unassignProdFromManufacturer(pID, mID);
    if (product != null)
    {
      System.out.println("Product " + product.getProdName() + " unassigned from " + m.getName() + " successfully.");
    }
    else
    {
      System.out.println("Product could not be unassigned.");
    }


  }

  public void listClients()
  {
    Client C_temp;
    Iterator<Client>  Client_Traversal = warehouse.getClients();
    while ((Client_Traversal.hasNext()) != false)
    {
      C_temp = Client_Traversal.next();
      System.out.println(C_temp.getName());
    }
  }

  public void listProducts()
  {
    Product P_temp;
    Iterator<Product> Product_Traversal = warehouse.getProducts();
    while ((Product_Traversal.hasNext()) != false)
    {
      P_temp = Product_Traversal.next();
      System.out.println(P_temp.getProdName());
    }
  }

  public void listManufacturers()
  {
    Manufacturer M_temp;
    Iterator<Manufacturer>  Manu_Traversal = warehouse.getManufacturers();
    while ((Manu_Traversal.hasNext()) != false)
    {
      M_temp = Manu_Traversal.next();
      System.out.println(M_temp.getName());
    }
  }

  public void listSuppliersOfProduct()
  {
    Manufacturer supplier;
    Double price;
    String pID = getToken("Enter the product ID: ");
    Product product = warehouse.searchProduct(pID);
    if (product != null)
    {
      Iterator<Manufacturer> m_traversal = warehouse.getSuppliersOfProduct(product);
      Iterator<Double> price_traversal = warehouse.getProductPrices(product);
      while (((m_traversal.hasNext()) != false) && ((price_traversal.hasNext()) != false))
      {
        supplier = m_traversal.next();
        price = price_traversal.next();
        System.out.println("Supplier: " + supplier.getName() + ". Supply Price: $" + price);
      }
    }
    else
    {
      System.out.println("Product not found");
    }
  }

  public void listProductsByManufacturer()
  {
    String m = getToken("Please enter manufacturer ID: ");
    Manufacturer manufacturer = warehouse.searchManufacturer(m);
    if (manufacturer != null)
    {
      Product p_temp;
      Iterator<Product> p_traversal = warehouse.getProductsByManufacturer(manufacturer);
      while (p_traversal.hasNext() != false)
      {
          p_temp = p_traversal.next();
          System.out.println(p_temp.getProdName());
      }
    }
    else
    {
      System.out.println("Manufacturer doesn't exist");
    }
  }

  private void save()
  {
    if (warehouse.save())
    {
      System.out.println("The warehouse has been successfully saved in the file WarehouseData \n");
    }
    else
    {
      System.out.println("There has been an error in saving the warehouse. \n");
    }
  }

  private void retrieve()
  {
    try{
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null)
      {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      }
      else
      {
        System.out.println("File doesn't exist; creating new warehouse");
        warehouse = Warehouse.instance();
      }
    }catch (Exception cnfe){
      cnfe.printStackTrace();
    }
  }


  public void process()
  {
    int command;
    help();
    while ((command = getCommand()) != EXIT)
    {
      switch (command)
      {
        case ADD_CLIENT:              addClient();
                                      break;
        case ADD_PRODUCT:             addProduct();
                                      break;
        case ADD_MANUFACTURER:        addManufacturer();
                                      break;
        case ASSIGN_PRODUCT:          assignProduct();
                                      break;
        case UNASSIGN_PRODUCT:        unassignProduct();
                                      break;
        case LIST_CLIENTS:            listClients();
                                      break;
        case LIST_PRODUCTS:           listProducts();
                                      break;
        case LIST_MANUFACTURERS:      listManufacturers();
                                      break;
        case LIST_SUPP_OF_PROD:       listSuppliersOfProduct();
                                      break;
        case LIST_PROD_BY_MANU:       listProductsByManufacturer();
                                      break;
        case SAVE:                    save();
                                      break;
        case RETRIEVE:                retrieve();
                                      break;
        case HELP:                    help();
                                      break;
      }
    }
  }
  public static void main(String[] s)
  {
    UserInterface.instance().process();
  }
}
