package gui;
public class LoginState extends WarehouseState{

    private static LoginState instance;
    private int loginChoice = -1;

    private LoginState(){

    }

    public static LoginState instance() {
        if ( instance == null )
            return  instance = new LoginState();
        return instance;
    }

    LoginPanel1 panel1 = new LoginPanel1();
    LoginPanel2 panel2 = new LoginPanel2();

    public void run()
    {
        // log out, then display first login panel
        Security.instance().logout();
        loginChoice = -1;
        changePanel(panel1);
    }

    public void continuePressed(String loginString){

        // record the type of user
        if( loginString.equals("Manager") )
            loginChoice = Security.MANAGER;
        else if(loginString.equals("Clerk"))
            loginChoice = Security.CLERK;
        else if(loginString.equals("Client"))
            loginChoice = Security.CLIENT;

        // switch to the next panel
        changePanel(panel2 = new LoginPanel2());
    }

    public void loginPressed(String name, String passwd){

        // attempt to login. if successfull, change state according to login type
        if( Security.instance().login(name, passwd, loginChoice)){

            switch( Security.instance().getLoginType() ){
                
                case Security.MANAGER:
                    context.changeState(WarehouseContext.LOGIN_AS_MANAGER);
                    break;
                case Security.CLERK:
                    context.changeState(WarehouseContext.LOGIN_AS_CLERK);
                    break;
                case Security.CLIENT:
                    context.changeState(WarehouseContext.LOGIN_AS_CLIENT);
                    break;
            }// end switch
        }// end if
        else{
            panel2.setMessage("User name and/or password was not recognized!");
            //context.changePanel(panel2);
        }

    }

    public void goBack(){
        changePanel(panel1 = new LoginPanel1() );
    }
}
