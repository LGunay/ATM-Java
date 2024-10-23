public class Customer {
    protected String fullname;
    protected String Password;
    protected Account account;
    public Customer() {}
    public Customer(String fullname, String Password,double amount) {
        this.fullname = fullname;
        this.Password = Password;
        this.account = new Account(amount);
    }
}
