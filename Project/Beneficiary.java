package Project;

public class Beneficiary {

    private String name;
    private int age;
    private String userId;
    private int amount;
    private int deductible; 
    private String copay;
    private int Cov_Per;
    private int User_Per;
    private String plan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDedictible() {
        return deductible;
    }

    public void setDeductible(int deductible) {
        this.deductible = deductible;
    }

    public String getCopay() {
        return copay;
    }

    public void setCopay(String copay) {
        this.copay = copay;
    }

    public int getUserPercent() {
        return User_Per;
    }

    public void setUserPercent(int User_Per) {
        this.User_Per = User_Per;
    }
    public int getCoveragePercent() {
        return Cov_Per;
    }

    public void setCoveragePercent(int Cov_Per) {
        this.Cov_Per = Cov_Per;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }


    @Override
    public String toString() {
        return "Beneficiary { Name: " + name + ", Age: " + age + ", User ID: " + userId + ", Plan: " + plan + ", Coverage Limit: " + amount + " }";
    }
}