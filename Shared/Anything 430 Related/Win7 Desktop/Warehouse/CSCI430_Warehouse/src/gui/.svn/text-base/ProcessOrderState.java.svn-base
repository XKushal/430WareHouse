package gui;
import warehouse.*;

public class ProcessOrderState extends WarehouseState{

    private static ProcessOrderState instance;

    private ProcessOrderState(){

    }

    public static ProcessOrderState instance() {
        if ( instance == null )
            return instance = new ProcessOrderState();
        return instance;
    }

    ProcessOrderPanel panel = new ProcessOrderPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void confirmPressed(){
        String clientId = Security.instance().getClientID();
        panel.resultTextBox.append(Warehouse.getInstance().showAllClientOrders(clientId));
        String orderId = panel.orderIDField.getText();
        
        if(!Warehouse.getInstance().processOrder(clientId, orderId))
            panel.resultTextBox.append("Unable to process order");
        
         panel.resultTextBox.append("Not yet implemented.");
    }
}

