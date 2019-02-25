package gui;
import warehouse.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class LoadDatabaseState extends WarehouseState{

    private static LoadDatabaseState instance;

    private LoadDatabaseState(){

    }

    public static LoadDatabaseState instance() {
        if ( instance == null )
            return  instance = new LoadDatabaseState();
        return instance;
    }

    LoadDatabasePanel panel = new LoadDatabasePanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public String loadDatabasePressed(){
        try {
          Warehouse tempWarehouse = Warehouse.retrieve();          
          if (tempWarehouse != null) {
            Warehouse.setInstance(tempWarehouse);
            return "Warehouse loaded successfully!";
          } else {
            System.out.println("File doesnt exist; creating new warehouse" );
            Warehouse.getInstance();
            return "File not found, creating new warehouse";
          }
        } catch(Exception cnfe) {
          cnfe.printStackTrace();
          return "Error loading warehouse.";
        }
    }
}
