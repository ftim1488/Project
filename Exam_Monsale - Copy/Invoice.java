public class Invoice {
    private String invno;
    private String customer;
    private int amount;
    private int payment;

    public Invoice (String invno, String customer, int amount, int payment) {
        this.invno = invno;
        this.customer = customer;
        this.amount = amount;
        this.payment = payment;
    }

    public String getInvo() {return invno; }
    public String getCustomer() {return customer; }
    public int getAmount()  {return amount; }
    public int getPayment () {return payment; }
    public int getBalance() {return amount - payment; }

    @Override public String toString() {
        return String.format("Invoice: %s | Customer: %s | Balabce: %d  ", invno, customer, getBalance());
    }

}