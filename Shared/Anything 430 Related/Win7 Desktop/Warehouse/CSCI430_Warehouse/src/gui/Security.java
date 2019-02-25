package gui;
import warehouse.*;

public class Security {

    // code to implement this class as a singleton
    private static Security instance;
    protected Security() throws Exception{
        if( getClass().getName().equals("Security"))
            throw new Exception();
    }
    private Security(int i){
    }    
    public static Security instance(){
        if( instance == null )
            return instance = new Security(1);
        return instance;
    }
    
    // login types
    public static final int LOGGED_OUT = -1, MANAGER = 0, CLERK = 1, CLIENT = 2;
    private String loginName = "";
    private String clientID = "";
    
    private int loginType = LOGGED_OUT;
    
    public boolean login( String name, String passwd, int type ){
        
        if( loginType != LOGGED_OUT )
            return false;

        switch( type ){
            
            case MANAGER:
                // check for valid manager password
                if( name.equals("manager") && passwd.equals("manager")){
                    loginType = MANAGER;
                    loginName = name;
                    return true;
                }
                break;
                
            case CLERK:
                // check for valid sales clerk password
                if( name.equals("clerk") && passwd.equals("clerk")){
                    loginType = CLERK;
                    loginName = name;
                    return true;
                }
                break;
                
            case CLIENT:
                // make sure client exists. if so, make sure name & pw are identical
                if( Warehouse.getInstance().searchClient(name) != null && name.equals(passwd)){
                    loginType = CLIENT;
                    clientID = name;
                    loginName = "client " + name;
                    return true;
                }
                //break;

        }// end switch

        return false;   // return false if login failed
    }

    public boolean confirmPassword( String passwd ){

        switch( loginType ){

            case MANAGER:
                // check for valid manager password
                if( passwd.equals("manager"))
                    return true;
                break;

            case CLERK:
                // check for valid sales clerk password
                if( passwd.equals("clerk"))
                    return true;
                break;

            case CLIENT:
                // check for valid client password
                if( passwd.equals(loginName))
                    return true;
                break;
        }// end switch

        return false;

    }

    public void becomeClient( String id ){
        clientID = id;
    }

    public void logout(){
        loginType = LOGGED_OUT;
        loginName = "";
    }

    public int getLoginType(){
        return loginType;
    }

    public String getLoginString(){
        return loginName;
    }

    public String getClientID(){
        return clientID;
    }

}
