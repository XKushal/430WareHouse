package gui;
import warehouse.*;
import java.util.Iterator;

public class ListProductsState extends WarehouseState{

    private static ListProductsState instance;

    private ListProductsState(){

    }

    public static ListProductsState instance() {
        if ( instance == null )
            return instance = new ListProductsState();
        return instance;
    }

    ListProductsPanel panel = new ListProductsPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

    public void listProductsPressed(){
        Iterator products = Warehouse.getInstance().getAllProducts();
        while( products.hasNext() )
            panel.resultTextBox.append( products.next().toString() + "\n");
     }
}
