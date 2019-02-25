package gui;
import warehouse.*;

public class AddManufacturerState extends WarehouseState{
    private static AddManufacturerState instance;

    private AddManufacturerState(){

    }

    public static AddManufacturerState instance() {
        if ( instance == null )
            return instance = new AddManufacturerState();
        return instance;
    }

    AddManufacturerPanel panel = new AddManufacturerPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void confirmPressed(){
        String name = panel.nameTextField.getText();
        String address = panel.addressTextField.getText();
        String phone = panel.phoneTextField.getText();
        Manufacturer result;

        result = Warehouse.getInstance().addManufacturer(name);
        if (result == null) {
            panel.resultTextBox.append("Could not add manufacturer!\n");
        }
        else{
            panel.resultTextBox.append(result.toString() + "\n");
        }
        panel.nameTextField.setText("");
        panel.addressTextField.setText("");
        panel.phoneTextField.setText("");
    }

}
