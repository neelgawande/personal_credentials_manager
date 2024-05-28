package people;
import utility.Authentication;
import utility.Credential;
import java.sql.*;
import java.util.ArrayList;
import utility.JDBCConnectivity;

public class User {
    private int userId;
    private String userName;
    private String passwd;
    private ArrayList<Credential> credentials = new ArrayList<Credential>();
    public int getUserId() {
        return this.userId;
    }
    public String getUserName() {
        return this.userName;
    }
    private String getPasswd() {
        return this.passwd;
    }

    // Constructors
    // this constructor will be called after creation for a new user.
    public User(int id, String name, String passwd) {
        this.userId = id;
        this.userName = name;
        this.passwd = passwd;
    }

    // this constructor will be called for existing users.
    public User(int id, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM users WHERE id=%d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            this.userId = resultSet.getInt("id");
            this.userName = resultSet.getString("username");
            this.passwd = resultSet.getString("passwd");
        }
        JDBCConnectivity.closeConnection();
        Statement statement2 = JDBCConnectivity.establishConnection();
        String sql2 = String.format("SELECT * FROM user_%d_credentials", id);
        ResultSet resultSet2 = statement2.executeQuery(sql2);
        while(resultSet2.next()) {
            this.credentials.add(new Credential(resultSet2.getInt("cred_id"), resultSet2.getString("cred_name"), resultSet2.getString("cred_value")));
        }
    }

    public void addCredential(int credentialId,String credentialName, String value, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        if(!Authentication.authenticateUser(this.userId, passwd)){
            System.out.println("Incorrect password for userId "+this.userId);
            return;
        }
        String sql = String.format("INSERT INTO user_%d_credentials VALUES (%d, '%s', '%s');",this.userId, credentialId, credentialName, value);
        int resultSet = statement.executeUpdate(sql);
        this.credentials.add(new Credential(credentialId, credentialName, value));
        if(resultSet == 1) {
            System.out.println("Successfully added credential "+credentialName+ " to user id "+this.userId);
        }
        else{
            System.out.println("Failed to add credential "+credentialName+ " to user id "+this.userId);
        }
    }
    public String retrieveCredential(int credentialId, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        if(!Authentication.authenticateUser(this.userId, passwd)){
            System.out.println("Incorrect password entered for userId "+this.userId);
            return null;
        }
        String sql = String.format("SELECT * FROM user_%d_credentials WHERE cred_id=%d", this.userId, credentialId);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            return resultSet.getString("cred_value");
        }
    return null;
    }

    public String retrieveCredential(String credentialName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        if(!Authentication.authenticateUser(this.userId, passwd)){
            System.out.println("Incorrect password entered for userId "+this.userId);
            return null;
        }
        String sql = String.format("SELECT * FROM user_%d_credentials WHERE cred_name='%s'", this.userId, credentialName);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            return resultSet.getString("cred_value");
        }
        return null;
    }

    public void displayUserInformation(String passwd) throws SQLException {
        if(!Authentication.authenticateUser(this.userId, passwd)){
            System.out.println("Incorrect password entered for userId "+this.userId);
            return;
        }
        System.out.println("\n ===================== USER INFORMATION FOR USERNAME: "+this.userName+" AND ID:  =====================\n"+this.userId);
        System.out.println(this.userId);
        System.out.println(this.getUserName());
        System.out.println(this.getPasswd());
    }

    public void displayAllCredentials(String passwd) throws SQLException {
        if(!Authentication.authenticateUser(this.userId, passwd)){
            System.out.println("Incorrect password entered for userId "+this.userId);
            return;
        }
        System.out.println("\n ===================== DISPLAYING ALL CREDENTIALS FOR USER "+this.userName+" =====================\n");
        for(Credential cred: this.credentials) {
            System.out.println("\n=====================================================================================\n");
            System.out.println("credential id: "+cred.getCredId());
            System.out.println("credential name: "+cred.getCredName());
            System.out.println("credential value: "+cred.getValue());
        }
    }

}
