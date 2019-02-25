package gui;
import warehouse.*;

public class AddProductState extends WarehouseState{

    private static AddProductState instance;

    private AddProductState(){

    }

    public static AddProductState instance() {
        if ( instance == null )
            return instance = new AddProductState();
        return instance;
    }

    AddProductPanel panel = new AddProductPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void confirmPressed(){
        String name = panel.nameTextField.getText();
        String description = panel.descTextField.getText();
        boolean valid = true;
        float price = -1;
        try{
            price = Float.parseFloat(panel.priceTextField.getText());
        } catch(Exception e){
            panel.resultTextBox.append("Please enter a valid price!\n");
            valid = false;
        }
        Product result = null;
        if(valid){
            result = Warehouse.getInstance().addProduct(name, description, price);
        }
        if (result == null) {
            panel.resultTextBox.append("Could not add product!\n");
        }
        else{
            panel.resultTextBox.append(result.toString() + "\n");
        }
        panel.nameTextField.setText("");
        panel.descTextField.setText("");
        panel.priceTextField.setText("");
    }
}
