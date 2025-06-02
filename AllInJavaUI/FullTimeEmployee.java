// Inherits from Employee (Inheritance)
public class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double salary) {
        super(name, id);
        this.monthlySalary = salary;
    }

    // Implements abstract method (Polymorphism)
    public double calculateSalary() {
        return monthlySalary;
    }
}
