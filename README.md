# 430WareHouse
This is the project perfectly based on modern software development practice.
The Clear vision and specification of the model is listed below.

 public class UserInterface()
 {
  private static UserInterface userInterface;
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
  
  private static final int OUTSTANDING_BALANCE_LIST      = 14;
  
  private static final int GET_WAIT_ORD_FOR_PROD         = 15;
  
  private static final int GET_WAIT_ORD_FOR_CLIENT       = 16;
  
  private static final int GET_LIST_ORDERS_MANU          = 17;
  
  private static final int RECEIVE_A_SHIPMENT            = 18;
  
  private static final int SAVE                          = 19;
  
  private static final int RETRIEVE                      = 20;
  
  private static final int HELP                          = 21;
  
}


public void menu()
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
        case OUTSTANDING_BALANCE_LIST:      ListClientsWithOutstandingBalance();
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
  
  This is just the User interface where user plays with system to carry out the various functions offered by the warehouse.

