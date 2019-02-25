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
public class viewAccount extends JFrame{
    private final int HEIGHTS = 350;
    private final int WIDTHS = 400;

    private JLabel id, fname, lname, address, city, state, zipcode, phonenumber, email;
    private JButton editinfo, done;
    private JPanel [] panel;
    private JPanel mainpanel, toppanel, buttonpanel;
    
   
    public viewAccount()
    {
        setTitle("View Account");
        setSize(WIDTHS, HEIGHTS);
        buildPanel();
        add(mainpanel);
        setVisible(true);

    }

    public void buildPanel()
    {
        id = new JLabel("Client ID");
        fname = new JLabel("First Name");
        lname = new JLabel("Last Name");
        address = new JLabel("Address");
        city = new JLabel("City");
        state = new JLabel("State");
        zipcode = new JLabel("Zip Code");
        phonenumber = new JLabel("Phone Number");
        email = new JLabel("Email");
        editinfo = new JButton("Edit Info");
        done = new JButton("Done");

        editinfo.addActionListener(new editListener());
        done.addActionListener(new doneListener());

        JPanel [] panel = new JPanel[11];
        for(int i = 0; i < 11; i++)
            panel[i] = new JPanel();

       panel[0].add(id);
       panel[1].add(fname);
       panel[2].add(lname);
       panel[3].add(address);
       panel[4].add(city);
       panel[5].add(state);
       panel[6].add(zipcode);
       panel[7].add(phonenumber);
       panel[8].add(email);
       panel[9].add(editinfo);
       panel[10].add(done);

       mainpanel = new JPanel();
       toppanel = new JPanel();
       buttonpanel = new JPanel();

       for(int i = 0; i < 9; i++)
       {
           toppanel.add(panel[i]);
           panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
       }

       
       toppanel.setLayout(new GridLayout(9, 1));
       
       buttonpanel.add(panel[9]);
       buttonpanel.add(panel[10]);
       panel[9].setLayout(new FlowLayout(FlowLayout.RIGHT));
       panel[10].setLayout(new FlowLayout(FlowLayout.LEFT));
       buttonpanel.setLayout(new GridLayout(1,2));

       mainpanel.setLayout(new BorderLayout());
       mainpanel.add(toppanel, BorderLayout.NORTH);
       mainpanel.add(buttonpanel, BorderLayout.SOUTH);        
    }

    private class editListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {

    }
    }

    private class doneListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
        client_menu mymenu = new client_menu(0);
        setVisible(false);
    }
    }

}
