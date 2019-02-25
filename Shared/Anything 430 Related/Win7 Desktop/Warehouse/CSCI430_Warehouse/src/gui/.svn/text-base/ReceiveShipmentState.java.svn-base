package gui;
import warehouse.*;
import java.io.*;
import java.util.Iterator;
import java.lang.String;
import java.util.List;
import java.util.ListIterator;
import javax.swing.*;
import java.awt.event.*;
public class ReceiveShipmentState extends WarehouseState{

    private static ReceiveShipmentState instance;

    Iterator onHold;        // items on hold for the current product
    int quantity;

    private ReceiveShipmentState(){

    }

    public static ReceiveShipmentState instance() {
        if ( instance == null )
            return instance = new ReceiveShipmentState();
        return instance;
    }

    ReceiveShipmentPanel panel = new ReceiveShipmentPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void confirmPressed() {
        String manId = panel.productIDField.getText();
        String orderId = panel.orderNumberField.getText();
        List backOrders = Warehouse.getInstance().receiveShipment(manId, orderId);
        if(backOrders == null)
            panel.ResultTextBox.append("Unable to receive shipment");
        else {
            for (ListIterator iterator = backOrders.listIterator(); iterator.hasNext();) {
                ClientOrder order = (ClientOrder) iterator.next();
                //Ask if they want to fill this order
                if (JOptionPane.showConfirmDialog(panel, "Would you like to fill this order?\n " + order.toString()) == 0) {
                    if(Warehouse.getInstance().processBackOrder(order))
                        break;
                }
            }
        }
    }

    public void yesPressed(){
         System.out.println("Not yet implemented.");
//        // ship the wait item and update quantity in
//        int numShipped = Warehouse.instance().shipFromWaitList(currentItem.getOrder().getID(),
//                currentItem.getProduct().getId(), quantity );
//
//        quantity -= numShipped;
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
//            if( Warehouse.instance().addQuantity(currentItem.getProduct().getId(), quantity) != null )
//                panel.ResultTextBox.append(quantity + " items added to stock for product " + currentItem.getProduct().toString() + "\n" );
//            else
//                panel.ResultTextBox.append("Error updating quantity for " + currentItem.getProduct().toString() + "\n" );
//            
//            panel.backButton1.setEnabled(true);
//            panel.logoutButton.setEnabled(true);
//            panel.productIDField.setEnabled(true);
//            panel.productIDField.setText("");
//            panel.quantityField.setEnabled(true);
//            panel.quantityField.setText("");
//            panel.confirmButton.setEnabled(true);
//            panel.yesButton.setVisible(false);
//            panel.noButton.setVisible(false);
//        }
    }

    public void noPressed(){
         System.out.println("Not yet implemented.");
//        if( onHold.hasNext() && quantity > 0 ){
//            currentItem = (WaitItem) onHold.next();
//            panel.ResultTextBox.append( currentItem.toString() + "\n");
//            panel.ResultTextBox.append( "Ship this partial order?\n");
//        }
//        else{
//
//            if( Warehouse.instance().addQuantity(currentItem.getProduct().getId(), quantity) != null )
//                panel.ResultTextBox.append(quantity + " items added to stock for product " + currentItem.getProduct().toString() );
//            else
//                panel.ResultTextBox.append("Error updating quantity for " + currentItem.getProduct().toString() );
//            panel.backButton1.setEnabled(true);
//            panel.logoutButton.setEnabled(true);
//            panel.productIDField.setEnabled(true);
//            panel.productIDField.setText("");
//            panel.quantityField.setEnabled(true);
//            panel.quantityField.setText("");
//            panel.confirmButton.setEnabled(true);
//            panel.yesButton.setVisible(false);
//            panel.noButton.setVisible(false);
//        }
    }

    void findManOrders() {
        panel.ResultTextBox.setText("");
        String manId = panel.productIDField.getText();
        String result = Warehouse.getInstance().showAllManufacturerOrders(manId);
        if(result == null)
            panel.ResultTextBox.append("Unable to find manufacturer");
        else
            panel.ResultTextBox.append(result);
    }
}