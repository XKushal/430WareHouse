package gui;
import javax.swing.JFrame;

public class WarehouseContext{

    private static WarehouseContext instance;
    private JFrame frame;
    private int[][] transitions;
    private WarehouseState[] states;

    private int currentState = 0;

    public static final int BACK = 0,
                            OPTION1 = 1,
                            OPTION2 = 2,
                            OPTION3 = 3,
                            OPTION4 = 4,
                            OPTION5 = 5,
                            OPTION6 = 6,
                            OPTION7 = 7,
                            OPTION8 = 8,
                            OPTION9 = 9,
                            OPTION10 = 10,
                            OPTION11 = 11,
                            OPTION12 = 12,
                            LOGOUT_AS_MANAGER = 13,
                            LOGOUT_AS_CLERK = 14,
                            LOGOUT_AS_CLIENT = 15,
                            BECOME_CLERK = 16,
                            BECOME_CLIENT = 17,
                            LOGIN_AS_MANAGER = 18,
                            LOGIN_AS_CLERK = 19,
                            LOGIN_AS_CLIENT = 20;

    public static final int LOGIN_STATE = 1,
                            MANAGER_MENU_STATE = 2,
                            ORDER_MANUFACTURER_STATE = 3,
                            MODIFY_SALE_PRICE_STATE = 4,
                            ADD_SUPPLIER_STATE = 5,
                            REMOVE_SUPPLIER_STATE = 6,
                            CLERK_MENU_STATE = 7,
                            LOAD_STATE = 8,
                            SAVE_STATE = 9,
                            RECEIVE_SHIPMENT_STATE = 10,
                            LIST_PRODUCTS_STATE = 11,
                            ADD_CLIENT_STATE = 12,
                            ADD_MANUFACTURER_STATE = 13,
                            ADD_PRODUCT_STATE = 14,
                            LIST_SUPPLIERS_STATE = 15,
                            PROCESS_ORDER_STATE = 16,
                            SHIP_WAITLIST_STATE = 17,
                            PROCESS_PAYMENT_STATE = 18,
                            LIST_CLIENTS_STATE = 19,
                            CLIENT_MENU_STATE = 20,
                            VIEW_TRANSACTIONS_STATE = 21,
                            PLACE_ORDER_STATE = 22,
                            CHECK_PRICE_STATE = 23;

    private WarehouseContext( int numStates, int numTransitions ) {
        frame = new JFrame("Warehouse");
        frame.setMinimumSize(new java.awt.Dimension(440, 470));
        frame.setMaximumSize(new java.awt.Dimension(440, 470));
        frame.setPreferredSize(new java.awt.Dimension(440, 470));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        states = new WarehouseState[numStates];
        transitions = new  int[numStates][numTransitions];

        for(int i = 0; i < numStates; i++)
            for(int j = 0; j < numTransitions; j++)
                transitions[i][j] = -1;
    }

    // used to create a new context
    // if context already exists, return null
    public static WarehouseContext instance( int numStates, int numTransitions) {
        if ( instance == null )
            return instance = new WarehouseContext(numStates,numTransitions);
        return null;
    }

    // used to access existing context.
    // if no context exists, null will be returned
    public static WarehouseContext instance() {
        return instance;
    }

    public JFrame getFrame(){
        return frame;
    }

    public boolean addState( WarehouseState state, int index ){
        try{
            states[index] = state;
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    // add a transition to the matrix
    public boolean addTransition( int from, int to, int event  ){
        try{
            transitions[from][event] = to;
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    // look up the new state, set it, and run it
    // if transition has not been added or event is not valid, remain in current
    // state and return false
    public boolean changeState( int event ){

        try{
            if ( transitions[currentState][event] == -1 )
                return false;
            currentState = transitions[currentState][event];
            states[currentState].run();
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    // start the context with a specific state
    public boolean start( int state ){

        currentState = state;
        try{
            states[state].run();
        }
        catch (Exception e){
            return false;
        }

        frame.setVisible(true);

        return true;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // create the context
                WarehouseContext context = WarehouseContext.instance(24, 21);

                // create the frame

                // LOGIN STATE
                context.addState(LoginState.instance(), LOGIN_STATE);
                context.addTransition(LOGIN_STATE, MANAGER_MENU_STATE, LOGIN_AS_MANAGER);
                context.addTransition(LOGIN_STATE, CLERK_MENU_STATE, LOGIN_AS_CLERK);
                context.addTransition(LOGIN_STATE, CLIENT_MENU_STATE, LOGIN_AS_CLIENT);


                // MANAGER MENU STATE
                context.addState(ManagerMenuState.instance(),MANAGER_MENU_STATE);
                context.addTransition(MANAGER_MENU_STATE, LOGIN_STATE, LOGOUT_AS_MANAGER);
                context.addTransition(MANAGER_MENU_STATE, ORDER_MANUFACTURER_STATE, OPTION1);
                context.addTransition(MANAGER_MENU_STATE, MODIFY_SALE_PRICE_STATE, OPTION2);
                context.addTransition(MANAGER_MENU_STATE, ADD_SUPPLIER_STATE, OPTION3);
                context.addTransition(MANAGER_MENU_STATE, REMOVE_SUPPLIER_STATE, OPTION4);
                context.addTransition(MANAGER_MENU_STATE, CLERK_MENU_STATE, BECOME_CLERK);

                // ORDER MANUFACTURER STATE
                context.addState(OrderManufacturerState.instance(), ORDER_MANUFACTURER_STATE);
                context.addTransition(ORDER_MANUFACTURER_STATE, MANAGER_MENU_STATE, BACK);
                context.addTransition(ORDER_MANUFACTURER_STATE, LOGIN_STATE, LOGOUT_AS_MANAGER);

                // MODIFY SALE PRICE STATE
                context.addState(ModifySalePriceState.instance(), MODIFY_SALE_PRICE_STATE);
                context.addTransition(MODIFY_SALE_PRICE_STATE, MANAGER_MENU_STATE, BACK);
                context.addTransition(MODIFY_SALE_PRICE_STATE, LOGIN_STATE, LOGOUT_AS_MANAGER);

                // ADD SUPPLIER STATE
                context.addState(AddSupplierState.instance(), ADD_SUPPLIER_STATE);
                context.addTransition(ADD_SUPPLIER_STATE, MANAGER_MENU_STATE, BACK);
                context.addTransition(ADD_SUPPLIER_STATE, LOGIN_STATE, LOGOUT_AS_MANAGER);

                // REMOVE SUPPLIER STATE
                context.addState(RemoveSupplierState.instance(), REMOVE_SUPPLIER_STATE);
                context.addTransition(REMOVE_SUPPLIER_STATE, MANAGER_MENU_STATE, BACK);
                context.addTransition(REMOVE_SUPPLIER_STATE, LOGIN_STATE, LOGOUT_AS_MANAGER);

                // PROCESS PAYMENT STATE
                context.addState(ProcessPaymentState.instance(), PROCESS_PAYMENT_STATE);
                context.addTransition(PROCESS_PAYMENT_STATE, CLIENT_MENU_STATE, BACK);
                context.addTransition(PROCESS_PAYMENT_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(PROCESS_PAYMENT_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // LIST OUTSTANDING BALANCES STATE
                context.addState(ListOutstandingState.instance(), LIST_CLIENTS_STATE);
                context.addTransition(LIST_CLIENTS_STATE, CLIENT_MENU_STATE, BACK);
                context.addTransition(LIST_CLIENTS_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(LIST_CLIENTS_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // from Anup
                // LOAD STATE
                context.addState(LoadDatabaseState.instance(), LOAD_STATE);
                context.addTransition(LOAD_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(LOAD_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(LOAD_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // SAVE STATE
                context.addState(SaveDatabaseState.instance(), SAVE_STATE);
                context.addTransition(SAVE_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(SAVE_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(SAVE_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // RECEIVE SHIPMENT STATE
                context.addState(ReceiveShipmentState.instance(), RECEIVE_SHIPMENT_STATE);
                context.addTransition(RECEIVE_SHIPMENT_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(RECEIVE_SHIPMENT_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(RECEIVE_SHIPMENT_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // LIST PRODUCTS STATE
                context.addState(ListProductsState.instance(), LIST_PRODUCTS_STATE);
                context.addTransition(LIST_PRODUCTS_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(LIST_PRODUCTS_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(LIST_PRODUCTS_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // LIST SUPPLIERS STATE
                context.addState(ListSuppliersState.instance(), LIST_SUPPLIERS_STATE);
                context.addTransition(LIST_SUPPLIERS_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(LIST_SUPPLIERS_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(LIST_SUPPLIERS_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // PROCESS ORDER STATE
                context.addState(ProcessOrderState.instance(), PROCESS_ORDER_STATE);
                context.addTransition(PROCESS_ORDER_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(PROCESS_ORDER_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(PROCESS_ORDER_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // SHIP WAITLIST STATE
                context.addState(ShipFromWaitListState.instance(), SHIP_WAITLIST_STATE);
                context.addTransition(SHIP_WAITLIST_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(SHIP_WAITLIST_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(SHIP_WAITLIST_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // PROCESS PAYMENT STATE
                context.addState(ProcessPaymentState.instance(), PROCESS_PAYMENT_STATE);
                context.addTransition(PROCESS_PAYMENT_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(PROCESS_PAYMENT_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(PROCESS_PAYMENT_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // LIST CLIENTS STATE
                context.addState(ListOutstandingState.instance(), LIST_CLIENTS_STATE);
                context.addTransition(LIST_CLIENTS_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(LIST_CLIENTS_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(LIST_CLIENTS_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                // CLERK MENU STATE
                context.addState(ClerkMenuState.instance(), CLERK_MENU_STATE);
                context.addTransition(CLERK_MENU_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);
                context.addTransition(CLERK_MENU_STATE, LOAD_STATE, OPTION1);
                context.addTransition(CLERK_MENU_STATE, SAVE_STATE, OPTION2);
                context.addTransition(CLERK_MENU_STATE, RECEIVE_SHIPMENT_STATE, OPTION3);
                context.addTransition(CLERK_MENU_STATE, LIST_PRODUCTS_STATE, OPTION4);
                context.addTransition(CLERK_MENU_STATE, ADD_CLIENT_STATE, OPTION5);
                context.addTransition(CLERK_MENU_STATE, ADD_MANUFACTURER_STATE, OPTION6);
                context.addTransition(CLERK_MENU_STATE, ADD_PRODUCT_STATE, OPTION7);
                context.addTransition(CLERK_MENU_STATE, LIST_SUPPLIERS_STATE, OPTION8);
                context.addTransition(CLERK_MENU_STATE, PROCESS_ORDER_STATE, OPTION9);
                context.addTransition(CLERK_MENU_STATE, SHIP_WAITLIST_STATE, OPTION10);
                context.addTransition(CLERK_MENU_STATE, PROCESS_PAYMENT_STATE, OPTION11);
                context.addTransition(CLERK_MENU_STATE, LIST_CLIENTS_STATE, OPTION12);
                context.addTransition(CLERK_MENU_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(CLERK_MENU_STATE, CLIENT_MENU_STATE, BECOME_CLIENT);

                //ADDED BY CODY ***********************************************//
                context.addTransition(LOGIN_STATE, CLIENT_MENU_STATE, LOGIN_AS_CLIENT);

                context.addState(AddClientState.instance(), ADD_CLIENT_STATE);
                context.addTransition(ADD_CLIENT_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(ADD_CLIENT_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(ADD_CLIENT_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                context.addState(AddManufacturerState.instance(), ADD_MANUFACTURER_STATE);
                context.addTransition(ADD_MANUFACTURER_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(ADD_MANUFACTURER_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(ADD_MANUFACTURER_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                context.addState(AddProductState.instance(), ADD_PRODUCT_STATE);
                context.addTransition(ADD_PRODUCT_STATE, LOGIN_STATE, LOGOUT_AS_CLERK);
                context.addTransition(ADD_PRODUCT_STATE, CLERK_MENU_STATE, BACK);
                context.addTransition(ADD_PRODUCT_STATE, MANAGER_MENU_STATE, LOGOUT_AS_MANAGER);

                context.addState(ClientMenuState.instance(),CLIENT_MENU_STATE);
                context.addTransition(CLIENT_MENU_STATE, LOGIN_STATE, LOGOUT_AS_CLIENT);
                context.addTransition(CLIENT_MENU_STATE, CLERK_MENU_STATE, LOGOUT_AS_CLERK);
                context.addTransition(CLIENT_MENU_STATE, CLERK_MENU_STATE, LOGOUT_AS_MANAGER);
                context.addTransition(CLIENT_MENU_STATE, VIEW_TRANSACTIONS_STATE, OPTION1);
                context.addTransition(CLIENT_MENU_STATE, PLACE_ORDER_STATE, OPTION2);
                context.addTransition(CLIENT_MENU_STATE, CHECK_PRICE_STATE, OPTION3);

                context.addState(CheckPriceState.instance(), CHECK_PRICE_STATE);
                context.addTransition(CHECK_PRICE_STATE, LOGIN_STATE, LOGOUT_AS_CLIENT);
                context.addTransition(CHECK_PRICE_STATE, CLIENT_MENU_STATE, BACK);
                context.addTransition(CHECK_PRICE_STATE, CLERK_MENU_STATE, LOGOUT_AS_CLERK);
                context.addTransition(CHECK_PRICE_STATE, CLERK_MENU_STATE, LOGOUT_AS_MANAGER);

                context.addState(ViewTransactionsState.instance(), VIEW_TRANSACTIONS_STATE);
                context.addTransition(VIEW_TRANSACTIONS_STATE, LOGIN_STATE, LOGOUT_AS_CLIENT);
                context.addTransition(VIEW_TRANSACTIONS_STATE, CLIENT_MENU_STATE, BACK);
                context.addTransition(VIEW_TRANSACTIONS_STATE, CLERK_MENU_STATE, LOGOUT_AS_CLERK);
                context.addTransition(VIEW_TRANSACTIONS_STATE, CLERK_MENU_STATE, LOGOUT_AS_MANAGER);

                context.addState(PlaceOrderState.instance(), PLACE_ORDER_STATE);
                context.addTransition(PLACE_ORDER_STATE, LOGIN_STATE, LOGOUT_AS_CLIENT);
                context.addTransition(PLACE_ORDER_STATE, CLIENT_MENU_STATE, BACK);
                context.addTransition(PLACE_ORDER_STATE, CLERK_MENU_STATE, LOGOUT_AS_CLERK);
                context.addTransition(PLACE_ORDER_STATE, CLERK_MENU_STATE, LOGOUT_AS_MANAGER);
                //*************************************************************//

                // start the context with LoginState
                context.start(LOGIN_STATE);

            }
        });
    }

    // Variables declaration - do not modify
    // End of variables declaration

}
