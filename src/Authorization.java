import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Authorization {
    public Authorization() {}
    public void signUp(String fullname, String password)throws Exception{
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/atm_database","postgres","Gunay123");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from customers WHERE fullname = '" + fullname + "' AND password = '" + password + "'");
        if (rs.next())
        {
            System.out.println("User already exists");
            throw new Exception("User already exists");
        }
        else{
            rs = st.executeQuery("INSERT INTO customers (fullname, password) VALUES ('" + fullname +"', '" + password +"') RETURNING id");
            rs.next();
            st.executeUpdate("INSERT INTO accounts (id_custumer, amount) VALUES ('" + rs.getInt("id") + "', '" + 0 + "')");
        }
    }
    public int logIn(String fullname, String password)throws Exception{
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/atm_database","postgres","Gunay123");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from customers WHERE fullname = '" + fullname + "' AND password = '" + password + "'");
        if(rs.next()){
            System.out.println("Login successful");
        }
        return rs.getInt("id");
    }
}
