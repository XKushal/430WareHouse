import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_MANUFACTURER = 1;
  private static final int SAVE = 2;
  private static final int RETRIEVE = 3;
  private static final int SHOW_MANUFACTURERS = 4;
  private static final int HELP = 5;
  
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
        StringTokenizer tokenizer = new StringTokenizer(line);
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
    System.out.println("Enter a number between 0 and 16 as explained below:");
    System.out.println(EXIT + " to Exit.");
    System.out.println(ADD_MANUFACTURER + " to add a manufacturer.");
    System.out.println(SAVE + " to save the warehouse.");
    System.out.println(RETRIEVE + " to retrieve the warehouse.");
    System.out.println(SHOW_MANUFACTURERS + " to show the manufacturers list.");
    System.out.println(HELP + " for help");
  }

  public void addManufacturer() {
     Manufacturer result;
     do {
      String name = getToken("Enter Manufacturer Name: ");
      String address = getToken("Enter Address: ");
      String phone = getToken("Enter Phone No.: ");
      result = warehouse.addManufacturer(name, address, phone);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Manufacturer could not be added. ");
      }
      if (!yesOrNo("Add more manufacturers?")) {
        break;
      }
    } while (true);
  }

   public void showManufacturers() {
      Iterator <Manufacturer> allManufacturers = warehouse.getManufacturers();
      System.out.println("---------------------------------------------------------------");
      while (allManufacturers.hasNext()){
    Manufacturer manufacturer = allManufacturers.next();
          System.out.println(manufacturer.toString());
      }
      System.out.println("---------------------------------------------------------------\n");
  }

  private void save() {
    if (Warehouse.save()) {
      System.out.println("The warehouse has been successfully saved in the file WarehouseData.\n" );
    } else {
      System.out.println("There has been an error in saving the warehouse.\n" );
    }
  }

  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println("The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_MANUFACTURER :  	addManufacturer();
                                	break;

        case SAVE              :  save();
                                	break;

        case RETRIEVE          :  retrieve();
                                	break;

        case SHOW_MANUFACTURERS:  showManufacturers();
                                  break;	

        case HELP              :  help();
                                	break;
      }
    }
  }

  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
