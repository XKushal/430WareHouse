package gui;
import java.util.List;
import java.util.ListIterator;
import warehouse.*;

public class OrderManufacturerState extends WarehouseState{

    private static OrderManufacturerState instance;
    private static Manufacturer manufacturer;
    private static double orderCost = 0.0;
    private static String orderForm;

    private OrderManufacturerState(){

    }

    public static OrderManufacturerState instance() {
        if ( instance == null )
            return instance = new OrderManufacturerState();
        return instance;
    }

    OrderManufacturerPanel panel = new OrderManufacturerPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void submitPressed(){
        String manufID = panel.manufacturerTextField.getText();
        manufacturer = Warehouse.getInstance().searchManufacturer(manufID);

        if(manufacturer == null){
            panel.manufacturerTextField.setText("");
            panel.resultTextBox.append("Could not find manufacturer " + manufID + ". Order will not be placed.\n");
            return;
        }
        else{
            panel.manufacturerTextField.setEditable(false);
            panel.submitButton.setEnabled(false);

            panel.productTextField.setEditable(true);
            panel.quantityTextField.setEditable(true);
            panel.productTextField.setEnabled(true);
            panel.quantityTextField.setEnabled(true);
            panel.addToOrderButton.setEnabled(true);
            panel.completeOrderButton.setEnabled(true);

            orderCost = 0.0;
            orderForm = "Order for " + manufacturer.getName() + "\n\nProduct\tID\tQuantity\tCost per unit\tTotal Cost\n"
              + "-------------------------------------------------------------------------------------\n";
        }
    }

    public void addToOrderPressed(){
        String prodID = panel.productTextField.getText();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(panel.quantityTextField.getText());
        } catch (Exception e) {
            panel.resultTextBox.append("Please enter a valid quantity!\n");
            return;
        }
        
        try {
            Product product = Warehouse.getInstance().searchProduct(prodID); //Find the product
            
            if(!product.isProducedByMan(manufacturer)) {
                throw new Exception("Manufacturer " + manufacturer.getName() + " does not product product " + prodID + ".\n");
            }
            if(!manufacturer.producesProduct(product)) {
                throw new Exception("Manufacturer " + manufacturer.getName() + " does not product product " + prodID + ".\n");
            }
            
            // get the supplier price, create a new line on the order form, update total orderCost
            List supplierTerms = product.getTerms();
            float supplierPrice = 0;
            for (ListIterator iterator = supplierTerms.listIterator(); iterator.hasNext(); ) {
                SupplierTerms terms = (SupplierTerms) iterator.next();
                if(terms.getManufacturer().equals(manufacturer)) {
                    supplierPrice = terms.getCost();
                    break;
                }
            }
            orderForm += product.getName() + "\t" + product.getId() + "\t" + quantity + "\t$" + supplierPrice + "\t$"
                    + (supplierPrice * quantity) + "\n";
            orderCost += supplierPrice * quantity;
            panel.resultTextBox.append("Product " + prodID + " was added to the order!\n");
            ManufacturerOrder order = new ManufacturerOrder(manufacturer, product, quantity); //Create new order object
            if(!(manufacturer.placeManufacturerOrder(order) && product.placeManufacturerOrder(order))) {
                panel.resultTextBox.append("Something went very wrong.\n");
            }
        } catch (Exception e) {
            panel.resultTextBox.append("Product " + prodID + " was not added to the order!\n");
        }
        
        panel.productTextField.setText("");
        panel.quantityTextField.setText("");

    }

    public void completeOrderPressed(){

        if( verifyPassword() )
        {
            orderForm += "\n-------------------------------------------------------------------------------------\n\n"
                  + "Total cost for this order: $" + orderCost;
            // output the order form
            panel.resultTextBox.append(orderForm + "\n");
            orderForm = "";
            orderCost = 0.0;

            panel.manufacturerTextField.setEditable(true);
            panel.manufacturerTextField.setText("");
            panel.submitButton.setEnabled(true);

            panel.productTextField.setText("");
            panel.quantityTextField.setText("");
            panel.productTextField.setEditable(false);
            panel.quantityTextField.setEditable(false);
            panel.addToOrderButton.setEnabled(false);
            panel.completeOrderButton.setEnabled(false);
        }
        else
            panel.resultTextBox.append("Password was not recognized! Operation cancelled.\n");
    }
}
