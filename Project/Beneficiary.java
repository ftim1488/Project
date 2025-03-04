package Project;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Beneficiary extends JFrame implements ActionListener {

    public static ArrayList<Beneficiary> beneficiaries = new ArrayList<>(); 

    private String name;
    private int age;
    private String userId;
    private String address;

    JLabel label1 = new JLabel("Enter name:");
    JLabel label2 = new JLabel("Enter age:");
    JLabel label3 = new JLabel("User ID (auto-generated):");
    JLabel label4 = new JLabel("Enter address:");

    JTextField nameField = new JTextField();
    JTextField ageField = new JTextField();
    JTextField userIdField = new JTextField();
    JTextField addressField = new JTextField();

    JButton submitButton = new JButton("Submit");

    Beneficiary() {

        this.setTitle("Add Beneficiary");
        this.setSize(400, 300); 
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        

        this.add(label1);
        this.add(nameField);
        this.add(label2);
        this.add(ageField);
        this.add(label3);
        this.add(userIdField);
        this.add(label4);
        this.add(addressField);
        this.add(submitButton);

        label1.setBounds(20, 40, 100, 30);
        label2.setBounds(20, 80, 100, 30);
        label3.setBounds(20, 120, 150, 30);
        label4.setBounds(20, 160, 100, 30);

        nameField.setBounds(150, 40, 200, 30);
        ageField.setBounds(150, 80, 200, 30);               
        userIdField.setBounds(170, 120, 180, 30);         
        addressField.setBounds(150, 160, 200, 30); 
        submitButton.setBounds(150, 210, 100, 30);
               
        submitButton.addActionListener(this); 
        this.getRootPane().setDefaultButton(submitButton);

        String generatedUserId = generateRandomUserId();
        userIdField.setText(generatedUserId);
        userIdField.setEditable(false);
        
    this.setVisible(true);
    }

    private String generateRandomUserId() {
        Random random = new Random();
        int number = random.nextInt(1000); 
        return ("09") + String.format("%03d", number);
    }


    public boolean setBeneficiaryDetails() {
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
            if ((age < 0) || (age >120)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid age.");
            return false; 
        }

        this.userId = userIdField.getText();

        this.address = addressField.getText();
        if (this.address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid address.");
            return false;
        }
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if (setBeneficiaryDetails()) {
            addBeneficiary(Beneficiary.this);
            JOptionPane.showMessageDialog(Beneficiary.this, "Beneficiary added successfully.");
            dispose();
            
        }
    }


    public void addBeneficiary(Beneficiary beneficiary) {
        beneficiaries.add(beneficiary);
    }

  
    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
    return "Name: " + name + ", Age: " + age + ", User ID: " + userId + ", Address: " + address;
    }
}