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
    JButton button5 = new JButton("Use Coverage");

    private static ArrayList<Beneficiary> allBeneficiaries = new ArrayList<>(); 
    InsuranceMain() {
        this.setTitle("Health Insurance");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        labelTitle.setFont(font);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);

        this.add(labelTitle, BorderLayout.NORTH);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        this.add(panel, BorderLayout.CENTER);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);

        this.setVisible(true);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            this.setVisible(false);
            new coverageMain(this);
        } else if (e.getSource() == button2) {
            String userIdToRemove = JOptionPane.showInputDialog(this, "Enter user ID to remove:");
            boolean found = false;
            for (Beneficiary b : allBeneficiaries) {
                if (b.getUserId().equals(userIdToRemove)) { 
                    allBeneficiaries.remove(b);
                    found = true;
                    break;
                } 
            } 
            if (found) {
                JOptionPane.showMessageDialog(this, "Beneficiary with ID: " + userIdToRemove + " has been removed.");
            } else {
                JOptionPane.showMessageDialog(this, "Beneficiary with ID: " + userIdToRemove + " not found.");
            }  
        } else if (e.getSource() == button3) {
            if (allBeneficiaries.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The beneficiary list is empty.");
            } else {
                StringBuilder beneficiaryList = new StringBuilder("Insurance Plan:\n");
                for (Beneficiary b : allBeneficiaries) {
                    beneficiaryList.append(b.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(this, beneficiaryList.toString());
            }
        } else if (e.getSource() == button4) {
            System.exit(0);
        } else if (e.getSource() == button5) {
            this.setVisible(false);
            new Transaction(this);  
        }
    }

    public static void addBeneficiary(Beneficiary beneficiary) {
        allBeneficiaries.add(beneficiary); 
    }

    public static ArrayList<Beneficiary> getAllBeneficiaries() {
        return allBeneficiaries;
    }
}
