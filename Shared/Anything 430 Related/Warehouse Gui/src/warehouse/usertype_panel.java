/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package warehouse;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Font;

/**
 *
 * @author kunga
 */
public class usertype_panel extends JFrame{
    private JButton client;
    private JButton clerk;
    private JButton manager;
    private JButton exit;

    private JPanel panel1;
    private JPanel panel2;
    private JPanel clientPanel;
    private JPanel clerkPanel;
    private JPanel managerPanel;
    private JPanel exitPanel;
    private JPanel welcomePanel;

    private JLabel welcome;


    private final int WIDTHS = 350;
    private final int HEIGHTS = 480;

    public usertype_panel()
    {
        setTitle("Warehouse System");
        setSize(WIDTHS, HEIGHTS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        add(panel2);
        setVisible(true);

    }

    public void buildPanel()
    {
      Font font = new Font("Serif", Font.BOLD, 50);

      client = new JButton("Client");
      clerk = new JButton("Clerk");
      manager = new JButton("Manager");
      exit = new JButton("Exit");
      welcome = new JLabel("Welcome");
      welcome.setFont(font);

      client.setPreferredSize(new Dimension(160, 50));
      clerk.setPreferredSize(new Dimension(160, 50));
      manager.setPreferredSize(new Dimension(160, 50));
      exit.setPreferredSize(new Dimension(160, 50));

      client.addActionListener(new clientListener());
      clerk.addActionListener(new clerkListener());
      manager.addActionListener(new managerListener());
      exit.addActionListener(new exitListener());
      
      welcomePanel = new JPanel();
      clientPanel = new JPanel();
      clerkPanel = new JPanel();
      managerPanel = new JPanel();
      exitPanel = new JPanel();

      panel1 = new JPanel();
      panel2 = new JPanel();

      welcomePanel.add(welcome);
      clientPanel.add(client);
      clerkPanel.add(clerk);
      managerPanel.add(manager);
      exitPanel.add(exit);

      panel1.setLayout(new GridLayout(5, 1));
      panel1.add(welcomePanel);
      panel1.add(clientPanel);
      panel1.add(clerkPanel);
      panel1.add(managerPanel);
      panel1.add(exitPanel);

      panel2.add(panel1);
    }

    private class clientListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
            
            login_panel myclass = new login_panel(0);
            setVisible(false);
        }
    }

       private class clerkListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
            login_panel myclass = new login_panel(1);
            setVisible(false);
            
        }
    }

          private class managerListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
            login_panel myclass = new login_panel(2);
            setVisible(false);
        }
    }
          
             private class exitListener implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}
