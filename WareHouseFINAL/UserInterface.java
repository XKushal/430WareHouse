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
  private static final int OUTSTANDING_BALANCE_LIST            = 14;
  private static final int GET_WAIT_ORD_FOR_PROD         = 15;
  private static final int GET_WAIT_ORD_FOR_CLIENT       = 16;
  private static final int GET_LIST_ORDERS_MANU          = 17;
  private static final int RECEIVE_A_SHIPMENT            = 18;
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
    System.out.println(OUTSTANDING_BALANCE_LIST + " to list clients with an outstanding balance");
    System.out.println(GET_WAIT_ORD_FOR_PROD + " to get a list of waitlisted orders for a product");
    System.out.println(GET_WAIT_ORD_FOR_CLIENT + " to get a list of waitlisted orders for a client");
    System.out.println(GET_LIST_ORDERS_MANU + " to get a list of orders placed with a manufacturer");
    System.out.println(RECEIVE_A_SHIPMENT + " to receive a Shipment from the manufacturer");
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
    int Mcount=1;
    int Pcount=1;
    String pID = getToken("Enter product ID: ");
    Product product;
    while((product = warehouse.searchProduct(pID)) == null){
      System.out.println("Product doesn't exist.");
      if(Pcount++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
        return;
      }
     pID = getToken("Enter valid product ID: ");
    }

    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer m;
    while((m = warehouse.searchManufacturer(mID)) == null){
      System.out.println("Manufacturer doesn't exist.");
      if(Mcount++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
        return;
      }
     mID = getToken("Enter valid ID: ");
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
      String quantity = getToken("Enter product quantity:  (if unknown enter 0)");
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
    int Mcount=1;
    int Pcount=1;
    String pID = getToken("Enter product ID: ");
    Product product;
    while((product = warehouse.searchProduct(pID)) == null){
      System.out.println("Product doesn't exist.");
      if(Pcount++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
        return;
      }
     pID = getToken("Enter valid product ID: ");
    }

    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer m;
    while((m = warehouse.searchManufacturer(mID)) == null){
      System.out.println("Manufacturer doesn't exist.");
      if(Mcount++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
        return;
      }
     mID = getToken("Enter valid ID: ");
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
    Iterator<Client>  allClients = warehouse.getClients();
    if(allClients.hasNext()==false){
      System.out.println("No Clients exist in the system. \n");
    }else{
      System.out.println("------------------------------------------------------------------------------------");
        while ((allClients.hasNext()) != false)
        {
          Client client = allClients.next();
          System.out.println(client.toString());
        }
        System.out.println("------------------------------------------------------------------------------------\n");
      }
  }

  public void listProducts()
  {
    Iterator<Product> allProducts = warehouse.getProducts();
    if(allProducts.hasNext()== false){
      System.out.println("No products in the System.\n");
      return;
    }else{
      System.out.println("------------------------");
        while ((allProducts.hasNext()) != false)
        {
          Product product = allProducts.next();
          System.out.println(product.toString());
        }
        System.out.println("------------------------\n");
      }
  }

  public void listManufacturers()
  {
    Iterator<Manufacturer>  allManufacturers = warehouse.getManufacturers();
    if(allManufacturers.hasNext() == false){
      System.out.println("No Manufacturer in the System.\n");
      return;
    }else{
      System.out.println("-------------------------------------------------");
      while ((allManufacturers.hasNext()) != false)
      {
        Manufacturer manufacturer = allManufacturers.next();
        System.out.println(manufacturer.toString());
      }
      System.out.println("-------------------------------------------------\n");
    }
  }

  public void listSuppliersOfProduct()
  {
    Double price;
    String pID = getToken("Enter the product ID: ");
    Product product = warehouse.searchProduct(pID);
    System.out.println("-----------------------------------------------");
    if (product != null)
    {
      Supplier supplier;
      Iterator<Supplier> allSuppliers = warehouse.getSuppliersOfProduct(product);
      while ((allSuppliers.hasNext()) != false)
      {
        supplier = allSuppliers.next();
        System.out.println("Supplier: " + supplier.getManufacturer().getName() + ". Price: $" + supplier.getPrice() + " Quantity: " + supplier.getQuantity());
      }
      System.out.println("-----------------------------------------------\n");
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
    System.out.println("--------------------------");
    if (manufacturer != null)
    {
      Product product;
      Iterator<Product> allProducts = warehouse.getProductsByManufacturer(manufacturer);
      while (allProducts.hasNext() != false)
      {
          product = allProducts.next();
          System.out.println(product.toString());
      }
      System.out.println("--------------------------\n");
    }
    else
    {
      System.out.println("Manufacturer doesn't exist. Try Again\n");
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
    int Mcount=1;
    int Pcount=1;
    int PTcount =1;

    String mID = getToken("Enter manufacturer ID: ");
    Manufacturer manufacturer;
    while((manufacturer = warehouse.searchManufacturer(mID)) == null){
      System.out.println("Manufacturer doesn't exist.");
      if(Mcount++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
        return;
      }
     mID = getToken("Enter valid ID: ");
    }

    ManufacturerOrder order = warehouse.CreateManufacturerOrder(manufacturer);
    if (order == null){
      return;
    }

    do {
      pID = getToken("Enter product ID: ");
      while((product = warehouse.searchProduct(pID)) == null){
        System.out.println("Product doesn't exist.");
        if(Pcount++ == 3){
         System.out.println("You have reached the maximum try. Try next time.\n");
        return;
        }
      pID = getToken("Enter valid product ID: ");
     }

      while ((supplier = warehouse.searchProductSupplier(product, manufacturer)) == null)
      {
        System.out.println("Product isn't supplied by specified manufacturer");
        pID = getToken("Enter product ID: ");
        while ((product = warehouse.searchProduct(pID)) == null){
          System.out.println("Product doesn't exist.");
          if(PTcount++ == 3){
            System.out.println("You have reached the maximum try. Try next time.\n");
          return;
          }
          pID = getToken("Enter valid product ID: ");
        }
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
    if (success){
      System.out.println("Order added successfully");
      System.out.println("Order ID: " + order.getID());
    }
    else{
      System.out.println("Failed to add order\n");
    }
  }

  public void AcceptClientPayment()
  {
    int C_count = 1;
    Client client;
    String cID = getToken("Enter client ID: ");
    while((client = warehouse.searchClient(cID)) == null){
      System.out.println("Client doesn't exist.");
      if(C_count++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
        return;
      }
     cID = getToken("Enter valid Client ID: ");
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
    System.out.println("Client " + client.getName() + "'S new balance is $" + newTotal);
  }

  public void ListClientsWithOutstandingBalance()
  {
    Iterator<Client> allClients = warehouse.getClients();
    Client c;
    int i = 1;
     System.out.println("---------------------------------------");
        while (allClients.hasNext())
        {
          c = allClients.next();
          if (c.getBilling() < 0)
          {
            System.out.println(i + ".) " + c.getID() + ", Remaining Balance: $" + c.getBilling());
            i++;
          }
        }
        System.out.println("---------------------------------------\n");
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
    Iterator<Waitlist> waitlist = warehouse.getWaitlistedClients(p);
    Waitlist w;
    System.out.println("---------------------------------");
    while (waitlist.hasNext())
    {
      w = waitlist.next();
      System.out.println(i + ".) Client: " + w.getClient().getID() + ", Amount Waitlisted: " + w.getQuantity());
      i++;
    }
    System.out.println("---------------------------------\n");
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
    System.out.println("------------------------------------");
    Iterator<Waitlist> waitlist = warehouse.getWaitlistedProducts(c);
    Waitlist w;
    while (waitlist.hasNext())
    {
      w = waitlist.next();
      System.out.println(i + ".) Product: " + w.getProduct().getID() + ", Amount Waitlisted: " + w.getQuantity());
      i++;
    }
    System.out.println("------------------------------------\n");
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

  private void ReceiveShipment()
  {
    int O_count = 1;
    String oID = getToken("Enter the order ID: ");
    ManufacturerOrder order ;
    while((order = warehouse.searchManuOrders(oID)) == null){
      System.out.println("No such order found. ");
      if(O_count++ == 3){
        System.out.println("You have reached the maximum try. Try next time.\n");
      }
      oID = getToken("Enter the valild order ID: ");
    }

    if (order.getOrderStatus() == true)
    {
      System.out.println("Order has already been processed and received\n");
      return;
    }

    Manufacturer manufacturer = order.getManufacturer();
    Iterator<Product> allProducts = order.getProds();
    Product p;
    Iterator<Integer> quantities = order.getQs();
    int q;
    System.out.println("Order details");
    System.out.println("-------------");
    while (allProducts.hasNext() && quantities.hasNext())
    {
      int j = 1;
      p = allProducts.next();
      q = quantities.next();
      System.out.println("Product: " + p.getID() + ", Quantity: " + q);
      j++;
    }
    boolean add = yesOrNo("Accept order?");
    if (add)
    {
      Iterator<Product> products = order.getProds();
      Product prod;
      Iterator<Integer> qtys = order.getQs();
      int quant;
      Supplier supplier;
      while (products.hasNext() && qtys.hasNext())
      {
        int j = 1;
        prod = products.next();
        quant = qtys.next();
        supplier = warehouse.searchProductSupplier(prod, manufacturer);
        if (supplier.getQuantity() == 0)
        {
          supplier.setNewQuantity(-1 * quant);
          //fullfill the waitlist first
          warehouse.FulfillWaitlist(prod, quant, supplier);
        }
        else
        {
          supplier.setNewQuantity(-1 * quant);
        }
        j++;
      }
      order.receiveOrder();//shipment has been received
    }
    System.out.println("Remainig products successfully added to inventory.\n");
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
        System.out.println("File doesn't exist; creating new warehouse\n");
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
        case OUTSTANDING_BALANCE_LIST:            ListClientsWithOutstandingBalance();
                                            break;
        case GET_WAIT_ORD_FOR_PROD:         GetWaitlistedOrdersForProd();
                                            break;
        case GET_WAIT_ORD_FOR_CLIENT:       GetWaitlistedOrdersForClient();
                                            break;
        case GET_LIST_ORDERS_MANU:          ListOrdersPlacedWithManufacturer();
                                            break;
        case RECEIVE_A_SHIPMENT:            ReceiveShipment();
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
