import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    private int userId;
    private String userName;
    private String passwd;

    public String getPasswd() {
        return passwd;
    }

    public String getUserName() {
        return userName;
    }
    static {

    }

    public void createNewUser() {
        String url = "jdbc:mysql://localhost:3306/user_credentials";
        String user = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql1 = String.format("insert into users values(%d,'%s','%s');",userId, userName, passwd);
            int resultSet = statement.executeUpdate(sql1);
            System.out.println(resultSet);
            String sql2 = String.format("create table user_%d_credentials(credential_name varchar(30), value varchar(50));",this.userId);
            int resultSet2 = statement.executeUpdate(sql2);
            System.out.println(resultSet2);
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

    // TODO: ADD ENCRYPTION TO THIS METHOD
    public void addCredentials(int userId, String credentialName, String credentialValue) {

        try{
            String url = "jdbc:mysql://localhost:3306/user_credentials";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = String.format("insert into user_%d_credentials values('%s','%s');",userId,credentialName,credentialValue);
            int resultSet = statement.executeUpdate(sql);
            System.out.println(resultSet);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void removeCredentials(int userId, String credentialName) {
        try{
            String url = "jdbc:mysql://localhost:3306/user_credentials";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = String.format("delete from user_%d_credentials where credential_name = '%s';",userId,credentialName);
            int resultSet = statement.executeUpdate(sql);
            System.out.println(resultSet);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

        //TODO: ADD AUTHENTICATION AND DECRYPTION TO THIS METHOD
    public String retrieveCredential(int userId, String credentialName) {
        try {
            String url = "jdbc:mysql://localhost:3306/user_credentials";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = String.format("select value from user_%d_credentials where credential_name = '%s';",userId,credentialName);
            ResultSet response = statement.executeQuery(query);
            while(response.next()) {
                System.out.println(response.getString("value"));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }




        return "dhungan";// just for now
    };

    // Constructor
    public User(int userId,String userName, String passwd) {
        this.userId = userId;
        this.userName = userName;
        this.passwd = passwd;
        createNewUser();
    }
    public User(){}

    public void showData() {
        System.out.println(this.userId);
        System.out.println(this.userName);
        System.out.println(this.passwd);
    }
    // Destructor


}