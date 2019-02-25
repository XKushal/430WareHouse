package gui;
import warehouse.*;

public class SaveDatabaseState extends WarehouseState{

    private static SaveDatabaseState instance;

    private SaveDatabaseState(){

    }

    public static SaveDatabaseState instance() {
        if ( instance == null )
            return  new SaveDatabaseState();
        return instance;
    }

    SaveDatabasePanel panel = new SaveDatabasePanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public String saveDatabasePressed(){
        if(Warehouse.getInstance().save())
            return "Warehouse saved successfully!";
        else
            return "Warehouse could not be saved";
    }

}
