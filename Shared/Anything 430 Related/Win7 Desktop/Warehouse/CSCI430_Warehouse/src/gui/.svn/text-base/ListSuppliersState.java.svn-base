package gui;
import warehouse.*;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListSuppliersState extends WarehouseState{

    private static ListSuppliersState instance;

    private ListSuppliersState(){

    }

    public static ListSuppliersState instance() {
        if ( instance == null )
            return  instance = new ListSuppliersState();
        return instance;
    }

    ListSuppliersPanel panel = new ListSuppliersPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
    }

     public void confirmPressed(){
         String prodID = panel.productIDField.getText();
         Product product = Warehouse.getInstance().searchProduct(prodID);
         if(product == null)
             panel.resultTextBox.append("Product not found.\n");
        //Get all the terms of this product
        List terms = product.getTerms();
        
        //Iterate through the list and display the man/price contained in all the terms
        for (ListIterator iterator = terms.listIterator(); iterator.hasNext(); ) {
          SupplierTerms supplyTerms = (SupplierTerms) iterator.next();
          panel.resultTextBox.append(supplyTerms.getManufacturer().getName() + "\t");
          panel.resultTextBox.append(supplyTerms.getCost() + "\n");
        }
     }
}
