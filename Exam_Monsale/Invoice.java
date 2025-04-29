import java.util.Scanner;
import java.sql.*;

public class Invoice {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/credential";
        String user = "root";
        String password = "";

        String selectQuery = "SELECT invno, customer, amount, payment From user";
        String insertQuery = "INSERT INTO student (invno, customer, amount, payment) VALUES (?,?,?,?)";


        InvoiceDatabase dbManager = new InvoiceDatabase();
        InvoiceApp invoiceApp = new InvoiceApp(dbManager);
        

        while (true) {
        Scanner scanner = new Scanner(System.in);  
        
        System.out.println("--- MENU ---");
        System.out.println("1. Add Invoice");
        System.out.println("2. Display Invoices");
        System.out.println("3. Pay Invoice");
        System.out.println("4. Delete Invoice");
        System.out.println("5. Exit");
        System.out.print("Select an option (1-5): ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1){
            System.out.println("");
            InvoiceApp.addUserCred();
            System.out.println("");
        } else if (option == 2) {   
            System.out.println("");
            try{
                Connection connection = DriverManager.getConnection(url,user,password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);


            System.out.println("--- Invoices with Balance---");
            while (resultSet.next()) {
                String invno = resultSet.getString("invno");
                String customer = resultSet.getString("customer");
                String amount = resultSet.getString("amount");
                String mi = resultSet.getString("payment");

                System.out.println("Invoice Number: " + invno + " Customer: " + customer + " Amount: " +amount);
            }
        } catch (SQLException e){
            e.printStackTrace();
            }
            
            System.out.println("");
        } else if (option == 3) {
            System.out.println("");
            invoiceApp.payUser();
            System.out.println("");
        } else if (option == 5) {
            System.out.println("Exciting...");
            break;
        } else if (option == 4) {
            System.out.println("");
            invoiceApp.deleteUser();
            System.out.println("");
        }


        }
    }
}