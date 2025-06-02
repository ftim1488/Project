// Abstract class (Abstraction)
public abstract class Employee {
    private String name; // Encapsulation
    private int id;      // Encapsulation

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getters (Encapsulation)
    public String getName() { return name; }
    public int getId() { return id; }

    // Abstract method (Abstraction)
    public abstract double calculateSalary();

    // Shared method
    public void displayInfo() {
        System.out.println("Employee: " + name + ", ID: " + id);
    }
}
