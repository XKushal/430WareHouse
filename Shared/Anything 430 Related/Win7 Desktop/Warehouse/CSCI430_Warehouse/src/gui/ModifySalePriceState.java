package gui;
import warehouse.*;

public class ModifySalePriceState extends WarehouseState{

    private static ModifySalePriceState instance;

    private ModifySalePriceState(){

    }

    public static ModifySalePriceState instance() {
        if ( instance == null )
            return instance = new ModifySalePriceState();
        return instance;
    }

    ModifySalePricePanel panel = new ModifySalePricePanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void changePricePressed(){
         panel.resultTextBox.append("Not yet implemented.");
//        if( verifyPassword() )
//        {
//            String id = panel.productIDTextField.getText();
//            double price = 0.0;
//            boolean valid = true;
//            try{
//                price = Double.parseDouble(panel.priceTextField.getText());
//            } catch(Exception e){
//                panel.resultTextBox.append("Please enter a valid price!\n");
//                valid = false;
//            }
//            Product result = null;
//            if(valid){
//                result = Warehouse.instance().changePrice(id, price);
//            }
//            if (result == null || price < 0.0) {
//                panel.resultTextBox.append("Could not change price of product " + id + "!\n");
//            }
//            else{
//                panel.resultTextBox.append(result.toString() + "\n");
//            }
//            panel.productIDTextField.setText("");
//            panel.priceTextField.setText("");
//        }
//        else
//            panel.resultTextBox.append("Password was not recognized! Operation cancelled.\n");
    }

}
