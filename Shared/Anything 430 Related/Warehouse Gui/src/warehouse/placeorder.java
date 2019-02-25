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
public class placeorder extends JFrame{
    private final int HEIGHT = 300;
    private final int WIDTH = 300;
    private JLabel clientid, orderid, productid, manufactureid, quantity, clientid2, orderid2;
    private JTextField productidfield, manufactureidfield, quantityfield;
    private JButton placeorder, cancel;
    private JPanel [] panel;
    private JPanel mainpanel, middlepanel, buttonpanel, toppanel;

    public placeorder()
    {
        setTitle("Place Order");
        setSize(WIDTH, HEIGHT);
        buildPanel();
        add(mainpanel);
        setVisible(true);
    }

    public void buildPanel()
    {
    clientid = new JLabel("Client ID");
    orderid = new JLabel("Order ID");

    clientid2 = new JLabel("649256");
    orderid2 = new JLabel("abc123");
    
    productid = new JLabel("Product ID");
    manufactureid = new JLabel("Manufacture ID");
    quantity = new JLabel("Quantity");

    productidfield = new JTextField(5);
    manufactureidfield = new JTextField(5);
    quantityfield = new JTextField(5);

    placeorder = new JButton("Place Order");
    cancel = new JButton("Cancel");

    JPanel [] panel = new JPanel[12];
    for(int i = 0; i < 12; i++)
        panel[i] = new JPanel();

    panel[0].add(clientid);
    panel[1].add(clientid2);
    panel[2].add(orderid);
    panel[3].add(orderid2);
    panel[4].add(productid);
    panel[5].add(productidfield);
    panel[6].add(manufactureid);
    panel[7].add(manufactureidfield);
    panel[8].add(quantity);
    panel[9].add(quantityfield);
    panel[10].add(placeorder);
    panel[11].add(cancel);

    cancel.addActionListener(new cancelListener());
    
    mainpanel = new JPanel();

    toppanel = new JPanel();
    middlepanel = new JPanel();
    buttonpanel = new JPanel();

    for(int i = 0; i < 4; i++)
        toppanel.add(panel[i]);
    
    toppanel.setLayout(new GridLayout(2,2));
    panel[0].setLayout(new FlowLayout(FlowLayout.RIGHT));
    panel[1].setLayout(new FlowLayout(FlowLayout.LEFT));
    panel[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
    panel[3].setLayout(new FlowLayout(FlowLayout.LEFT));
 
    for(int i = 4; i < 10; i++)
        middlepanel.add(panel[i]);
    
    for(int i = 4; i < 10; i = i+2)
    {
        panel[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    for(int i = 5; i < 11; i = i+2)
    {
        panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
    }

   buttonpanel.add(panel[10]);
   buttonpanel.add(panel[11]);
   panel[10].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[11].setLayout(new FlowLayout(FlowLayout.LEFT));
   buttonpanel.setLayout(new GridLayout(1,2));
   
   middlepanel.setLayout(new GridLayout(3,2));

   mainpanel.add(toppanel);
   mainpanel.add(middlepanel);
   mainpanel.add(buttonpanel);

   mainpanel.setLayout(new BorderLayout());
   mainpanel.add(toppanel, BorderLayout.NORTH);
   mainpanel.add(middlepanel, BorderLayout.CENTER);
   mainpanel.add(buttonpanel, BorderLayout.SOUTH);
   
    }

    private class cancelListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
       setVisible(false);
    }
    }
}