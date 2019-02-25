/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package warehouse;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable.*;
import java.util.*;

/**
 *
 * @author kunga
 */
public class productlist extends JFrame{
    private final int HEIGHTS = 650;
    private final int WIDTHS = 1000;
    private JTable table;
    private JButton viewdetail, edit, done;
    private JPanel tablePanel, buttonPanel, mainPanel;
    String [] columnNames = {"Product Type", "Product ID", "Product Name", "Manufacture Name", "Quantity", "Price"};
    Object [] [] data = {{"television", "1234","sony 32", "sony", new Integer(5), new Integer(5)}};
    

    public productlist(int a)
    {
        setTitle("Product List");
        setSize(WIDTHS, HEIGHTS);
        buildPanel(a);
        

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(900, 500));
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);
        tablePanel.add(scrollpane);

        add(mainPanel);
        setVisible(true);
    
    }
    
    public void buildPanel(int b)
    {
        viewdetail = new JButton("View Detail");
        edit = new JButton("Edit");
        done = new JButton("Done");

        done.addActionListener(new doneListener(b));

        tablePanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();

        buttonPanel.add(viewdetail);
        buttonPanel.add(edit);
        buttonPanel.add(done);
        buttonPanel.setLayout(new GridLayout(1,3));


        mainPanel.add(tablePanel);
        mainPanel.add(buttonPanel);
      
    }

   private class doneListener implements ActionListener
    {
        int c;
        public doneListener(int d){c = d;}
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            if(c == 0){
            clerk_menu mymenu;
            mymenu= new clerk_menu(0);}
            else if(c == 1)
            {
            manager_menu mymenu;
            mymenu = new manager_menu();}
            }

        }
    }


