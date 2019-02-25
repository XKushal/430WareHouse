package gui;
import warehouse.*;

public class RemoveSupplierState extends WarehouseState{

    private static RemoveSupplierState instance;

    private RemoveSupplierState(){

    }

    public static RemoveSupplierState instance() {
        if ( instance == null )
            return instance = new RemoveSupplierState();
        return instance;
    }

    RemoveSupplierPanel panel = new RemoveSupplierPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void removeSupplierPressed(){
        panel.resultTextBox.append("Not yet implemented");
//
//        if( verifyPassword() )
//        {
//            String manID = panel.manufacturerIDTextField.getText();
//            String prodID = panel.productIDTextField.getText();
//
//            Product result;
//            result = Warehouse.instance().removeFromSupplyList(prodID, manID);
//            if (result == null) {
//                panel.resultTextBox.append("Could not remove manufacturer from supply list for this product!\n");
//            } else {
//                panel.resultTextBox.append(manID + " was removed from supply list for product " + prodID + "\n");
//            }
//        }
//        else
//            panel.resultTextBox.append("Password was not recognized! Operation cancelled.\n");

    }

}
