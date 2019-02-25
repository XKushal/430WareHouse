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
public class addproduct extends JFrame{
    
    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    private JLabel productype, productid, productname, manufactureid, manufacturename, quantity, price, specification, remarks;
    private JTextField productnamefield, productidfield, manufactureidfield, manufacturenamefield, quantityfield, pricefield, specificationfield, remarksfield;
    private JButton cancelbutton, savebutton;
    private JPanel [] panel;
    private JPanel [] subpanel;
    private JComboBox productcombo;
    private String [] products = {"Television", "Computer", "Laptop"};
    private JPanel mainpanel;
    private JTextArea specificationarea, remarksarea;

    public addproduct()
    {
        setTitle("Add Product");
        setSize(WIDTH, HEIGHT);
        buildPanel();
        add(mainpanel);
        setVisible(true);
    }

    public void buildPanel()
    {
    productype = new JLabel("Product Type");
    productid = new JLabel("Product ID");
    productname = new JLabel("Product Name");
    manufactureid = new JLabel("Manufacture ID");
    manufacturename = new JLabel("Manufacture Name");
    quantity = new JLabel("Quantity");
    price = new JLabel("Price");
    specification = new JLabel("Specification");
    remarks = new JLabel("Remarks");

    productnamefield = new JTextField(10);
    productidfield = new JTextField(10);
    manufactureidfield = new JTextField(10);
    manufacturenamefield = new JTextField(10);
    manufactureidfield = new JTextField(10);
    quantityfield = new JTextField(10);
    pricefield = new JTextField(10);
    productcombo = new JComboBox(products);

    specificationarea = new JTextArea(5, 20);
    remarksarea = new JTextArea(5, 20);
    JScrollPane scrollpane1 = new JScrollPane(remarksarea);
   
    cancelbutton = new JButton("Cancel");
    savebutton = new JButton("Save");

    cancelbutton.addActionListener(new cancelListener());

    JPanel [] panel = new JPanel[20];
    for(int i = 0; i < 20; i ++)
        panel[i] = new JPanel();

    panel[0].add(productype);
    panel[1].add(productcombo);
    panel[2].add(productid);
    panel[3].add(productidfield);
    panel[4].add(productname);
    panel[5].add(productnamefield);
    panel[6].add(manufactureid);
    panel[7].add(manufactureidfield);
    panel[8].add(manufacturename);
    panel[9].add(manufacturenamefield);
    panel[10].add(quantity);
    panel[11].add(quantityfield);
    panel[12].add(price);
    panel[13].add(pricefield);
    panel[14].add(remarks);
    panel[15].add(scrollpane1);
    panel[16].add(cancelbutton);
    panel[17].add(savebutton);

   JPanel [] subpanel = new JPanel[10];
   for(int i = 0; i < 10; i++)
       subpanel[i] = new JPanel();

   subpanel[0].add(panel[0]);
   subpanel[0].add(panel[1]);
   subpanel[0].setLayout(new GridLayout(1,2));
   panel[0].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[1].setLayout(new FlowLayout(FlowLayout.LEFT));

   subpanel[1].add(panel[2]);
   subpanel[1].add(panel[3]);
   subpanel[1].add(panel[4]);
   subpanel[1].add(panel[5]);
   subpanel[1].setLayout(new GridLayout(1,4));
   panel[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[3].setLayout(new FlowLayout(FlowLayout.LEFT));
   panel[4].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[5].setLayout(new FlowLayout(FlowLayout.LEFT));

   subpanel[2].add(panel[6]);
   subpanel[2].add(panel[7]);
   subpanel[2].add(panel[8]);
   subpanel[2].add(panel[9]);
   subpanel[2].setLayout(new GridLayout(1,4));
   panel[6].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[7].setLayout(new FlowLayout(FlowLayout.LEFT));
   panel[8].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[9].setLayout(new FlowLayout(FlowLayout.LEFT));

   subpanel[3].add(panel[10]);
   subpanel[3].add(panel[11]);
   subpanel[3].add(panel[12]);
   subpanel[3].add(panel[13]);
   subpanel[3].setLayout(new GridLayout(1,4));
   panel[10].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[11].setLayout(new FlowLayout(FlowLayout.LEFT));
   panel[12].setLayout(new FlowLayout(FlowLayout.RIGHT));
   panel[13].setLayout(new FlowLayout(FlowLayout.LEFT));

   subpanel[4].add(panel[14]);
   subpanel[4].add(panel[15]);
   subpanel[4].setLayout(new GridLayout(1,2));

   subpanel[5].add(panel[16]);
   subpanel[5].add(panel[17]);
   subpanel[5].setLayout(new GridLayout(1,2));
   subpanel[5].setLayout(new FlowLayout(FlowLayout.CENTER));

   for(int i = 0; i < 5; i++)
       subpanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
   
   mainpanel = new JPanel();
   
   for(int i = 0; i < 6; i++)
       mainpanel.add(subpanel[i]);

   mainpanel.setLayout(new GridLayout(6,1));

    }

        private class cancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            manager_menu mymenu = new manager_menu();
        }
    }

}


