import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDatabase {
    private final Connection conn; 
  
    public InvoiceDatabase(Connection conn) {
        this.conn = conn;
    }
    
    public boolean InvoiceExists(String invno){
        String sql = "SELECT * FROM receivable WHERE invno = ?";
       
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, invno);
                ResultSet rs = stmt.executeQuery();
                return rs.next();     
            }catch (SQLException e){
                System.out.println("Error checking invoice: " + e.getMessage());
                return false;
            }   

    }

    public boolean addInvoice (String invno, String customer, int amount) {
        if (InvoiceExists(invno)) {
            return false;
        }
        String sql ="Insert INTO receivable (invno, customer, amount, payment) VALUES (?, ?, ?, 0)" ;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invno);
            stmt.setString(2, customer);
            stmt.setInt(3, amount);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding invoice: " + e.getMessage());
            return false;
        }
    } 

    public List <Invoice> getInvoicesWithBalance() {
        List<Invoice> invoices = new ArrayList<>();
        String sql ="Select invno, customer, amount, payment From receivable WHERE amount > payment";

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    invoices.add(new Invoice(
                        rs.getString("invno"),
                        rs.getString("customer"),
                        rs.getInt("amount"),
                        rs.getInt("payment")
                    ));
                    
                }
        }catch (SQLException e) {
            System.out.println("Error retrieving invoice: " + e.getMessage());
        }
        return null;
    }
    public Invoice getInvoiceByInvno(String invno) {
        String sql = "SELECT invno, customer, amount, payment FROM receivable WHERE invno = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invno);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Invoice(
                    rs.getString("invno"),
                    rs.getString("customer"),
                    rs.getInt("amount"),
                    rs.getInt("payment")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving invoice: " + e.getMessage());

        }
        return null;

    }

    public void updatePayment (String invno, int newPayment) {
        String sql = "UPDATE receivable SET payment = ? WHERE Invno = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newPayment);
            stmt.setString(2, invno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating payment: " + e.getMessage());

        }
    }
    
    
    
        

    



}
