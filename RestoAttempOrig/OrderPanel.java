///Di pni final..may butang pni n changes and kulang p ang total price
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;


public class OrderPanel extends JPanel {
    private MainFrame frame;
    private int tableId;
    private JPanel cartPanel;
    private JTextArea cartText;
    private List<String> cart = new ArrayList<>();


    public OrderPanel(MainFrame frame, int tableId) {
        this.frame = frame;
        this.tableId = tableId;
        setLayout(new BorderLayout());


        JLabel label = new JLabel("Order for Table " + tableId, SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);


        JPanel menuPanel = new JPanel(new GridLayout(1, 2));


        // Left: Menu Buttons
        JPanel categoryPanel = new JPanel(new GridLayout(1, 3));
        Map<String, List<MenuItem>> categorizedItems = loadMenuItems();


        JPanel innerCol1 = createCategoryPanel("Main", categorizedItems.getOrDefault("Main", new ArrayList<>()));
        JPanel innerCol2 = createCategoryPanel("Sandwich", categorizedItems.getOrDefault("Sandwich", new ArrayList<>()));
        JPanel innerCol3 = createCategoryPanel("Drinks", categorizedItems.getOrDefault("Drinks", new ArrayList<>())) ;


        categoryPanel.add(innerCol1);
        categoryPanel.add(innerCol2);
        categoryPanel.add(innerCol3);


        // Right: Cart Preview
        cartPanel = new JPanel(new BorderLayout());
        cartText = new JTextArea(10, 20);
        cartText.setEditable(false);
        cartPanel.add(new JLabel("Orders:"), BorderLayout.NORTH);
        cartPanel.add(new JScrollPane(cartText), BorderLayout.CENTER);


        menuPanel.add(categoryPanel);
        menuPanel.add(cartPanel);


        // Bottom: Buttons
        JButton orderBtn = new JButton("Place Order");
        orderBtn.addActionListener(e -> placeOrder());


        add(menuPanel, BorderLayout.CENTER);
        add(orderBtn, BorderLayout.SOUTH);
    }


    private JPanel createCategoryPanel(String category, List<MenuItem> items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(category));


        for (MenuItem item : items) {
            JButton btn = new JButton(item.name + " ₱" + item.price);
            btn.addActionListener(e -> addToCart(item.name, item.price));
            panel.add(btn);
        }


        return panel;
    }


    private void addToCart(String name, double price) {
        cart.add(name + " - ₱" + price);
        updateCart();
    }


    private void updateCart() {
        cartText.setText("");
        for (String item : cart) {
            cartText.append(item + "\n");
        }
    }


    private void placeOrder() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }

    markTableAsOccupied(tableId);



        JOptionPane.showMessageDialog(this, "Order placed for Table " + tableId + "!\n" + String.join("\n", cart));
        cart.clear();
        updateCart();
        frame.setContentPane(new TableSelectionPanel(frame));
        frame.revalidate();
        frame.repaint(); 
    }


    private void markTableAsOccupied(int tableId) {
        try (Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE restaurant_table SET is_occupied = true WHERE table_id = ?")) {
            ps.setInt(1, tableId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private Map<String, List<MenuItem>> loadMenuItems() {
        Map<String, List<MenuItem>> menuMap = new HashMap<>();


        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, price, category FROM menu_item")) {


            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String category = rs.getString("category");


                menuMap.computeIfAbsent(category, k -> new ArrayList<>())
                       .add(new MenuItem(name, price));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load menu items: " + e.getMessage());
        }


        return menuMap;
    }


    private static class MenuItem {
        String name;
        double price;


        MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}



