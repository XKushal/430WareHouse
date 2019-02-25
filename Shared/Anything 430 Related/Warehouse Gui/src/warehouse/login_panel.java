/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package warehouse;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 *
 * @author kunga
 */
public class login_panel extends JFrame{
    private JLabel loginlabel, passwordlabel, clientlabel, clerklabel, managerlabel;
    private JTextField loginfield;
    private JPasswordField passwordfield;

    private JPanel loginlabelpanel, loginfieldpanel, passwordlabelpanel, passwordfieldpanel, loginbuttonpanel, cancelbuttonpanel;
    private JPanel mainpanel, messagepanel, panel1, panel2, panel3, panel4;
    private JButton loginButton, cancelButton;

    
    private final int HEIGHTS = 400;
    private final int WIDTHS = 500;

    public login_panel(int i)
    {
    setTitle("Warehouse System");
    setSize(WIDTHS, HEIGHTS);
    buildPanel(i);

    add(mainpanel);
    setVisible(true);

    }
    
    public void buildPanel(int i)
    {
        loginlabel = new JLabel("Username");
        passwordlabel = new JLabel("Password");
        loginfield = new JTextField(10);
        passwordfield = new JPasswordField(10);

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new cancelListener());

        loginButton.addActionListener(new loginListener(i));



        clientlabel = new JLabel("Client Login");
        clerklabel = new JLabel("Clerk Login");
        managerlabel = new JLabel("Manager Login");

        Font font = new Font("Serif", Font.BOLD, 30);
        clientlabel.setFont(font);
        clerklabel.setFont(font);
        managerlabel.setFont(font);

        messagepanel = new JPanel();

        if((i == 0 || i == 3) || i == 6)
        messagepanel.add(clientlabel);
        else if(i == 1 || i == 5)
        messagepanel.add(clerklabel);
        else if(i == 2 || i == 4)
        messagepanel.add(managerlabel);

        loginlabelpanel = new JPanel();
        loginlabelpanel.add(loginlabel);
        loginfieldpanel = new JPanel();
        loginfieldpanel.add(loginfield);

        passwordlabelpanel = new JPanel();
        passwordlabelpanel.add(passwordlabel);
        passwordfieldpanel = new JPanel();
        passwordfieldpanel.add(passwordfield);

        loginbuttonpanel = new JPanel();
        loginbuttonpanel.add(loginButton);
        cancelbuttonpanel = new JPanel();
        cancelbuttonpanel.add(cancelButton);

        panel1 = new JPanel();
        panel1.add(messagepanel);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel2 = new JPanel();
        panel2.add(loginlabelpanel);
        panel2.add(loginfieldpanel);
        panel2.setLayout(new GridLayout(1,2));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel3 = new JPanel();
        panel3.add(passwordlabelpanel);
        panel3.add(passwordfieldpanel);
        panel3.setLayout(new GridLayout(2,1));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel4 = new JPanel();
        panel4.add(loginbuttonpanel);
        panel4.add(cancelbuttonpanel);
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER));

        mainpanel = new JPanel();
        mainpanel.add(panel1);
        mainpanel.add(panel2);
        mainpanel.add(panel3);
        mainpanel.add(panel4);

        mainpanel.setLayout(new GridLayout(4,1));
     
    }

    private class cancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           usertype_panel mypanel = new usertype_panel();
           setVisible(false);
        }
    }

    private class loginListener implements ActionListener
    {   int x;
        public loginListener(int m)
        {
        x = m;
        }
        public void actionPerformed(ActionEvent e)
        {
         setVisible(false);
         if( x == 0)
         {
         client_menu abc;
         abc = new client_menu(0);
         }
         else if(x == 1)
         {
         clerk_menu abc;
         abc = new clerk_menu(1);
         }
         else if(x == 2)
         {
         manager_menu abc;
         abc = new manager_menu();
         }
         else if(x == 3)
         {
         client_menu abc;
         abc = new client_menu(3);
         }
         else if(x == 4)
         {
         clerk_menu abc;
         abc = new clerk_menu(4);
         }
         else if(x == 5)
         {
             clerk_menu abc;
             abc = new clerk_menu(5);
         }
         else if(x == 6)
         {
            client_menu abc;
            abc = new client_menu(6);
         }


        }
    }




}