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
public class viewhistory extends JFrame{
    private final int HEIGHTS = 500;
    private final int WIDTHS = 900;
    private JTable table;
    private JButton done;
    private JPanel tablePanel, buttonPanel, mainPanel;
    String [] columnNames = {"Order ID", "Product Name", "Quantity", "Total Price"};
    Object [] [] data = {};


    public viewhistory(int k)
    {
        setTitle("Product List");
        setSize(WIDTHS, HEIGHTS);
        buildPanel(k);


        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);
        tablePanel.add(scrollpane);

        add(mainPanel);
        setVisible(true);

    }

    public void buildPanel(int m)
    {
        done = new JButton("Done");

        done.addActionListener(new doneListener(m));
        tablePanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();

        buttonPanel.add(done);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tablePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    private class doneListener implements ActionListener
    {
        int n;
        public doneListener(int x){n = x;}
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        if(n == 0){
        client_menu mymenu;
        mymenu= new client_menu(0);
        }
        else if(n == 1)
        {
        clerk_menu mymenu;
        mymenu = new clerk_menu(0);
        }
        
    }
    }

}
