 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package warehouse;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTable.*;

/**
 *
 * @author kunga
 */
public class transactions extends JFrame{
    private final int HEIGHTS = 650;
    private final int WIDTHS = 1000;
    private JTable table;
    private JButton done;
    private JPanel tablePanel, buttonPanel, mainPanel;
    String [] columnNames = {"Date", "Payment ID", "Paid to/from", "Amount"};
    Object [] [] data = {{}};


    public transactions()
    {
        setTitle("Transactions");
        setSize(WIDTHS, HEIGHTS);
        buildPanel();

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(900, 500));
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);
        tablePanel.add(scrollpane);

        add(mainPanel);
        setVisible(true);

    }

    public void buildPanel()
    {
        done = new JButton("Done");

        done.addActionListener(new doneListener());

        tablePanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();

        buttonPanel.add(done);
  
        mainPanel.add(tablePanel);
        mainPanel.add(buttonPanel);

    }

   private class doneListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            manager_menu mymenu = new manager_menu();}


        }
    }


