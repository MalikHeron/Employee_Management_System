import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

    JPanel panel = new JPanel();
    //ImageIcon Icon = new ImageIcon("images/topup_icon.png");

    //Default Constructor
    public MainScreen() {
        //Properties for JFrame
        setPreferredSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(600, 600));
        setResizable(false);// Not resizeable
        setLayout(null);// no layout style
        setLocationRelativeTo(null);// Center window on screen
        //setIconImage(Icon.getImage());// Set icon for window
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Properties for Panel
        panel.setLayout(null);
        panel.setSize(600, 600);
        panel.setBackground(Color.WHITE);
        panel.setVisible(true);

        //Properties for login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(190, 150, 250, 80);
        loginButton.setFocusPainted(false);
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Properties for Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(190, 250, 250, 80);
        exitButton.setFocusPainted(false);
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("times new roman", Font.PLAIN, 24));

        //Add components to Panel
        panel.add(loginButton);
        panel.add(exitButton);

        pack();
        add(panel);//Add Panel to JFrame

        //Remove current contents and replace with new menu
        panel.removeAll();
        panel.add(new Department().getComponent());
        panel.validate();
        panel.repaint();
    }
}
