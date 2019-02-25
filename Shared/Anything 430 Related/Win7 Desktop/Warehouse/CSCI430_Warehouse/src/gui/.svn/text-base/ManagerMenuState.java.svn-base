package gui;
public class ManagerMenuState extends WarehouseState{

    private static ManagerMenuState instance;

    private ManagerMenuState(){

    }

    public static ManagerMenuState instance() {
        if ( instance == null )
            return instance = new ManagerMenuState();
        return instance;
    }

    ManagerMenuPanel panel = new ManagerMenuPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void orderManufacturerPressed(){
        context.changeState(WarehouseContext.OPTION1);
    }

    public void modifyPricePressed(){
        context.changeState(WarehouseContext.OPTION2);
    }

    public void addSupplierPressed(){
        context.changeState(WarehouseContext.OPTION3);
    }

    public void removeSupplierPressed(){
        context.changeState(WarehouseContext.OPTION4);
    }

    public void becomeClerkPressed(){
        if( verifyPassword() )
            context.changeState(WarehouseContext.BECOME_CLERK);
    }

}
