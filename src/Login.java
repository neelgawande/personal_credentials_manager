import java.sql.*;

/**
 * @WARNING: DO NOT USE the 'authenticate()' method for users, only use it for admins. For users use the 'authenticateUser()' method instead.
 */

public class Login {
    public static Admin adminLogin(int id, String password) throws SQLException{
        Statement statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE id = %d", id);
        assert statement != null;
        ResultSet response = statement.executeQuery(sql);

        while(response.next()){
            if(Authentication.authenticate(id, password)){
                System.out.println("Login Successful for admin with id "+id);
                return new Admin(id, password);
            }
            else{
                System.out.println("Login Failed. Password is incorrect");
            }

        }
        return null;
    }
    public static Admin adminLogin(String username, String password) throws SQLException {
        Statement statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE username = '%s'", username);
        ResultSet response = statement.executeQuery(sql);

        while(response.next()){
            if(Authentication.authenticate(username, password)){
                System.out.println("Login Successful for admin with username "+username);
                return new Admin(username, password);
            }
            else{
                System.out.println("Login Failed. Password is incorrect");
            }
        }
        return null;
    }
    public static User userLogin(int id, String password) throws SQLException {
        Statement statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        ResultSet response = statement.executeQuery(sql);
        while(response.next()){
            if(Authentication.authenticateUser(id, password)){
                System.out.println("Login Successful for user with id "+id);
                return new User(id, password);
            }
        }
        return null;
    }

    //TODO: REMOVE THIS ONCE IT IS NO LONGER NEEDED
    public static void main(String[] args) throws SQLException, NullPointerException {
//        Admin admin = adminLogin(100, "burp");
//        assert admin != null;
        User user1 = userLogin(101, "ngpasswd1");
        assert user1 != null;
        user1.displayUserInformation("ngpasswd1");
        user1.displayAllCredentials("ngpasswd1");

    }
}
