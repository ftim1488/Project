package Project;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class coverageMain extends JFrame implements ActionListener {

    private String name;
    private int age;
    private String userId;
    private int amount;
    private int deductible;
    private String copay;
    private int Cov_Per;
    private int User_Per;
    private String plan;

    JLabel label1 = new JLabel("Enter Name:");
    JLabel label2 = new JLabel("Enter Age:");
    JLabel label3 = new JLabel("User ID:");
    JLabel label4 = new JLabel("Select anual Plan:");
    JLabel label5 = new JLabel("Enter Deductible Payment:");
    JLabel coverageDetailsLabel = new JLabel(""); 

    JRadioButton radioButton1 = new JRadioButton("₱100,000 Php. Basic Coverage Plan");
    JRadioButton radioButton2 = new JRadioButton("₱500,000 Php. Standard Coverage Plan");
    JRadioButton radioButton3 = new JRadioButton("₱1,250,000 Php. Intermediate Coverage Plan");
    JRadioButton radioButton4 = new JRadioButton("₱3,000,000 Php. Premium Coverage Plan");

    ButtonGroup radioGroup = new ButtonGroup(); 

    JTextField nameField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField userIdField = new JTextField();
    JTextField deductibleField = new JTextField(); 
    
    JButton submitButton = new JButton("Submit");
    JButton backButton = new JButton("Back to Main Menu");

    private InsuranceMain insuranceMainFrame;

    public coverageMain(InsuranceMain insuranceMainFrame) {
        this.insuranceMainFrame = insuranceMainFrame;
        this.insuranceMainFrame.setVisible(false);

        this.setTitle("Subsribe a plan");
        this.setSize(500, 450); 
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(label1);
        this.add(nameField);
        this.add(label2);
        this.add(ageField);
        this.add(label3);
        this.add(userIdField);
        this.add(label4);
        this.add(submitButton);
        this.add(backButton);
        this.add(label5); 
        this.add(deductibleField); 

        this.add(radioButton1);
        this.add(radioButton2);
        this.add(radioButton3);
        this.add(radioButton4);

        this.add(coverageDetailsLabel);

        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);
        radioGroup.add(radioButton4);

        label1.setBounds(20, 40, 120, 30);
        nameField.setBounds(150, 40, 200, 30);
        
        label2.setBounds(20, 80, 120, 30);
        ageField.setBounds(150, 80, 200, 30);
        
        label3.setBounds(20, 120, 120, 30);
        userIdField.setBounds(150, 120, 200, 30);
        userIdField.setEditable(false);

        label4.setBounds(20, 160, 150, 30);
        radioButton1.setBounds(150, 160, 400, 30);
        radioButton2.setBounds(150, 190, 400, 30);
        radioButton3.setBounds(150, 220, 400, 30);
        radioButton4.setBounds(150, 250, 400, 30);

        label5.setBounds(20, 280, 180, 30); 
        deductibleField.setBounds(200, 280, 200, 30);  
        
        submitButton.setBounds(150, 320, 100, 30);
        backButton.setBounds(250, 320, 200, 30);  

        coverageDetailsLabel.setBounds(20, 350, 600, 50); 

        String generatedUserId = generateRandomUserId();
        userIdField.setText(generatedUserId);

        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);
        radioButton3.addActionListener(this);
        radioButton4.addActionListener(this);

        label5.setVisible(false);
        deductibleField.setVisible(false);

        backButton.addActionListener(this);
        submitButton.addActionListener(this);

        this.setVisible(true);
    }

    private String generateRandomUserId() {
        Random random = new Random();
        int number = random.nextInt(100);
        return "09" + String.format("%02d", number);
    }


    public boolean setcoverageDetails() {
        this.name = nameField.getText();
        if (this.name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid name.");
            return false;
        }
        for (char c: name.toCharArray()) {
            if (Character.isDigit(c)){
                JOptionPane.showMessageDialog(this, "Please enter a valid name.");
                    return false;
            }
        }

        try {
            this.age = Integer.parseInt(ageField.getText());
            if (age < 18 || age > 120) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.");
            return false;
        }

        this.userId = userIdField.getText();


        if (radioButton1.isSelected()) {
            this.plan = "Basic Coverage";
            amount = 100000; Cov_Per = 0;
            this.deductible = 10000;
            this.copay = "No co-payment";
            coverageDetailsLabel.setText("Plan: ₱100,000 - Basic Coverage (No co-payment, ₱10,000 deductible)");
        } else if (radioButton2.isSelected()) {
            this.plan = "Standard Coverage";
            amount = 500000; Cov_Per = 70; User_Per = 30;
            this.deductible = 20000;
            this.copay = "70/30";
            coverageDetailsLabel.setText("Plan: ₱500,000 - Standard Coverage (70/30 co-payment, ₱20,000 deductible)");
        } else if (radioButton3.isSelected()) {
            this.plan = "Intermediate Coverage";
            amount = 1250000; Cov_Per = 60; User_Per = 40; 
            this.deductible = 40000;
            this.copay = "60/40";
            coverageDetailsLabel.setText("Plan: ₱1,250,000 - Intermediate Coverage (60/40 co-payment, ₱40,000 deductible)");
        } else if (radioButton4.isSelected()) {
            this.plan = "Premium Coverage";
            amount = 3000000; Cov_Per = 50; User_Per = 50;
            this.amount = 3000000;
            this.deductible = 80000;
            this.copay = "50/50";
            coverageDetailsLabel.setText("Plan: ₱3,000,000 - Premium Coverage (50/50 co-payment, ₱80,000 deductible)");
        }

        label5.setVisible(true);
        deductibleField.setVisible(true);

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            insuranceMainFrame.setVisible(true);
                dispose();
        
        } else if (e.getSource() == submitButton) {
            if (setcoverageDetails()) {   
                String deductibleInput = deductibleField.getText().replace(",", "");                

                try {
                    int userDeductible = Integer.parseInt(deductibleInput);
                    
                    if (userDeductible != deductible) {
                        JOptionPane.showMessageDialog(this, "Deductible payment is less or more than the required deductible.");
                        return;
                    } 
                        
                    Beneficiary beneficiary = new Beneficiary();
                    beneficiary.setName(this.name);
                    beneficiary.setAge(this.age);
                    beneficiary.setUserId(this.userId);
                    beneficiary.setAmount(this.amount);
                    beneficiary.setDeductible(this.deductible);
                    beneficiary.setCopay(this.copay);
                    beneficiary.setCoveragePercent(this.Cov_Per);
                    beneficiary.setUserPercent(this.User_Per);
                    beneficiary.setPlan(this.plan);

                    InsuranceMain.addBeneficiary(beneficiary);

                    String msg = "Coverage beneficiary added successfully.";
                    JOptionPane.showMessageDialog(this, msg);
                    
                    insuranceMainFrame.setVisible(true); 
                    dispose();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid deductible amount");
                }
            }
        } else {
            setcoverageDetails();
        } 
    }
}
