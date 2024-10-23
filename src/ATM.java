import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/atm_database", "postgres", "Gunay123");
        Statement st = con.createStatement();
        System.out.println("Sign up or log in");
        Scanner obj = new Scanner(System.in);
        String autho = obj.nextLine();
        Authorization authorization = new Authorization();
        if (!(autho.equals("signup")|| autho.equals("login"))){
            System.out.println("Wrong autho");
            return;
        }
        System.out.println("Name and password");
        String fullname = obj.nextLine();
        String password = obj.nextLine();
        switch (autho) {
            case "signup":
                authorization.signUp(fullname, password);
                break;
            case "login":
                authorization.logIn(fullname, password);
                break;
        }
        boolean quit = false;
        while (true) {
            System.out.println("Deposit, Withdraw, Transfer, Balance, Quit");
            String a = obj.next();
            ResultSet rs = st.executeQuery("SELECT id from customers WHERE fullname = '" + fullname + "' AND password = '" + password + "'");
            rs.next();
            int id = rs.getInt("id");
            ResultSet b = st.executeQuery("SELECT amount from accounts WHERE id_custumer = '" + id + "'");
            b.next();
            int amount = b.getInt("amount");
            Account account = new Account(amount);
            switch (a) {
                case "Deposit":
                    double amount0 = obj.nextDouble();
                    account.depositfunc(amount0, id);
                    break;
                case "Withdraw":
                    double amount1 = obj.nextDouble();
                    account.withdrawfunc(amount1,id);
                    break;
                case "Transfer":
                    int transferwho = obj.nextInt();
                    double amount2 = obj.nextDouble();
                    account.tranferfunc(account, account, amount2,id,transferwho);
                    break;
                case "Balance":
                    System.out.println(amount);
                    break;
                case "Quit":
                    quit = true;
                    break;
                default:
                    System.out.println("There is no such service");
                    }
                    if (quit) {
                        break;
                    }
                }
        con.close();
    }
}