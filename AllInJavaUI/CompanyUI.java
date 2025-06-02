import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompanyUI {
    public static void main(String[] args) {
        // Create the main frame (window)
        JFrame frame = new JFrame("Employee Entry Form");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 2, 10, 10));

        // Input fields
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        String[] types = {"Full-Time", "Part-Time"};
        JComboBox<String> typeBox = new JComboBox<>(types);
        JTextField salaryField = new JTextField();
        JTextField hoursField = new JTextField();

        // Button
        JButton saveButton = new JButton("Save Employee");

        // Output area
        JTextArea output = new JTextArea(5, 20);
        output.setEditable(false);

        // Add components to frame
        frame.add(new JLabel("Employee ID:"));
        frame.add(idField);
        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Type:"));
        frame.add(typeBox);
        frame.add(new JLabel("Salary / Hourly Rate:"));
        frame.add(salaryField);
        frame.add(new JLabel("Hours Worked (Part-Time only):"));
        frame.add(hoursField);
        frame.add(saveButton);
        frame.add(new JScrollPane(output));

        // Button logic
        saveButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = (String) typeBox.getSelectedItem();
                double salary = Double.parseDouble(salaryField.getText());
                int hours = hoursField.getText().isEmpty() ? 0 : Integer.parseInt(hoursField.getText());

                Employee emp;
                EmployeeDAO dao = new EmployeeDAO();

                if (type.equals("Full-Time")) {
                    emp = new FullTimeEmployee(name, id, salary);
                    dao.saveEmployee(emp, "full", salary, 0);
                } else {
                    emp = new PartTimeEmployee(name, id, salary, hours);
                    dao.saveEmployee(emp, "part", salary, hours);
                }

                // Show output
                output.setText("Saved Employee:\n");
                output.append("Name: " + emp.getName() + "\n");
                output.append("ID: " + emp.getId() + "\n");
                output.append("Salary: $" + emp.calculateSalary());

            } catch (Exception ex) {
                output.setText("Error: " + ex.getMessage());
            }
        });

        // Show the window
        frame.setVisible(true);
    }
}
