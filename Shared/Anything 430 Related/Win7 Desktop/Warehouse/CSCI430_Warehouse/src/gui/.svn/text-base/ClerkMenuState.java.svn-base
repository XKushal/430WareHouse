package gui;
import javax.swing.*;
import warehouse.*;

public class ClerkMenuState extends WarehouseState{

    private static ClerkMenuState instance;

    private ClerkMenuState(){

    }

    public static ClerkMenuState instance() {
        if ( instance == null )
            return instance = new ClerkMenuState();
        return instance;
    }

    ClerkMenuPanel panel = new ClerkMenuPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void loadPressed(){
        context.changeState(WarehouseContext.OPTION1);
    }

    public void savePressed(){
           context.changeState(WarehouseContext.OPTION2);
    }

    public void receiveShipmentPressed(){
           context.changeState(WarehouseContext.OPTION3);
    }

    public void listProductwithQuantityPressed(){
           context.changeState(WarehouseContext.OPTION4);
    }

    public void addClientPressed(){
           context.changeState(WarehouseContext.OPTION5);
    }

    public void addManufacturerPressed(){
           context.changeState(WarehouseContext.OPTION6);
    }

    public void addProductPressed(){
           context.changeState(WarehouseContext.OPTION7);
    }

    public void listManufacturerforProductPressed(){
           context.changeState(WarehouseContext.OPTION8);
    }

    public void processClientOrderPressed(){
           context.changeState(WarehouseContext.OPTION9);
    }

    public void shipfromWaitlistPressed(){
           context.changeState(WarehouseContext.OPTION10);
    }

    public void processPaymentPressed(){
           context.changeState(WarehouseContext.OPTION11);
    }

    public void listClientwithOutstandingBalancePressed(){
           context.changeState(WarehouseContext.OPTION12);
    }

    public void becomeClientPressed(){
        if( verifyPassword() )
        {
            // create a message dialog box to input the clientID
            JTextField id = new JTextField();
            final JComponent[] inputs = new JComponent[] {
                    new JLabel("Client ID"),
                    id
            };
            JOptionPane.showMessageDialog(null, inputs, "Enter Client ID", JOptionPane.PLAIN_MESSAGE);

            if( Warehouse.getInstance().searchClient( id.getText() ) != null )   // verify client exists
            {
                Security.instance().becomeClient( id.getText() );             // update Security object
                context.changeState(WarehouseContext.BECOME_CLIENT);
            }
            else
                JOptionPane.showMessageDialog(null, "Client not found!", "Error!", JOptionPane.PLAIN_MESSAGE );
        }
    }

    
}
