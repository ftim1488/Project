package Project;

import javax.swing.*;
import java.awt.event.*;

public class Transaction extends JFrame implements ActionListener {
    JLabel label1 = new JLabel("User Id");
    JLabel label2 = new JLabel("Enter Full expense");

    JTextField IdField = new JTextField();
    JTextField expenseField = new JTextField();
    
    JButton submitButton = new JButton("Submit");
    JButton cancelButton = new JButton("Cancel");
    
    Beneficiary newBeneficiary = new Beneficiary();

    private InsuranceMain insuranceMainFrame;

    public Transaction(InsuranceMain insuranceMainFrame) {
        this.insuranceMainFrame = insuranceMainFrame;

        this.setTitle("Transaction");
        this.setSize(400, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(label1);
        this.add(IdField);
        this.add(label2);
        this.add(expenseField);
        this.add(submitButton);
        this.add(cancelButton);

        label1.setBounds(20, 40, 120, 30);
        IdField.setBounds(150, 40, 200, 30);
        label2.setBounds(20, 80, 120, 30);
        expenseField.setBounds(150, 80, 200, 30);
        submitButton.setBounds(100, 120, 100, 30);
        cancelButton.setBounds(210, 120, 100, 30);

        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);
            

    this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            insuranceMainFrame.setVisible(true); 
                dispose();
        } else if (e.getSource() == submitButton) {
            String userId = IdField.getText(); 
            String expenseStr = expenseField.getText().replace(",", "");
            double expense = 0;
            try {
                expense = Double.parseDouble(expenseStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for expense.");
                return;
            }
            boolean found = false;
            for (Beneficiary b : InsuranceMain.getAllBeneficiaries()) {
                if (b.getAmount() == 0) {
                    JOptionPane.showMessageDialog(this, "You have reached the maximum amount. Please subscribe again.");
                }else if (b.getUserId().equals(userId)) {
                        String name = b.getName();
                        int amount = b.getAmount();
                        String plan = b.getPlan();
                        double userAmount;
                        double coveredAmount;
                        double total;

                        if (b.getCoveragePercent() != 0) {
                            coveredAmount = (expense * b.getCoveragePercent()) / 100;
                            userAmount = (expense * b.getUserPercent()) / 100;
                        } else {
                            userAmount = 0;
                            coveredAmount = expense;
                        }
                
                        total = amount - coveredAmount;
                        b.setAmount((int) total); 

                        JOptionPane.showMessageDialog(this, "Transaction Successful\n" +
                            "Name: " + name + "\n" + 
                            "Plan: " + plan + "\n" +
                            "User ID: " + userId + "\n" +
                            "Full Expense: ₱" + expense + "\n" +
                            "Coverage: ₱" + coveredAmount + "\n" +
                            "Remaining Amount to Pay: ₱" + userAmount + "\n" +
                            "Remaining Balance: ₱" + total);
                                            
                        this.setVisible(false);
                        insuranceMainFrame.setVisible(true);
                        found = true;
                        break;
                    }
            
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "User ID not found.");
            }               
        }    
    }
}
