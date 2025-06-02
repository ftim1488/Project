import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;


public class TableSelectionPanel extends JPanel {
    private MainFrame frame;
    private JPanel tablePanel;


    public TableSelectionPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Select a Table", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);


        tablePanel = new JPanel(new GridLayout(2, 3, 10, 10));
        loadTables();
        add(tablePanel, BorderLayout.CENTER);


        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> frame.returnToMain());
        add(backBtn, BorderLayout.SOUTH);
    }


    private void loadTables() {
        tablePanel.removeAll();


        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT table_id, table_name, is_occupied FROM restaurant_table")) {


            while (rs.next()) {
                int tableId = rs.getInt("table_id");
                String tableName = rs.getString("table_name");
                boolean isOccupied = rs.getBoolean("is_occupied");


                JButton tableButton = new JButton(tableName);
                tableButton.setBackground(isOccupied ? Color.RED : Color.GREEN);
                tableButton.setEnabled(true);


                tableButton.addActionListener(e -> {
                    if (isOccupied) {
                        handleOccupiedTable(tableId, tableName);
                    } else {
                        frame.setContentPane(new OrderPanel(frame, tableId));
                        frame.revalidate();
                        frame.repaint();


                    }
                });


                tablePanel.add(tableButton);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading tables: " + ex.getMessage());
        }


        tablePanel.revalidate();
        tablePanel.repaint();
    }


    private void handleOccupiedTable(int tableId, String tableName) {
        String[] options = {"Add Order", "Pay & Close", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Table " + tableName + " is occupied. What would you like to do?",
                "Table Occupied",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );


        switch (choice) {
            case 0: // Add Order
                frame.setContentPane(new OrderPanel(frame, tableId));
                frame.revalidate();
                frame.repaint();
                break;
            case 1: // Pay & Close
                markTableAsFree(tableId);
                loadTables(); // Refresh
                break;
            default: // Cancel
                break;
        }
    }


    private void markTableAsFree(int tableId) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE restaurant_table SET is_occupied = false WHERE table_id = ?")) {
            ps.setInt(1, tableId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    



}



