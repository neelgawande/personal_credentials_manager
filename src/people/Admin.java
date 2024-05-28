package people;

import java.sql.*;
import utility.JDBCConnectivity;

public class Admin  {
    private int userId;
    private String userName;
    private String passwd;

    public int getUserId() {
        return userId;
    }
    public String getPasswd() {
        return passwd;
    }

    public String getUserName() {
        return userName;
    }

    // Constructor
    // these constructors will be used for existing admins.
    public Admin(int id, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE id=%d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            this.userId = resultSet.getInt("id");
            this.userName = resultSet.getString("username");
            this.passwd = resultSet.getString("passwd");
        }
    }
    public Admin(String userName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE username='%s'", userName);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            this.userId = resultSet.getInt("id");
            this.userName = resultSet.getString("username");
            this.passwd = resultSet.getString("passwd");
        }
    }

    // This constructor will be called after creation of a *NEW* admin.
    public Admin(int id, String userName, String passwd){
        this.userId = id;
        this.userName = userName;
        this.passwd = passwd;
    }

    // add a new admin
    public static Admin createNewAdmin(int id, String userName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("INSERT INTO admins values (%d, '%s', '%s');", id, userName, passwd);
        int resultSet = statement.executeUpdate(sql);
        if(resultSet > 0) {
            return new Admin(id, userName, passwd);
        }
        return null;
    }

    // display all users
    public void displayAllUsers() throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM users;");
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            System.out.println("user id: "+resultSet.getInt("id"));
            System.out.println("username: "+resultSet.getString("username")+"\n");
        }
    }

    // display all users
    public void displayAllAdmins() throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins;");
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            System.out.println("user id: "+resultSet.getInt("id"));
            System.out.println("username: "+resultSet.getString("username")+"\n");
        }
    }

    // to add new user
    public static User createNewUser(int userId, String userName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql1 = String.format("CREATE TABLE user_%d_credentials (cred_id int, cred_name varchar(50), cred_value varchar(50));", userId);
        String sql2 = String.format("INSERT INTO users values(%d, '%s', '%s');", userId, userName, passwd);
        int resultSet1 = statement.executeUpdate(sql1);
        int resultSet2 = statement.executeUpdate(sql2);
        return new User(userId, userName, passwd);
    }
}
