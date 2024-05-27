import java.sql.*;
import java.util.*;

public class Login {
    public static Admin adminLogin(int id, String password) throws SQLException{
        Statement statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE id = %d", id);
        ResultSet response = statement.executeQuery(sql);

        while(response.next()){
            if(Authentication.authenticate(id, password)){
                System.out.println("Login Successful for admin with id "+id);
                Admin admin = new Admin(id, password);
                return admin;
            }
            else{
                System.out.println("Login Failed. Password is incorrect");
                return null;
            }
        }
        return null;
    }
    public static void adminLogin(String username, String password){

    }
    public void userLogin() {

    }

    //TODO: REMOVE THIS ONCE IT IS NO LONGER NEEDED
    public static void main(String[] args) throws SQLException, NullPointerException {
        Admin admin = adminLogin(100, "burp");
        assert admin != null;

    }
}
