import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Welcome to PERSONAL CREDENTIALS MANAGER");
        System.out.println("Choose one of the following options");
        System.out.println("1. Create a new account");
        System.out.println("2. Add a new Credential");
        System.out.println("3. Modify an existing Credential");
        System.out.println("4. Delete an existing Credential");

        String url = "jdbc:mysql://localhost:3306/user_credentials";
        String user = "root";
        String password = "root";

//        User user1 = new User(101,"Neel", "haha");
//    User user1 = new User(101);
//        User user2 = new User(102, "Vikas", "sanki");

        User user2 = new User(102);
        user2.addCredentials(102, "Mobile-PIN", "9087", "sanki");



    }
}
