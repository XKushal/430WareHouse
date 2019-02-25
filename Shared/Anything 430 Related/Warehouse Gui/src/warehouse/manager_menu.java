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
public class manager_menu extends JFrame{

    private final int HEIGHT = 500;
    private final int WIDTH = 600;
    private JLabel managernamelabel;
    private JButton addproduct, addmanufacture, addclient, allproduct, allmanufacture, allclient, viewtransaction, placeorder, makepayment;
    private JButton switchuser, switchuser1, logout;

    private JPanel [] panel;
    private JPanel mainpanel, toppanel, buttonpanel, middlepanel;

    public manager_menu()
    {

        setTitle("Manager menu");
        setSize(WIDTH, HEIGHT);
        buildPanel();
        add(mainpanel);
        setVisible(true);

    }

    public void buildPanel()
    {
        managernamelabel = new JLabel("Manager Name");
        addproduct = new JButton("Add Product");
        addmanufacture = new JButton("Add Manufacture");
        addclient = new JButton("Add Client");
        allproduct = new JButton("List All Products");
        allmanufacture = new JButton("List All Manufactures");
        allclient = new JButton("List All Clients");
        viewtransaction = new JButton("View Transactions");
        placeorder = new JButton("Place Order");
        makepayment = new JButton("Make Payment");
        switchuser = new JButton("Switch to Clerk");
        switchuser1 = new JButton("Switch to Client");
        logout = new JButton("Logout");

        logout.addActionListener(new logoutListener());
        switchuser.addActionListener(new switchuserListener());
        switchuser1.addActionListener(new switchuser1Listener());
        allclient.addActionListener(new clientListener());
        allproduct.addActionListener(new productListener());
        allmanufacture.addActionListener(new manufactureListener());
        addproduct.addActionListener(new addproductListener());
        addclient.addActionListener(new addclientListener());
        addmanufacture.addActionListener(new addmanufacturetListener());
        //viewtransaction.addActionListener(new transactionListener());

        addproduct.setPreferredSize(new Dimension(150, 30));
        addmanufacture.setPreferredSize(new Dimension(150, 30));
        addclient.setPreferredSize(new Dimension(150, 30));
        allproduct.setPreferredSize(new Dimension(150, 30));
        allmanufacture.setPreferredSize(new Dimension(150, 30));
        allclient.setPreferredSize(new Dimension(150, 30));
        viewtransaction.setPreferredSize(new Dimension(150, 30));
        placeorder.setPreferredSize(new Dimension(150, 30));
        makepayment.setPreferredSize(new Dimension(150, 30));
        switchuser.setPreferredSize(new Dimension(150, 30));
        logout.setPreferredSize(new Dimension(150, 30));
        switchuser1.setPreferredSize(new Dimension(150, 30));

        JPanel [] panel = new JPanel[13];
        
        for(int i = 0; i < 13; i++)
            panel[i] = new JPanel();

        panel[0].add(managernamelabel);
        panel[1].add(addproduct);
        panel[2].add(addmanufacture);
        panel[3].add(addclient);
        panel[4].add(allproduct);
        panel[5].add(allmanufacture);
        panel[6].add(allclient);
        panel[7].add(viewtransaction);
        panel[8].add(placeorder);
        panel[9].add(makepayment);
        panel[10].add(switchuser);
        panel[11].add(logout);
        panel[12].add(switchuser1);

        toppanel = new JPanel();
        toppanel.add(managernamelabel);
        toppanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));
        middlepanel = new JPanel();
        
        for(int i = 1; i < 10; i++)
            middlepanel.add(panel[i]);
        middlepanel.setLayout(new GridLayout(3,3));

        buttonpanel = new JPanel();
        buttonpanel.add(switchuser);
        buttonpanel.add(switchuser1);
        buttonpanel.add(logout);
        buttonpanel.setLayout(new GridLayout(1,3));
        buttonpanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 50));

        mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(3,1,0, 50));
        mainpanel.add(toppanel);
        mainpanel.add(middlepanel);
        mainpanel.add(buttonpanel);

    }

        private class logoutListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
        setVisible(false);
        usertype_panel mypanel = new usertype_panel();

    }
    }

        private class switchuserListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
        setVisible(false);
        login_panel mypanel = new login_panel(5);
    }
    }

  private class switchuser1Listener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
        setVisible(false);
        login_panel mypanel = new login_panel(6);

    }
    }
  
  private class productListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
    {
        productlist mylist = new productlist(1);
        setVisible(false);
    }
    }

 private class manufactureListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
    {
        manufacturelist mylist = new manufacturelist(1);
        setVisible(false);
    }
    }

  private class clientListener implements ActionListener
   {
    public void actionPerformed(ActionEvent e)
    {
        clientlist mylist = new clientlist(1);
        setVisible(false);
    }
    }

  private class addproductListener implements ActionListener
  {
  public void actionPerformed(ActionEvent e)
      {
      setVisible(false);
      addproduct mymenu = new addproduct();
  }
  }

    private class addmanufacturetListener implements ActionListener
  {
  public void actionPerformed(ActionEvent e)
      {
      setVisible(false);
      addmanufacture mymenu = new addmanufacture();
  }

  }

      private class addclientListener implements ActionListener
  {
  public void actionPerformed(ActionEvent e)
      {
      setVisible(false);
      addclient mymenu = new addclient();

  }
  }

 private class transactionListener implements ActionListener
  {
  public void actionPerformed(ActionEvent e)
      {
      setVisible(false);
      transactions mymenu = new transactions();

  }
  }


        

}

