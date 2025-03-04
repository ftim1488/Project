package Project;

import javax.swing.*;
import java.awt.event.*;
 
public class LoginForm extends JFrame implements ActionListener {

    private final String correctUsername = "admin";
    private final String correctPassword = "1234";

    JLabel labelUser = new JLabel("Username:");
    JLabel labelPass = new JLabel("Password:");

    JTextField textField1 = new JTextField();
    JPasswordField textField2 = new JPasswordField();

    JButton button1 = new JButton("Login");
    JButton button2 = new JButton("Cancel");


    LoginForm() {
        
        this.setTitle("Login to Health Insurance");
        this.setSize(400, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(labelUser);
        this.add(labelPass);
        this.add(textField1);
        this.add(textField2);
        this.add(button1);
        this.add(button2);

        labelUser.setBounds(50, 50, 150, 30);
        textField1.setBounds(150, 50, 150, 30);      
        labelPass.setBounds(50, 100, 150, 30);   
        textField2.setEchoChar('*');
        textField2.setBounds(150, 100, 150, 30);
        button1.setBounds(100, 150, 100, 30);
        button2.setBounds(210, 150, 100, 30);

        button1.addActionListener(this);
        button2.addActionListener(this);
        this.getRootPane().setDefaultButton(button1);
        
    this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = textField1.getText();
        String pass = new String(textField2.getPassword());

        if (e.getSource() == button2) {
            System.exit(0);
        } else if (user.equals(correctUsername) && pass.equals(correctPassword)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new InsuranceMain();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid choice, please try again.");
        }
    }
}

