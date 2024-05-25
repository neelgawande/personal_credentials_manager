import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
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
        User user1 = new User();
//        user1.removeCredentials(101, "email");
        user1.retrieveCredential(101,"email");

//        try{
//            String name = user1.getUserName();
//            String passwd = user1.getPasswd();
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url,user,password);
//            Statement statement = connection.createStatement();
//            String sql = String.format("insert into users values(1,'%s','%s')",name,passwd);
//            String toSql = sql;
//            int resultSet = statement.executeUpdate(toSql);
//            System.out.println("User created: " + resultSet);
//
//            connection.close();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
    }
}
