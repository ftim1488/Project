package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InsuranceMain extends JFrame implements ActionListener {

    JLabel labelTitle = new JLabel("Choose an option:");
    Font font = new Font("Courier", Font.BOLD, 16);

    JButton button1 = new JButton("1. Add beneficiary");
    JButton button2 = new JButton("2. Remove beneficiary");
    JButton button3 = new JButton("3. List");
    JButton button4 = new JButton("4. Exit");

    InsuranceMain() {
        this.setTitle("Health Insurance");
        this.setSize(400, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        labelTitle.setFont(font);
        labelTitle.setBounds(85, 17, 200, 30);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);

        this.add(labelTitle);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);

        button1.setBounds(108, 65, 160, 30);
        button2.setBounds(106, 115, 165, 30);
        button3.setBounds(108, 165, 160, 30);
        button4.setBounds(108, 215, 160, 30);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        
        this.setVisible(true);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            new Beneficiary(); 
            
        } else if (e.getSource() == button2) {
            String userIdToRemove = JOptionPane.showInputDialog(this, "Enter user ID to remove:");
            boolean found = false;
            for (Beneficiary b : Beneficiary.beneficiaries) {
                if (b.getUserId().equals(userIdToRemove)) { 
                    Beneficiary.beneficiaries.remove(b);
                    found = true;
                    break;
            }
                } 
                if (found) {
                    JOptionPane.showMessageDialog(this, "Beneficiary with ID: " +  userIdToRemove + " has been removed.");
                } else {
                    JOptionPane.showMessageDialog(this, "Beneficiary with ID: " + userIdToRemove + " not found.");
                }        
            
        } else if (e.getSource() == button3) {
            ArrayList<Beneficiary> allBeneficiaries = Beneficiary.beneficiaries;
            if (allBeneficiaries.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The list is empty.");
            } else {
                StringBuilder beneficiaryList = new StringBuilder("Beneficiaries:\n");
                for (Beneficiary b : allBeneficiaries) {
                    beneficiaryList.append(b.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(this, beneficiaryList.toString());
            }

        } else if (e.getSource() == button4) {
            new LoginForm();
        }
    }
}