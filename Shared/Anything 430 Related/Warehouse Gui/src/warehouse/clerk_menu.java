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
public class clerk_menu extends JFrame{

    private final int HEIGHT = 500;
    private final int WIDTH = 600;

    private JPanel [] panel;
    private JPanel [] panel1;
    private JPanel mainpanel;
    private JLabel clerknamelabel, orderreceivedlabel, pendinglabel, clerkidlabel;
    private JButton allproduct, allmanufacture, allclients, receiveorder, viewhistory, update;
    private JButton switchuser, logout;
    
    public clerk_menu(int x)
    {
        setTitle("Clerk Menu");
        setSize(WIDTH, HEIGHT);

        buildPanel(x);
        add(mainpanel);
        setVisible(true);

    }

    public void buildPanel(int x)
    {
    clerknamelabel = new JLabel("Clerk Name");
    clerkidlabel = new JLabel("Clerk ID");
    orderreceivedlabel = new JLabel("Order Received Today ");
    pendinglabel = new JLabel("Pending Order");

    allproduct  = new JButton("List all Products");
    allmanufacture = new JButton("List all Manufactures");
    allclients = new JButton("List all Clients");
    receiveorder = new JButton("Receive Order");
    viewhistory = new JButton("View Order History");
    update = new JButton("Updata Inventory");
    switchuser = new JButton("Switch to Client");
    logout = new JButton("Logout");

    logout.addActionListener(new logoutListener(x));
    switchuser.addActionListener(new switchListener(x));
    allproduct.addActionListener(new allproductListener());
    allmanufacture.addActionListener(new allmanufactureListener());
    allclients.addActionListener(new allclientListener());
    //receiveorder.addActionListener(new receiveorderListener());
    viewhistory.addActionListener(new historyListener());
    //update.addActionListener(new updateListener());

    allproduct.setPreferredSize(new Dimension(150,30));
    allmanufacture.setPreferredSize(new Dimension(155,30));
    allclients.setPreferredSize(new Dimension(150,30));
    receiveorder.setPreferredSize(new Dimension(150,30));
    viewhistory.setPreferredSize(new Dimension(150,30));
    update.setPreferredSize(new Dimension(150,30));
    switchuser.setPreferredSize(new Dimension(125,30));
    logout.setPreferredSize(new Dimension(100,30));


    JPanel [] panel = new JPanel[10];
    for(int i = 0; i < 10; i++)
        panel[i] = new JPanel();

    JPanel [] panel1 = new JPanel[5];
    for(int i = 0; i < 5; i++)
        panel1[i] = new JPanel();

    panel[0].add(clerknamelabel);
    panel[0].setLayout(new FlowLayout(FlowLayout.CENTER));
    

    panel[1].setLayout(new GridLayout(2,1));
    panel[1].setLayout(new FlowLayout(FlowLayout.CENTER));
    panel[1].add(orderreceivedlabel);
    panel[1].add(pendinglabel);
   

    panel[2].add(allproduct);
    panel[3].add(allmanufacture);
    panel[4].add(allclients);
    panel[5].add(receiveorder);
    panel[6].add(viewhistory);
    panel[7].add(update);
    panel[8].add(switchuser);
    panel[9].add(logout);


    panel1[0].add(panel[0]);
    panel1[0].add(panel[1]);
    panel1[0].setLayout(new GridLayout(2,1));
    panel1[1].add(panel[2]);
    panel1[1].add(panel[3]);
    panel1[1].add(panel[4]);
    panel1[1].add(panel[5]);
    panel1[1].add(panel[6]);
    panel1[1].add(panel[7]);
    //panel1[1].setLayout(new GridLayout(2, 3, 10, 0));
    panel1[1].setLayout(new FlowLayout(FlowLayout.CENTER));
    panel1[2].add(panel[8]);
    panel1[2].add(panel[9]);
    panel1[2].setLayout(new GridLayout(1,2));
    panel1[2].setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 30));

   
    mainpanel = new JPanel();
    mainpanel.setLayout(new GridLayout(3,1));
    mainpanel.add(panel1[0]);
    mainpanel.add(panel1[1]);
    mainpanel.add(panel1[2]);
    }

    private class logoutListener implements ActionListener
    {
        int m;
        public logoutListener(int n) {m = n;}
    public void actionPerformed(ActionEvent e)
        {
        setVisible(false);
        if(m == 0){
        usertype_panel mypanel;
        mypanel = new usertype_panel();}
        else if(m == 5)
        {
            manager_menu mypanel;
            mypanel = new manager_menu();
        }

    }
    }

   private class switchListener implements ActionListener
   {
    int j;
    public switchListener(int k) {j = k;}
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        login_panel mypanel = new login_panel(3);
    }
    }

      private class allproductListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
    {
        productlist mylist = new productlist(0);
        setVisible(false);
    }
    }

 private class allmanufactureListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
    {
        manufacturelist mylist = new manufacturelist(0);
        setVisible(false);
    }
    }

  private class allclientListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
    {
        clientlist mylist = new clientlist(0);
        setVisible(false);
    }
    }

  private class historyListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
        viewhistory myhistory = new viewhistory(1);
        setVisible(false);
      }
  }

}

