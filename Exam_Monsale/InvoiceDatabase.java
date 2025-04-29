import java.sql.*;
import java.util.Scanner;

public class InvoiceDatabase {
    private String url = "jdbc:mysql://localhost:3306/credential";
    private String dbUser = "root";
    private String dbPassword = "";

    

    
    public boolean authU(String invno){
        String queryU = "SELECT * FROM user WHERE invno = ?";
       
        try(Connection conn = DriverManager.getConnection(url,dbUser,dbPassword);
            PreparedStatement pst = conn.prepareStatement(queryU)){

                pst.setString(1, invno);
      
                try(ResultSet rs = pst.executeQuery()){
                    return rs.next();
                }

            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }   

    }

    public void addUser(String invno, String customer, int amount, int payment){
        String insertQuery = "INSERT INTO user (invno, customer, amount, payment) VALUES (?,?,?,?)";

        try(Connection conn1 = DriverManager.getConnection(url,dbUser,dbPassword);
            PreparedStatement pst1 = conn1.prepareStatement(insertQuery)){
        
                pst1.setString(1, invno);
                pst1.setString(2, customer);
                pst1.setInt(3, amount);
                pst1.setInt(4, payment);

                int rowsAffected = pst1.executeUpdate();
                if(rowsAffected > 0){
                    System.out.println("Invoice successfuly added!");
                }else{
                    System.out.println("Failed to add the Invoice.");
                }

    
        }catch (SQLException e){
        e.printStackTrace();
        }
    }

    public void updateUser(String invno, int payment){
        String updateQuery = "Update user Set payment = ? where invno = ?";

        try(Connection conn2 = DriverManager.getConnection(url,dbUser,dbPassword);
            PreparedStatement pst2 = conn2.prepareStatement(updateQuery)){

                pst2.setString(2, invno);
                pst2.setInt(1, payment);

                int rowsAffected = pst2.executeUpdate();
                if(rowsAffected > 0){
                    System.out.println("Payment recorded");
                }else{
                    System.out.println("Failed to add the student.");
                }
            }catch (SQLException e){
                e.printStackTrace();
                
        }
            
    }


    public void deleteUser(String invno){
        String deleteQuery = "DELETE FROM user WHERE invno = ?";


        try(Connection conn2 = DriverManager.getConnection(url,dbUser,dbPassword);
            PreparedStatement pst2 = conn2.prepareStatement(deleteQuery)){

                pst2.setString(1, invno);
                
                int rowsAffected = pst2.executeUpdate();
                if(rowsAffected > 0){
                    System.out.println("Invoice Deleted Successfully!");
                }else{
                    System.out.println("Failed to delete Invoice.");
                    return;
                }

    
    }catch (SQLException e){
        e.printStackTrace();
        
    }




}
    
    
        

    



}
