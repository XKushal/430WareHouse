import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface{
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT                          = 0;
  private static final int ADD_CLIENT                    = 1;
  private static final int ADD_PRODUCT                   = 2;
  private static final int ADD_MANUFACTURER              = 3;
  private static final int ASSIGN_PRODUCT                = 4;
  private static final int UNASSIGN_PRODUCT              = 5;
  private static final int LIST_CLIENTS                  = 6;
  private static final int LIST_PRODUCTS                 = 7;
  private static final int LIST_MANUFACTURERS            = 8;
  private static final int LIST_SUPP_OF_PROD             = 9;
  private static final int LIST_PROD_BY_MANU             = 10;
  private static final int ADD_CLIENT_ORDER              = 11;
  private static final int PLACE_ORDER_WITH_MANUFACTURER = 12;
  private static final int ACCEPT_CLIENT_PAYMENT         = 13;
  private static final int LIST_C_W_OUTST_BAL            = 14;
  private static final int GET_WAIT_ORD_FOR_PROD         = 15;
  private static final int GET_WAIT_ORD_FOR_CLIENT       = 16;
  private static final int GET_LIST_ORDERS_MANU          = 17;
  private static final int RECEIVE_MANU_ORDER            = 18;
  private static final int SAVE                          = 19;
  private static final int RETRIEVE                      = 20;
  private static final int HELP                          = 21;

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
    System.out.println("\nEnter a number between 0 and 21 as explained below:");
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
    System.out.println(ADD_CLIENT_ORDER + " to add and process a client order");
    System.out.println(PLACE_ORDER_WITH_MANUFACTURER + " to place an order with a manufacturer");
    System.out.println(ACCEPT_CLIENT_PAYMENT + " to accept a payment from a client");
    System.out.println(LIST_C_W_OUTST_BAL + " to list clients with an outstanding balance");
    System.out.println(GET_WAIT_ORD_FOR_PROD + " to get a list of waitlisted orders for a product");
    System.out.println(GET_WAIT_ORD_FOR_CLIENT + " to get a list of waitlisted orders for a client");
    System.out.println(GET_LIST_ORDERS_MANU + " to get a list of orders placed with a manufacturer");
    System.out.println(RECEIVE_MANU_ORDER + " to receive and process a manufacturer order");
    System.out.println(SAVE + " to save");
    System.out.println(RETRIEVE + " to retrieve");
    System.out.println(HELP + "  for help\n");
  }

  public void addClient()
  {
    String Name = getToken("Enter client name: ");
    String Address = getToken("Enter client address: ");
    String Phone = getToken("Enter client phone: ");
    double Billing;
    while (true)
    {
      String balance = getToken("Enter client billing info(balance): ");
      try {
        Billing = Double.parseDouble(balance);
        break; // will only get to here if input was a double
        } catch (NumberFormatException ignore) {
        System.out.println("Invalid input");
      }
    }
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
    Product result;
    result = warehouse.addProduct(Name);
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

    int q;
    while (true)
    {
      String quantity = getToken("Enter product quantity:  (if unknown or NA, enter 0)");
      try {
        q = Integer.parseInt(quantity);
        break; // will only get to here if input was an int
        } catch (NumberFormatException ignore) {
        System.out.println("Invalid input");
      }
    }

    product = warehouse.assignProdToManufacturer(pID, mID, p, q);
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
    Supplier supplier;
    Double price;
    String pID = getToken("Enter the product ID: ");
    Product product = warehouse.searchProduct(pID);
    if (product != null)
    {
      Iterator<Supplier> s_traversal = warehouse.getSuppliersOfProduct(product);
      while ((s_traversal.hasNext()) != false)
      {
        supplier = s_traversal.next();
        System.out.println("Supplier: " + supplier.getManufacturer().getName() + ". Supply Price: $" + supplier.getPrice() + " Quantity: " + supplier.getQuantity());
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

  public void AddClientOrder()
  {
    String cID = getToken("Please enter client ID: ");
    Client client = warehouse.searchClient(cID);
    if (client != null)
    {
      ClientOrder order = warehouse.CreateClientOrder(client);

      do{
        String pID = getToken("Enter product ID: ");
        Product product = warehouse.searchProduct(pID);
        while (product == null)
        {
          System.out.println("Could not find product.");
          pID = getToken("Enter product ID: ");
          product = warehouse.searchProduct(pID);
        }
        int q;
        while (true)
        {
          String quantity = getToken("Enter quantity: ");
          try {
            q = Integer.parseInt(quantity);
            break; // will only get to here if input was an int
            } catch (NumberFormatException ignore) {
              System.out.println("Invalid input");
          }
        }
        boolean success = warehouse.AddProdToOrder(product, q, order, client);
        if (success)
        {
          System.out.println("Product added to order successfully.");
        }
        else
        {
          System.out.println("Product couldn't be added to order.");
        }
      }while(yesOrNo("Add another product to order? "));
      double Total = warehouse.GetOrderTotal(order);
      System.out.println("Order total is: $" + Total);
      double NewClientBalance = warehouse.updateClientBalance(client, Total);
      System.out.println("Client " + client.getName() + " new balance is " + NewClientBalance);
    }
    else
    {
      System.out.println("Client doesn't exist");
    }
  }

  public void PlaceOrderWithManufacturer()
  {
    String pID;
    int quantity;
    Product product;
    Supplier supplier;
    Boolean success;
    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer manufacturer = warehouse.searchManufacturer(mID);
    if (manufacturer == null)
    {
      System.out.println("Manufacturer doesn't exist");
      return;
    }
    ManufacturerOrder order = warehouse.CreateManufacturerOrder(manufacturer);
    if (order == null){
      return;
    }
    do {
      pID = getToken("Enter product ID: ");
      product = warehouse.searchProduct(pID);
      while (product == null)
      {
        System.out.println("No such product: ");
        pID = getToken("Enter product ID: ");
        product = warehouse.searchProduct(pID);
      }

      supplier = warehouse.searchProductSupplier(product, manufacturer);
      while (supplier == null)
      {
        System.out.println("Product isn't supplied by specified manufacturer");
        pID = getToken("Enter product ID: ");
        product = warehouse.searchProduct(pID);
        while (product == null)
        {
          System.out.println("No such product: ");
          pID = getToken("Enter product ID: ");
          product = warehouse.searchProduct(pID);
        }
        supplier = warehouse.searchProductSupplier(product, manufacturer);
      }


      while (true)
      {
        String q = getToken("Enter quantity: ");
        try {
          quantity = Integer.parseInt(q);
          break; // will only get to here if input was an int
          } catch (NumberFormatException ignore) {
            System.out.println("Invalid input");
        }
      }
      success = warehouse.AddProductsToManuOrder(product, quantity, order);
      if (!success){
        System.out.println("Couldn't add to order.");
        return;
      }
    }while (yesOrNo("Add another product to order? "));
    success = warehouse.AddOrderToManufacturer(manufacturer, order);
    success = warehouse.addManuOrder(order);
    if (success)
    {
      System.out.println("Order added successfully");
      System.out.println("Order ID: " + order.getID());
    }

    else
    {
      System.out.println("Failed to add order");
    }
  }

  public void AcceptClientPayment()
  {
    String cID = getToken("Enter client ID: ");
    Client client = warehouse.searchClient(cID);
    if (client == null)
    {
      System.out.println("Client doesn't exist");
      return;
    }
    Double Payment;
    while (true)
    {
      String p = getToken("Enter payment amount: ");
      try {
        Payment = Double.parseDouble(p);
        break;
        } catch (NumberFormatException ignore) {
          System.out.println("Invalid input");
      }
    }
    Payment = -1 * Payment; //used so we can use the same updateBalance method in client
    Double newTotal = warehouse.updateClientBalance(client, Payment);
    System.out.println("Client " + client.getName() + " new balance is $" + newTotal);
  }

  public void ListClientsWithOutstandingBalance()
  {
    Iterator<Client> C_Traversal = warehouse.getClients();
    Client c;
    int i = 1;
    while (C_Traversal.hasNext())
    {
      c = C_Traversal.next();
      if (c.getBilling() < 0)
      {
        System.out.println(i + ".) " + c.getID() + ", Bal: $" + c.getBilling());
        i++;
      }
    }
  }

  public void GetWaitlistedOrdersForProd()
  {
    String pID = getToken("Enter product ID: ");
    Product p = warehouse.searchProduct(pID);
    int i = 1;
    if (p == null)
    {
      System.out.println("Product doesn't exist");
      return;
    }
    Iterator<Waitlist> w_Traversal = warehouse.getWaitlistedClients(p);
    Waitlist w;
    while (w_Traversal.hasNext())
    {
      w = w_Traversal.next();
      System.out.println(i + ".) Client: " + w.getClient().getID() + ", Amount Waitlisted: " + w.getQuantity());
      i++;
    }
  }

  public void GetWaitlistedOrdersForClient()
  {
    String cID = getToken("Enter client ID: ");
    Client c = warehouse.searchClient(cID);
    int i = 1;
    if (c == null)
    {
      System.out.println("Client doesn't exist");
      return;
    }
    Iterator<Waitlist> c_Traversal = warehouse.getWaitlistedProducts(c);
    Waitlist w;
    while (c_Traversal.hasNext())
    {
      w = c_Traversal.next();
      System.out.println(i + ".) Product: " + w.getProduct().getID() + ", Amount Waitlisted: " + w.getQuantity());
      i++;
    }
  }

  private void ListOrdersPlacedWithManufacturer()
  {
    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer m = warehouse.searchManufacturer(mID);
    int i = 1;
    if (m == null)
    {
      System.out.println("Manufacturer doesn't exist");
      return;
    }
    Iterator<ManufacturerOrder> o_Traversal = warehouse.getManuOrders(m);
    ManufacturerOrder order;
    while (o_Traversal.hasNext())
    {
      System.out.println("ORDER NUMBER: " + i + "\n---------------");
      order = o_Traversal.next();
      System.out.println("Oder ID: " + order.getID());
      System.out.println("Received: " + order.getOrderStatus());
      Iterator<Product> p_Traversal = order.getProds();
      Product p;
      Iterator<Integer> q_Traversal = order.getQs();
      int q;
      while (p_Traversal.hasNext() && q_Traversal.hasNext())
      {
        int j = 1;
        p = p_Traversal.next();
        q = q_Traversal.next();
        System.out.println("Product: " + p.getID() + ", Quantity: " + q);
        j++;
      }
      i++;
      System.out.println("");
    }
  }

  private void ReceiveManufacturerOrder()
  {
    String oID = getToken("Enter the order ID: ");
    ManufacturerOrder order = warehouse.searchManuOrders(oID);
    if (order == null)
    {
      System.out.println("No such order");
      return;
    }
    if (order.getOrderStatus() == true)
    {
      System.out.println("Order has already been processed and received");
      return;
    }
    Manufacturer manufacturer = order.getManufacturer();
    Iterator<Product> p_Traversal = order.getProds();
    Product p;
    Iterator<Integer> q_Traversal = order.getQs();
    int q;
    System.out.println("Order details");
    System.out.println("-------------");
    while (p_Traversal.hasNext() && q_Traversal.hasNext())
    {
      int j = 1;
      p = p_Traversal.next();
      q = q_Traversal.next();
      System.out.println("Product: " + p.getID() + ", Quantity: " + q);
      j++;
    }
    boolean add = yesOrNo("Accept order?");
    if (add)
    {
      Iterator<Product> prod_Traversal = order.getProds();
      Product prod;
      Iterator<Integer> quant_Traversal = order.getQs();
      int quant;
      Supplier supplier;
      while (prod_Traversal.hasNext() && quant_Traversal.hasNext())
      {
        int j = 1;
        prod = prod_Traversal.next();
        quant = quant_Traversal.next();
        supplier = warehouse.searchProductSupplier(prod, manufacturer);
        if (supplier.getQuantity() == 0)
        {
          supplier.setNewQuantity(-1 * quant);
          warehouse.FulfillWaitlist(prod, quant, supplier);
        }
        else
        {
          supplier.setNewQuantity(-1 * quant);
        }
        j++;
      }
      order.receiveOrder();
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
        case ADD_CLIENT:                    addClient();
                                            break;
        case ADD_PRODUCT:                   addProduct();
                                            break;
        case ADD_MANUFACTURER:              addManufacturer();
                                            break;
        case ASSIGN_PRODUCT:                assignProduct();
                                            break;
        case UNASSIGN_PRODUCT:              unassignProduct();
                                            break;
        case LIST_CLIENTS:                  listClients();
                                            break;
        case LIST_PRODUCTS:                 listProducts();
                                            break;
        case LIST_MANUFACTURERS:            listManufacturers();
                                            break;
        case LIST_SUPP_OF_PROD:             listSuppliersOfProduct();
                                            break;
        case LIST_PROD_BY_MANU:             listProductsByManufacturer();
                                            break;
        case ADD_CLIENT_ORDER:              AddClientOrder();
                                            break;
        case PLACE_ORDER_WITH_MANUFACTURER: PlaceOrderWithManufacturer();
                                            break;
        case ACCEPT_CLIENT_PAYMENT:         AcceptClientPayment();
                                            break;
        case LIST_C_W_OUTST_BAL:            ListClientsWithOutstandingBalance();
                                            break;
        case GET_WAIT_ORD_FOR_PROD:         GetWaitlistedOrdersForProd();
                                            break;
        case GET_WAIT_ORD_FOR_CLIENT:       GetWaitlistedOrdersForClient();
                                            break;
        case GET_LIST_ORDERS_MANU:          ListOrdersPlacedWithManufacturer();
                                            break;
        case RECEIVE_MANU_ORDER:            ReceiveManufacturerOrder();
                                            break;
        case SAVE:                          save();
                                            break;
        case RETRIEVE:                      retrieve();
                                            break;
        case HELP:                          help();
                                            break;
      }
    }
  }
  public static void main(String[] s)
  {
    UserInterface.instance().process();
  }
}
