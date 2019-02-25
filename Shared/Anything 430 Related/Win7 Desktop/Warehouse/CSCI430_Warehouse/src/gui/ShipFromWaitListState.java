package gui;
import warehouse.*;
import java.io.*;
import java.util.Iterator;
import java.lang.String;
public class ShipFromWaitListState extends WarehouseState{

    private static ShipFromWaitListState instance;

    Iterator onHold;        // items on hold for the current product
    Product product;
    int quantity;

    private ShipFromWaitListState(){

    }

    public static ShipFromWaitListState instance() {
        if ( instance == null )
            return instance = new ShipFromWaitListState();
        return instance;
    }

    ShipFromWaitListPanel panel = new ShipFromWaitListPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void confirmPressed() {
        System.out.println("Not yet implemented");
//
//        String prodID = panel.productIDField.getText();
//        
//        product = (Product) Warehouse.instance().searchProduct(prodID);
//        if ((product == null)) {
//            panel.ResultTextBox.append("Could not find product" + prodID + ". Shipment was not processed.");
//            return;
//        }
//        quantity = product.getQuantity();
//        panel.ResultTextBox.append("There are currently " + quantity + " items in stock for product " + product.getId() + "\n");
//
//        onHold = Warehouse.instance().getWaitListOrders(prodID);
//
//        if ( onHold.hasNext() && quantity > 0 ){
//
//            panel.backButton1.setEnabled(false);
//            panel.logoutButton.setEnabled(false);
//            panel.productIDField.setEnabled(false);
//            panel.confirmButton.setEnabled(false);
//            panel.yesButton.setVisible(true);
//            panel.noButton.setVisible(true);
//
//            currentItem = (WaitItem) onHold.next();
//            panel.ResultTextBox.append( currentItem.toString() + "\n");
//            panel.ResultTextBox.append( "Ship this partial order?\n");
//        }
//        else if( quantity <= 0 )
//            panel.ResultTextBox.append( "Ran out of stock for product " + product.getId() + "\n\n");
//        else
//            panel.ResultTextBox.append( "All waitlisted items for product " + product.getId() + " have been processed\n\n");
    }

    public void yesPressed(){
        System.out.println("Not yet implemented");
//        // ship the wait item and update quantity in
//        int numShipped = Warehouse.instance().shipFromWaitList(currentItem.getOrder().getID(),
//                currentItem.getProduct().getId(), quantity );
//
//        quantity -= numShipped;
//        Warehouse.instance().removeQuantity(product.getId(), numShipped);
//
//        panel.ResultTextBox.append( numShipped + " items were shipped for order " + currentItem.getOrder().getID() + "\n\n" );
//
//        // get next item, if applicable
//        if( onHold.hasNext() && quantity > 0 ){
//            currentItem = (WaitItem) onHold.next();
//            panel.ResultTextBox.append( currentItem.toString() + "\n");
//            panel.ResultTextBox.append( "Ship this partial order?\n");
//        }
//        else{
//            if( quantity <= 0 )
//                panel.ResultTextBox.append( "Ran out of stock for product " + product.getId() + "\n\n");
//            else
//                panel.ResultTextBox.append( "All waitlisted items for product " + product.getId() + " have been processed\n\n");
//            
//            panel.backButton1.setEnabled(true);
//            panel.logoutButton.setEnabled(true);
//            panel.productIDField.setEnabled(true);
//            panel.productIDField.setText("");
//            panel.confirmButton.setEnabled(true);
//            panel.yesButton.setVisible(false);
//            panel.noButton.setVisible(false);
//        }
    }

    public void noPressed(){
        System.out.println("Not yet implemented");
//        if( onHold.hasNext() && quantity > 0 ){
//            currentItem = (WaitItem) onHold.next();
//            panel.ResultTextBox.append( currentItem.toString() + "\n");
//            panel.ResultTextBox.append( "Ship this partial order?\n");
//        }
//        else{
//            if( quantity <= 0 )
//                panel.ResultTextBox.append( "Ran out of stock for product " + product.getId() + "\n\n");
//            else
//                panel.ResultTextBox.append( "All waitlisted items for product " + product.getId() + " have been processed\n\n");
//           
//            panel.backButton1.setEnabled(true);
//            panel.logoutButton.setEnabled(true);
//            panel.productIDField.setEnabled(true);
//            panel.productIDField.setText("");
//            panel.confirmButton.setEnabled(true);
//            panel.yesButton.setVisible(false);
//            panel.noButton.setVisible(false);
//        }
    }
}
