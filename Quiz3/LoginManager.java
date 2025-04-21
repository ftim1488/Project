import java.util.Scanner;


public class LoginManager {
    private DatabaseManager dbManager;
    private static final int MAX_ATTEMPTS = 3;

    public LoginManager(DatabaseManager dbManager){
        this.dbManager = dbManager;
    }

    public boolean login (String username, String password){
        return dbManager.authenticateUser(username, password);
    }

    public boolean search (String username) {
        return dbManager.checkUser(username);
    }


    public void startLoginProccess(){
        Scanner scanner = new Scanner(System.in);
        int attemptCount = 0;

        while (attemptCount < MAX_ATTEMPTS){
            System.out.println("Enter Username: ");
            String inputUsername = scanner.nextLine();

            System.out.println("Enter Password: ");
            String inputPassword = scanner.nextLine();

            if (login(inputUsername, inputPassword)){
                System.out.println("Login Successful!");
                return;
            }else {
                attemptCount++;
                System.out.println("Invalid Username or Password. Attempts remaining: " + (MAX_ATTEMPTS - attemptCount) + "\n");
            }

            if (attemptCount == 3){
                System.out.println("Terminated. Too many failed attempts.");
            }
            
        }
        
        
        
    }
    
    
    public void addUserCred(){
        DatabaseManager dbManager = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new username: ");
            String username = scanner.nextLine();
            System.out.println("Enter new  password: ");
            String password = scanner.nextLine();

        dbManager.addUser(username, password);

    }

    public void updateUserCred(){
 
            DatabaseManager dbManager = new DatabaseManager();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter username to search: ");
            String username = scanner.nextLine();

            if (search(username)){
                System.out.println("Userfound. Enter new password");
                String password = scanner.nextLine();
                
                dbManager.updateUser(username,password);

        }
        

    }

    }



