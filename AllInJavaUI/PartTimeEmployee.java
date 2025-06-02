// Inherits from Employee (Inheritance)
public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, int id, double rate, int hours) {
        super(name, id);
        this.hourlyRate = rate;
        this.hoursWorked = hours;
    }

    // Implements abstract method (Polymorphism)
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}
