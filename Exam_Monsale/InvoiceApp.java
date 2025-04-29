import java.util.Scanner;

public class InvoiceApp {
    private InvoiceDatabase dbManager;


    public InvoiceApp(InvoiceDatabase dbManager){
        this.dbManager = dbManager;
    }

    public boolean uTest(String invno){
        return dbManager.authU(invno);
    }
    

    public static void addUserCred(){
        InvoiceDatabase dbManager = new InvoiceDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Invoice Number: ");
            String invno = scanner.nextLine();
            System.out.println("Enter Customer Name: ");
            String customer = scanner.nextLine();
            System.out.println("Enter Invoice Amount: ");
            int amount = scanner.nextInt();

            int payment = 0;

        dbManager.addUser(invno, customer, amount, payment);

    }

    

    public void payUser(){
        InvoiceDatabase dbManager = new InvoiceDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Invoice Number to pay: ");
        String invno1 = scanner.nextLine();

        if(uTest(invno1)){
          System.out.println("Enter Payment Amount: ");
          
          int payment = scanner.nextInt();
          
          dbManager.updateUser(invno1, payment);
        } else{
            System.out.println("Invoice not found");
        }

        

    }

    public void deleteUser(){
        InvoiceDatabase dbManager = new InvoiceDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Invoice Number to delete: ");
        String invno = scanner.nextLine();

        dbManager.deleteUser(invno);

    }


}