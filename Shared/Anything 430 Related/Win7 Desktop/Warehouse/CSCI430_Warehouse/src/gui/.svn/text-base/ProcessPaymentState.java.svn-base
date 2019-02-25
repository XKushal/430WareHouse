package gui;
import warehouse.*;

public class ProcessPaymentState extends WarehouseState{

    private static ProcessPaymentState instance;
    Client c;
    
    private ProcessPaymentState(){

    }

    public static ProcessPaymentState instance() {
        if ( instance == null )
            return instance = new ProcessPaymentState();
        return instance;
    }

    ProcessPaymentPanel panel = new ProcessPaymentPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void submitPressed(){
        String clientID = panel.clientIDTextField.getText();

        c = Warehouse.getInstance().searchClient(clientID);

       if (c == null) {
            panel.resultTextBox.append("Could not find Client!\n");
        }
       else{
           panel.PriceTextField.setEnabled(true);
           panel.processPaymentButton.setEnabled(true);
           panel.clientIDTextField.setEnabled(false);
           panel.SubmitButton.setEnabled(false);
        }
    }

    public void confirmPressed(){
         panel.resultTextBox.append("Not yet implemented.");
//
//        String amount = panel.PriceTextField.getText();
//        double paymentAmt = 0.0;
//
//        try{
//            paymentAmt = Double.parseDouble(amount);
//        }
//        catch (Exception e) {
//            panel.resultTextBox.append("Please enter a valid amount!\n");
//            return;
//        }
//
//        if( paymentAmt < 0.0 ){
//            panel.resultTextBox.append("Please enter a valid amount!\n");
//            return;
//        }
//
//        Warehouse.getInstance().acceptPayment(c.getId(), paymentAmt);
//
//        panel.resultTextBox.append("payment of $" + amount + " was received for client " + c.getId() +", "
//                      + c.getName() + ". New balance: $" + c.getBalance() + "\n");
//
//        panel.processPaymentButton.setEnabled(false);
//        panel.PriceTextField.setEnabled(false);
//        panel.clientIDTextField.setEnabled(true);
//        panel.SubmitButton.setEnabled(true);
//        panel.clientIDTextField.setText("");
//        panel.PriceTextField.setText("");
    }

}
