package gui;
import javax.swing.*;

public abstract class WarehouseState {

    protected static WarehouseContext context;
    protected static JFrame frame;

    protected WarehouseState(){
        context = WarehouseContext.instance();
        frame = WarehouseContext.instance().getFrame();
    }

    public abstract void run();

    public void changePanel(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.repaint();
    }

    public void logoutPressed(){
        if( Security.instance().getLoginType() == Security.MANAGER)
            context.changeState(WarehouseContext.LOGOUT_AS_MANAGER);
        else if (Security.instance().getLoginType() == Security.CLERK)
            context.changeState(WarehouseContext.LOGOUT_AS_CLERK);
        else
            context.changeState(WarehouseContext.LOGOUT_AS_CLIENT);
    }

    public void backPressed(){
        context.changeState(WarehouseContext.BACK);
    }

    protected boolean verifyPassword(){

        // create a message dialog box to verify the password
        JPasswordField password = new JPasswordField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Password"),
                password
        };
        JOptionPane.showMessageDialog(null, inputs, "Confirm Password", JOptionPane.PLAIN_MESSAGE);

        if( Security.instance().confirmPassword( String.valueOf( password.getPassword() ) ) )
            return true;

        JOptionPane.showMessageDialog(null, "Invalid Password! Operation cancelled.", "Error!", JOptionPane.PLAIN_MESSAGE );
        return false;
    }
}
