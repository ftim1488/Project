import java.util.Scanner;

public class Company {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();
        Employee emp = null;

        System.out.println("Enter employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Enter employee name: ");
        String name = sc.nextLine();

        System.out.println("Enter employee type (full/part): ");
        String type = sc.nextLine().toLowerCase();

        if (type.equals("full")) {
            // Full-time employee
            System.out.println("Enter monthly salary: ");
            double salary = sc.nextDouble();

            emp = new FullTimeEmployee(name, id, salary);
            dao.saveEmployee(emp, "full", salary, 0);

        } else if (type.equals("part")) {
            // Part-time employee
            System.out.println("Enter hourly rate: ");
            double rate = sc.nextDouble();

            System.out.println("Enter hours worked: ");
            int hours = sc.nextInt();

            emp = new PartTimeEmployee(name, id, rate, hours);
            dao.saveEmployee(emp, "part", rate, hours);

        } else {
            System.out.println("Invalid type entered.");
            sc.close();
            return;
        }

        // Display the employee (using polymorphism)
        emp.displayInfo();
        System.out.println("Calculated Salary: $" + emp.calculateSalary());
        sc.close();
    }
}
