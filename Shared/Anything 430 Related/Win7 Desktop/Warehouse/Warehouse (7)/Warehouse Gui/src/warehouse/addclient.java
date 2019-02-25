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
public class addclient extends JFrame{
    private final int HEIGHTS = 500;
    private final int WIDTHS = 750;
    private JLabel clientid, clientfname, clientlname, address, city, state, zipcode, phonenumber, email, remarks;
    private JTextField clientidfield, clientfnamefield, clientlnamefield, addressfield, cityfield, statefield, zipcodefield, phonenumberfield, emailfield;
    private JTextArea remarksarea;
    private JScrollPane pane1;
    private JButton savebutton, cancelbutton;
    private JPanel [] panel;
    private JPanel [] subpanel;
    private JPanel mainpanel;

        public addclient()
    {
            setTitle("Add Client");
            setSize(WIDTHS, HEIGHTS);
            buildPanel();
            add(mainpanel);
            setVisible(true);
    }

        public void buildPanel()
    {

        clientid = new JLabel("Client ID");
        clientfname = new JLabel("First Name");
        clientlname = new JLabel("Last Name");
        address = new JLabel("Address");
         city = new JLabel("City");
        state = new JLabel("State");
        zipcode = new JLabel("Zip Code");
        phonenumber = new JLabel("Phone Number");
        email = new JLabel("Email");
        remarks = new JLabel("Remarks");

        clientidfield = new JTextField(10);
        clientfnamefield = new JTextField(10);
        clientlnamefield = new JTextField(10);
        addressfield= new JTextField(30);
        cityfield = new JTextField(10);
        statefield = new JTextField(3);
        zipcodefield = new JTextField(6);
        phonenumberfield = new JTextField(10);
        emailfield = new JTextField(10);
        remarksarea = new JTextArea(20, 20);
        pane1 = new JScrollPane(remarksarea);

        savebutton = new JButton("Save");
        cancelbutton = new JButton("Cancel");
        cancelbutton.addActionListener(new cancelListener());
        JPanel [] panel = new JPanel[22];
        for(int i = 0; i < 22; i++)
            panel[i] = new JPanel();

        panel[0].add(clientid);
        panel[1].add(clientidfield);
        panel[2].add(clientfname);
        panel[3].add(clientfnamefield);
        panel[4].add(clientlname);
        panel[5].add(clientlnamefield);
        panel[6].add(address);
        panel[7].add(addressfield);
        panel[8].add(city);
        panel[9].add(cityfield);
        panel[10].add(state);
        panel[11].add(statefield);
        panel[12].add(zipcode);
        panel[13].add(zipcodefield);
        panel[14].add(phonenumber);
        panel[15].add(phonenumberfield);
        panel[16].add(email);
        panel[17].add(emailfield);
        panel[18].add(remarks);
        panel[19].add(remarksarea);
        panel[20].add(savebutton);
        panel[21].add(cancelbutton);


        for(int i = 0; i < 19; i = i+2)
            panel[i].setLayout(new FlowLayout(FlowLayout.RIGHT));

        for(int i = 1; i < 19; i = i + 2)
            panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel [] subpanel = new JPanel[7];
        for(int i = 0; i < 7; i++)
            subpanel[i] = new JPanel();

        subpanel[0].add(panel[0]);
        subpanel[0].add(panel[1]);
        subpanel[0].setLayout(new GridLayout(1,2));
        subpanel[1].add(panel[2]);
        subpanel[1].add(panel[3]);
        subpanel[1].add(panel[4]);
        subpanel[1].add(panel[5]);
        subpanel[1].setLayout(new GridLayout(1,4));

        subpanel[2].add(panel[6]);
        subpanel[2].add(panel[7]);
        subpanel[2].setLayout(new GridLayout(1,2));

        subpanel[3].add(panel[8]);
        subpanel[3].add(panel[9]);
        subpanel[3].add(panel[10]);
        subpanel[3].add(panel[11]);
        subpanel[3].add(panel[12]);
        subpanel[3].add(panel[13]);
        subpanel[3].setLayout(new GridLayout(1,6));

        subpanel[4].add(panel[14]);
        subpanel[4].add(panel[15]);
        subpanel[4].add(panel[16]);
        subpanel[4].add(panel[17]);
        subpanel[4].setLayout(new GridLayout(1,4));

        subpanel[5].add(panel[18]);
        subpanel[5].add(panel[19]);
        subpanel[5].setLayout(new GridLayout(1,2));

        subpanel[6].add(panel[20]);
        subpanel[6].add(panel[21]);
        subpanel[6].setLayout(new GridLayout(1,2));
        subpanel[6].setLayout(new FlowLayout(FlowLayout.RIGHT));

        mainpanel = new JPanel();

        for(int i = 0; i < 7; i++)
            mainpanel.add(subpanel[i]);

        for(int i = 0; i < 5; i++)
          subpanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));


        mainpanel.setLayout(new GridLayout(8,1));


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
