package gui;
import warehouse.*;
import java.util.*;

public class PlaceOrderState extends WarehouseState{

    private static PlaceOrderState instance;
    private static ArrayList<Product> products = new ArrayList<Product>();
    private static ArrayList<Integer> quantities = new ArrayList<Integer>();
    private static String orderForm;
    private static double orderCost;

    private PlaceOrderState(){

    }

    public static PlaceOrderState instance() {
        if ( instance == null )
            return instance = new PlaceOrderState();
        return instance;
    }

    PlaceOrderPanel panel = new PlaceOrderPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void orderPressed(){
        String prodId = panel.idTextField.getText();
        String clientId = Security.instance().getClientID();
        int quantity = Integer.parseInt(panel.quantityTextField.getText());
        Calendar date = Calendar.getInstance();
        panel.resultTextBox.append(Warehouse.getInstance().newClientOrder(clientId, prodId, quantity, date));
    }
}
