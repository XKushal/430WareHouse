package gui;
import warehouse.*;

public class AddClientState extends WarehouseState{
    private static AddClientState instance;

    private AddClientState(){

    }

    public static AddClientState instance() {
        if ( instance == null )
            return instance = new AddClientState();
        return instance;
    }

    AddClientPanel panel = new AddClientPanel();

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
        Client result;
        //System.out.println(name + address + phone);
        result = Warehouse.getInstance().addMember(name, address, phone);
        if (result == null) {
            panel.resultTextBox.append("Could not add client!\n");
        }
        else{
            panel.resultTextBox.append(result.toString() + "\n");
        }
        panel.nameTextField.setText("");
        panel.addressTextField.setText("");
        panel.phoneTextField.setText("");
    }

}
