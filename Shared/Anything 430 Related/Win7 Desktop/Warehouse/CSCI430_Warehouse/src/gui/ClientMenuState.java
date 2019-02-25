package gui;
public class ClientMenuState extends WarehouseState{

    private static ClientMenuState instance;

    private ClientMenuState(){

    }

    public static ClientMenuState instance() {
        if ( instance == null )
            return instance = new ClientMenuState();
        return instance;
    }

    ClientMenuPanel panel = new ClientMenuPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void viewTransactionsPressed(){
        context.changeState(WarehouseContext.OPTION1);
    }

    public void placeOrderPressed(){
        context.changeState(WarehouseContext.OPTION2);
    }

    public void checkPricePressed(){
        context.changeState(WarehouseContext.OPTION3);
    }

}
