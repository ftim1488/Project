import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class InvoiceApp {
    static final String url = "jdbc:mysql://localhost:3306/credential";
    static final String dbUser = "root";
    static final String dbPassword = "";
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(url,dbUser,dbPassword);
            Scanner scanner = new Scanner(System.in)) {
            InvoiceDatabase db = new InvoiceDatabase(conn);
            while (true) {
                System.out.println("\n--- MENU ---\n");
                System.out.println("1. Add Invoice");
                System.out.println("2. Pay Invoice");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter Invoice Number: ");
                        String invno = scanner.nextLine();
                        System.out.println("Enter Customer Name: ");
                        String customer = scanner.nextLine();
                        System.out.println("Enter Invoice Amount: ");
                        int amount = Integer.parseInt(scanner.nextLine());

                        boolean added = db.addInvoice(invno, customer, amount);
                        if (added) {
                            System.out.println("Invoice added successfully. ");
                        } else {
                            System.out.println("Invoice already exist!");
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter Invoice Number ro pay: ");
                        String invno = scanner.nextLine();

                        Invoice invoice = db.getInvoiceByInvno(invno);
                        if (invoice == null) {
                            System.out.println("Invoice not found. ");
                            break;
                        } 
                        System.out.print("Enter payment amount: ");
                        int paymentAmount = Integer.parseInt(scanner.nextLine());

                        int newPayment = invoice.getPayment() + paymentAmount;
                            if (newPayment > invoice.getAmount()) {
                                System.out.println("payement exceeds");
                            } else {
                                db.updatePayment(invno, newPayment);
                                System.out.println("payment recorded");
                            }
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    } 
}