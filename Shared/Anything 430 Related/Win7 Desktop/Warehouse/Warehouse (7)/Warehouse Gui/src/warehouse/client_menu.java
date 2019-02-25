/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package warehouse;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author kunga
 */
public class client_menu extends JFrame{
    private JButton viewaccount, placeorder, viewhistory, logout;
    private JLabel clientname, clientid;
    private JPanel toppanel, mainpanel;
    private JPanel [] panel;
    private final int HEIGHTS = 500;
    private final int WIDTHS = 600;

    public client_menu(int k)
    {
        setTitle("Client Menu");
        setSize(WIDTHS, HEIGHTS);

        buildPanel(k);
        add(mainpanel);
        setVisible(true);
    }

    public void buildPanel(int j)
    {
        viewaccount = new JButton("View Account");
        placeorder = new JButton("Place order");
        viewhistory = new JButton("View Order History");
        logout = new JButton("Logout");
        clientname = new JLabel("Client Name ");
        clientid = new JLabel("Client ID");

        logout.addActionListener(new logoutListener(j));
        viewaccount.addActionListener(new viewListener());
        placeorder.addActionListener(new orderListener());
        viewhistory.addActionListener(new historyListener());

        viewaccount.setPreferredSize(new Dimension(160, 50));
        placeorder.setPreferredSize(new Dimension(160, 50));
        viewhistory.setPreferredSize(new Dimension(160, 50));
        logout.setPreferredSize(new Dimension(160, 50));
        clientid.setPreferredSize(new Dimension(160, 50));
        clientname.setPreferredSize(new Dimension(160, 50));

        JPanel [] panel = new JPanel[6];
        for(int i = 0; i < 6; i++)
            panel[i] = new JPanel();

        panel[0].add(clientid);
        panel[1].add(clientname);
        panel[2].add(viewaccount);
        panel[3].add(placeorder);
        panel[4].add(viewhistory);
        panel[5].add(logout);

        toppanel = new JPanel();
        toppanel.add(panel[0]);
        toppanel.add(panel[1]);
        toppanel.setLayout(new GridLayout(1,2));

        mainpanel = new JPanel();

        mainpanel.add(toppanel);
        
        for(int i = 2; i < 6; i++)
            mainpanel.add(panel[i]);

        mainpanel.setLayout(new GridLayout(5,1));

    }

    private class viewListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
        viewAccount myview = new viewAccount();
        setVisible(false);
    }
    }

    private class orderListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
        placeorder myorder = new placeorder();
    }
    }

    private class logoutListener implements ActionListener
    {
        int i;
        public logoutListener(int k)
        {
        i = k;
        }

    public void actionPerformed(ActionEvent e)
        {
        setVisible(false);
        
        if(i == 0)
        {
            usertype_panel mypanel;
           mypanel = new usertype_panel();
            }
        else if(i == 3)
        {
        clerk_menu mypanel;
        mypanel = new clerk_menu(3);
        }
        else if(i == 6)
        {
            manager_menu mypanel;
            mypanel = new manager_menu();}
        }
    }

    private class historyListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
        viewhistory myview = new viewhistory(0);
        setVisible(false);
    }
    }


}
