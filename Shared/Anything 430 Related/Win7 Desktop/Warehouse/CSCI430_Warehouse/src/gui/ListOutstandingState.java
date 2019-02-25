package gui;
import warehouse.*;
import java.util.Iterator;

public class ListOutstandingState extends WarehouseState{

    private static ListOutstandingState instance;

    private ListOutstandingState(){

    }

    public static ListOutstandingState instance() {
        if ( instance == null )
            return instance = new ListOutstandingState();
        return instance;
    }

    ListOutstandingPanel panel = new ListOutstandingPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void confirmPressed(){
        panel.resultTextBox.append("Clients with outstanding balances: \n");
        panel.resultTextBox.append(Warehouse.getInstance().showAllClientsWithBackorders());
    }
}
