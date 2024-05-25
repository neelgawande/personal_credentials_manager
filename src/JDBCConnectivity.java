import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnectivity {
    public void establishConnection() {
        String url = "jdbc:mysql://localhost:3306/user_credentials";
        String user = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void closeConnection() {
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
    public void update() {

    }


    //TODO: REMOVE THIS ONCE IT IS NO LONGER NEEDED
    public static void main(String[] args) {
        JDBCConnectivity obj = new JDBCConnectivity();
        obj.establishConnection();
        obj.closeConnection();
    }
}
//407-C or D CB

/**
 * @DEPRECATED MAIN METHOD
 */


//public static void main(String[] args) {
//    String url = "jdbc:mysql://localhost:3306/testdb";
//    String user = "root";
//    String password = "";
//    try{
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(url,user,password);
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from students");
//
//        while(resultSet.next()){
//            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
//            connection.close();
//        }
//    }
//    catch(Exception e){
//        System.out.println(e);
//    }
//}