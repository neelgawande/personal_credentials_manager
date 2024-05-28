import java.sql.*;

public class User {
    private int userId;
    private String userName;
    private String passwd;

    public int getUserId() {
        return this.userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public String getPasswd() {
        return this.passwd;
    }

    // Constructors
    // this constructor will be called after creation for a new user.
    public User(int id, String name, String passwd) {
        this.userId = id;
        this.userName = name;
        this.passwd = passwd;
    }

    // these constructors will be called for existing users.
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
    }



    // To create a *NEW* user
    public static User createNewUser(int userId, String userName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql1 = String.format("CREATE TABLE user_%_credentials (cred_id int, credential_name varchar(50), passwd varchar(50));", userId);
        String sql2 = String.format("INSERT INTO users values(%d, '%s', '%s');", userId, userName, passwd);
        int resultSet1 = statement.executeUpdate(sql1);
        ResultSet resultSet2 = statement.executeQuery(sql2);
        while(resultSet2.next()) {
            return new User(userId, userName, passwd);
        }
        return null;
    }
}
