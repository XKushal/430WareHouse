package gui;
import warehouse.*;

public class CheckPriceState extends WarehouseState{

    private static CheckPriceState instance;

    private CheckPriceState(){

    }

    public static CheckPriceState instance() {
        if ( instance == null )
            return instance = new CheckPriceState();
        return instance;
    }

    CheckPricePanel panel = new CheckPricePanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void confirmPressed(){
        String id = panel.idTextField.getText();
        Product result;
        result = Warehouse.getInstance().searchProduct(id);
        if (result == null) {
            panel.resultTextBox.append("Could not find product!\n");
        }
        else{
           panel.resultTextBox.append(result.toString() + "\n");
        }
        panel.idTextField.setText("");
    }

}
