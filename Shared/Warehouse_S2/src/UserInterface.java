
import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface {

    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int ADD_PRODUCTS = 2;
    private static final int ADD_MANUFACTURER = 3;
    private static final int ACCEPT_ORDERS = 4;
    private static final int ACCEPT_PAYMENT = 5;
    private static final int GET_BALANCE = 6;
    private static final int GET_OVERDUE = 7;
    private static final int SHOW_WAITLIST = 8;
    private static final int SHOW_INVOICE = 9;
    private static final int ACCEPT_SHIPMENT = 10;

    /*
     private static final int REMOVE_HOLD = 8;
     private static final int PROCESS_HOLD = 9;
     private static final int GET_TRANSACTIONS = 10;

     */
    private static final int SHOW_MANUFACTURERS = 10;
    private static final int SHOW_CLIENTS = 11;
    private static final int SHOW_PRODUCTS = 12;
    private static final int SAVE = 13;
    private static final int RETRIEVE = 14;
    private static final int HELP = 15;

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
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
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
        System.out.println("Enter a number between 0 and 12 as explained below:");
        System.out.println(EXIT + " to Exit\n");
        System.out.println(ADD_CLIENT + " to add a client");
        System.out.println(ADD_PRODUCTS + " to add product");
        System.out.println(ADD_MANUFACTURER + " to add manufacturer");
        System.out.println(ACCEPT_ORDERS + " to accept orders from a client");
        System.out.println(ACCEPT_PAYMENT + " to accept payment from a client");
        System.out.println(GET_BALANCE + " to get a client balance ");
        System.out.println(GET_OVERDUE + " to get overdue balance of all clients ");
        /*
         System.out.println(RENEW_BOOKS + " to  renew books ");
         System.out.println(REMOVE_BOOKS + " to  remove books");
         System.out.println(PLACE_HOLD + " to  place a hold on a book");
         System.out.println(REMOVE_HOLD + " to  remove a hold on a book");
         System.out.println(PROCESS_HOLD + " to  process holds");
         System.out.println(GET_TRANSACTIONS + " to  print transactions");
         */
        System.out.println(SHOW_WAITLIST + " to print waitlist of products associated with clients");
        System.out.println(SHOW_INVOICE + " to get a client invoice ");
        System.out.println(ACCEPT_SHIPMENT + " to get a client invoice ");

        System.out.println(SHOW_MANUFACTURERS + " to  print manufacturers");
        System.out.println(SHOW_CLIENTS + " to  print clients");
        System.out.println(SHOW_PRODUCTS + " to  print products");
        System.out.println(SAVE + " to  save data");
        System.out.println(RETRIEVE + " to  retrieve");
        System.out.println(HELP + " for help");
    }

    public void addClient() {
        String name = getToken("Enter client name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Client result;
        result = warehouse.addMember(name, address, phone);
        if (result == null) {
            System.out.println("Could not add client");
        }
        System.out.println(result);
    }

    public void addProducts() {
        Product result;
        double price;
        int quantity;

        do {
            String manufact = getToken("Enter a manufacturer ID: ");
            if (warehouse.findManufacturer(manufact) != null) {

                String title = getToken("Enter  product name: ");
                String productID = getToken("Enter id: ");
                price = Double.parseDouble(getToken("Enter price: "));
                quantity = Integer.parseInt(getToken("Enter quantity: "));

                result = warehouse.addProduct(title, productID, price, quantity);
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("Product could not be added.");
                }
            }//End of find manufacturer if
            else {
                System.out.println("Invalid Manufacturer ID.");
            }

            if (!yesOrNo("Add more products?")) {
                break;
            }

        } while (true);
    }//End addProducts

    public void addManufacturer() {
        String name = getToken("Enter manufacturer name");
        String address = getToken("Enter address");
        String phone = getToken("Enter phone");
        Manufacturer result;
        result = warehouse.addManufacturer(name, address, phone);
        if (result == null) {
            System.out.println("Could not add manufacturer");
        }
        System.out.println(result);
    }

    public void acceptOrders() {
        String clientId = getToken("Enter client ID: ");
        String productId;
        int quantity;

        if (warehouse.findClient(clientId)) {
            //client is found so we create an order for a client
            warehouse.createOrder(clientId);
            do {
                productId = getToken("Enter product Id");

                Product tempProduct = warehouse.findProduct(productId);

                if (tempProduct == null) {
                    System.out.println("Product Not found.");

                } else {
                    quantity = Integer.parseInt(getToken("Enter quantity"));
                    warehouse.addToOrder(clientId, productId, quantity);
                   // warehouse.updateClientBalance(clientId, tempProduct.getPrice() * quantity);

                }

            } while (yesOrNo("Add more items?"));
            // after order is done we need to add it to the order list

            warehouse.addToOrderList();

        } else {
            System.out.println("Client is not found.");
        }
    }//End of acceptOrders

    public void acceptPayment() {
        double balance, payment;
        do {
            String clientID = getToken("Enter client ID: ");

            Iterator allMembers = warehouse.getMembers();
            if (warehouse.findClient(clientID)) {
                //client is found so we accept a payment from a client

                balance = warehouse.getClientBalance(clientID);
                System.out.println("This client's balance is: " + balance);
                payment = Double.parseDouble(getToken("Enter payment amount"));
                warehouse.updateClientBalance(clientID, -payment);

                balance = warehouse.getClientBalance(clientID);
                System.out.println("This client's new balance is: " + balance);

            } else {
                System.out.println("Client is not found.");
            }

        } while (yesOrNo("Would you like to accept payment from another client?"));
    }//End of acceptPayment

    public void showProducts() {
        Iterator allProducts = warehouse.getProducts();
        while (allProducts.hasNext()) {
            Product product = (Product) (allProducts.next());
            System.out.println(product.toString());
        }
    }

    public void showClients() {
        Iterator allMembers = warehouse.getMembers();
        while (allMembers.hasNext()) {
            Client member = (Client) (allMembers.next());
            System.out.println(member.toString());
        }
    }

    public void showInvoice() {

        String clientId = getToken("Enter client ID");
        if (warehouse.findClient(clientId)) {
            Order o = warehouse.findOrder(clientId);
            if(o != null){
            o.printOrder();
            }
        } else {
            System.out.println("Client is not found.");
        }



    }

    public void showWaitlist() {
         System.out.println("Show wait list for product.");
         String clientId = getToken("Enter product ID");

        Iterator wholeWaitList = warehouse.getWaitList(clientId);
        
        while (wholeWaitList.hasNext()) {
            Wait waitList = (Wait) (wholeWaitList.next());
            System.out.println(waitList.toString());
        }
    }

    public void showManufacturers() {
        Iterator allManu = warehouse.getManufacturers();
        while (allManu.hasNext()) {
            Manufacturer manufacturer = (Manufacturer) (allManu.next());
            System.out.println(manufacturer.toString());
        }
    }

    public void returnBooks() {
        System.out.println("Dummy Action");
    }

    public void getOverdueBalance() {
        Iterator allMembers = warehouse.getMembers();
        while (allMembers.hasNext()) {
            Client member = (Client) (allMembers.next());
            if (member.getBalance() > 0) {
                System.out.println(member.toStringBalance());

            }
        }

    }

    public void getClientBalance() {

        String clientId = getToken("Enter client ID");
        if (warehouse.findClient(clientId)) {
            //client is found so we create an order for a client
            double total = warehouse.getClientBalance(clientId);
            System.out.println("Total balance: " + total);

        } else {
            System.out.println("Client is not found.");
        }

    }

    public void removeBooks() {
        System.out.println("Dummy Action");
    }

    public void placeHold() {
        System.out.println("Dummy Action");
    }

    public void removeHold() {
        System.out.println("Dummy Action");
    }

    public void processHolds() {
        System.out.println("Dummy Action");
    }

    public void getTransactions() {
        System.out.println("Dummy Action");
    }

    private void save() {
        if (warehouse.save()) {
            System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n");
        } else {
            System.out.println(" There has been an error in saving \n");
        }
    }

    private void retrieve() {
        try {
            Warehouse tempLibrary = Warehouse.retrieve();
            if (tempLibrary != null) {
                System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n");
                warehouse = tempLibrary;
            } else {
                System.out.println("File doesnt exist; creating new warehouse");
                warehouse = Warehouse.instance();

            }
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    public void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
                case ADD_CLIENT:
                    addClient();
                    break;
                case ADD_PRODUCTS:
                    addProducts();
                    break;
                case ADD_MANUFACTURER:
                    addManufacturer();
                    break;

                case ACCEPT_ORDERS:
                    acceptOrders();
                    break;

                case ACCEPT_PAYMENT:
                    acceptPayment();
                    break;

                case GET_BALANCE:
                    getClientBalance();
                    break;
                case GET_OVERDUE:
                    getOverdueBalance();
                    break;

                case SHOW_WAITLIST:
                    showWaitlist();
                    break;
                case SHOW_INVOICE:
                    showInvoice();
                    break;
                /*
                 case RENEW_BOOKS:       renewBooks();
                 break;
                 case PLACE_HOLD:        placeHold();
                 break;
                 case REMOVE_HOLD:       removeHold();
                 break;
                 case PROCESS_HOLD:      processHolds();
                 break;
                 case GET_TRANSACTIONS:  getTransactions();
                 break;
                 */
                case SAVE:
                    save();
                    break;
                case RETRIEVE:
                    retrieve();
                    break;
                case SHOW_CLIENTS:
                    showClients();
                    break;
                case SHOW_PRODUCTS:
                    showProducts();
                    break;
                case SHOW_MANUFACTURERS:
                    showManufacturers();

                    break;
                case HELP:
                    help();
                    break;
            }
        }
    }

    public static void main(String[] s) {
        UserInterface.instance().process();
    }
}
