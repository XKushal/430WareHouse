package gui;
import warehouse.*;

public class AddSupplierState extends WarehouseState{

    private static AddSupplierState instance;

    private AddSupplierState(){

    }

    public static AddSupplierState instance() {
        if ( instance == null )
            return instance = new AddSupplierState();
        return instance;
    }

    AddSupplierPanel panel = new AddSupplierPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void confirmPressed(){

        if( verifyPassword() )
        {
            float price = 0;
            try{
                price = Float.parseFloat(panel.priceTextField.getText());
            } catch (Exception e) {
                panel.resultTextBox.append("Please enter a valid price!\n");
            }
            String manID = panel.manufacturerIDTextField.getText();
            String prodID = panel.productIDTextField.getText();

            SupplierTerms result;
            result = Warehouse.getInstance().addTerms(manID, prodID, price);

            if (result == null)
                panel.resultTextBox.append("Could not add manufacturer to supply list for this product!\n");
            else
                panel.resultTextBox.append(manID + " was added to supply list for product " + prodID + "\n");
        }
        else
            panel.resultTextBox.append("Password was not recognized! Operation cancelled.\n");
    }

}
