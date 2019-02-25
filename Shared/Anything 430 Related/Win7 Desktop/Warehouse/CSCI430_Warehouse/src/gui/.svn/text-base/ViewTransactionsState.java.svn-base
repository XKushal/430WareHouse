package gui;
import warehouse.*;
import java.util.*;

public class ViewTransactionsState extends WarehouseState{
    private static ViewTransactionsState instance;
    private int type = 0;

    private ViewTransactionsState(){

    }

    public static ViewTransactionsState instance() {
        if ( instance == null )
            return instance = new ViewTransactionsState();
        return instance;
    }

    ViewTransactionsPanel panel = new ViewTransactionsPanel();

    public void run()
    {
        panel.setup();
        changePanel(panel);
        panel.resultTextBox.setText("");
    }

    public void paymentsPressed(){
        System.out.println("Not yet implemented");
//        panel.resultTextBox.setText("");
//        type = Transaction.PAYMENT;
//        printTransactions();
    }

    public void invoicesPressed(){
        System.out.println("Not yet implemented");
//        panel.resultTextBox.setText("");
//        type = Transaction.INVOICE;
//        printTransactions();
    }

    public void ordersPressed(){
        String clientId = Security.instance().getClientID();
        if(Warehouse.getInstance().showAllClientOrders(clientId) == null)
            panel.resultTextBox.append("Unable to find client");
        else
            panel.resultTextBox.append(Warehouse.getInstance().showAllClientOrders(clientId));
    }

    public void printTransactions(){
        System.out.println("Not yet implemented");
//        String clientID = Security.instance().getClientID();
//        Client c = Warehouse.instance().searchClient(clientID);
//
//        Iterator trans = Warehouse.instance().getTransactionsForClient(clientID);
//        if(type == Transaction.ORDER)
//            panel.resultTextBox.append("Orders for client " + c.getId() +
//                    ", " + c.getName() + ": \n");
//        else if(type == Transaction.PAYMENT)
//            panel.resultTextBox.append("Payments for client " + c.getId() +
//                    ", " + c.getName() + ": \n");
//        else if(type == Transaction.INVOICE)
//            panel.resultTextBox.append("Invoices for client " + c.getId() +
//                    ", " + c.getName() + ": \n");
//        while (trans.hasNext()) {
//            Transaction t = (Transaction) trans.next();
//            if(type == t.getType())
//                panel.resultTextBox.append(t.toString() + "\n");
//        }
    }

}
