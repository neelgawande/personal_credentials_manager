import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCConnectivity {
    public static Statement establishConnection() {
        String url = "jdbc:mysql://localhost:3306/user_credentials";
        String user = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            return statement;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static void closeConnection() {
        String url = "jdbc:mysql://localhost:3306/user_credentials";
        String user = "root";
        String password = "root";
        try{
        DriverManager.getConnection(url,user,password).close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
//407-C or D CB