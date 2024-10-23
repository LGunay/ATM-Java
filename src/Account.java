import java.sql.*;

public class Account {
    double amount;

    Account(double amount) {
        this.amount = amount;
    }
    public void depositfunc(double amount, int id) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/atm_database","postgres","Gunay123");
        Statement st = con.createStatement();
        this.amount += amount;
        st.executeUpdate("UPDATE accounts SET amount = amount + " + amount + " WHERE id_custumer = " + id);
    }

    public void withdrawfunc(double amount, int id) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/atm_database","postgres","Gunay123");
        Statement st = con.createStatement();
        this.amount -= amount;
        st.executeUpdate("UPDATE accounts SET amount = amount - " + amount + " WHERE id_custumer = " + id);
    }
    public void tranferfunc(Account from, Account to, double amount, int id, int transferid) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/atm_database","postgres","Gunay123");
        Statement st = con.createStatement();
        from.withdrawfunc(amount,id);
        to.depositfunc(amount,transferid);
    }

}
